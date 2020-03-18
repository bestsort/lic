package cn.bestsort.lic.service.impl;

import cn.bestsort.lic.handler.CacheStoreHandler;
import cn.bestsort.lic.model.entity.Options;
import cn.bestsort.lic.model.enums.propertys.PropertyEnum;
import cn.bestsort.lic.repository.OptionsRepository;
import cn.bestsort.lic.service.OptionsService;
import cn.bestsort.lic.service.base.AbstractBaseServiceImpl;
import cn.bestsort.lic.utils.EnumUtil;
import cn.bestsort.lic.utils.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.*;

/**
 * 用户配置表(Options)表服务实现类
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
 */
@Slf4j
@Service
public class OptionsServiceImplImpl extends AbstractBaseServiceImpl<Options, Long> implements OptionsService {

    private final OptionsRepository optionsRepository;
    private final ApplicationContext applicationContext;
    private final Map<String, PropertyEnum> propertiesEnumMap;
    private final  ApplicationEventPublisher eventPublisher;
    private final  Validator validator;

    @Transactional(rollbackOn = Exception.class)
    public void save(Map<String, Object> objectMap){
        if (CollectionUtils.isEmpty(objectMap)){
            return;
        }
        Map<String, Options> optionKeyMap = ServiceUtil.convertToMap(listAll(), Options::getOptionKey);

        List<Options> optionsToCreate = new LinkedList<>();
        List<Options> optionsToUpdate = new LinkedList<>();

        objectMap.forEach((key, value) -> {
            Options option = optionKeyMap.get(key);
            if (option == null || !option.getOptionValue().equals(value.toString())) {
                if (option == null){
                    // Create
                    option = new Options();
                    option.setOptionKey(key);
                    option.setOptionValue(value.toString());
                    validator.validate(option);
                    optionsToCreate.add(option);
                }
                else if (!option.getOptionValue().equals(value.toString())) {
                    // Update it
                    option.setOptionValue(value.toString());
                    optionsToUpdate.add(option);
                }
            }
        });

        // Update them
        updateInBatch(optionsToUpdate);

        // Create them
        createInBatch(optionsToCreate);

        if (!CollectionUtils.isEmpty(optionsToUpdate) || !CollectionUtils.isEmpty(optionsToCreate)) {
            // If there is something changed
            publishOptionUpdatedEvent();
        }
    }


    @Override
    public String queryValueByKey(String key) {
        Options options = optionsRepository.findByOptionKey(key);
        return options == null ? null : options.getOptionValue();
    }

    @Override
    public String queryValueByKeyOrDefault(String optionKey, Object defaultOptionValue) {
        Options result = optionsRepository.findByOptionKey(optionKey);
        return result == null ? defaultOptionValue.toString() : result.getOptionKey();
    }

    @Override
    public void inertOrUpdate(String optionKey, String optionValue) {
        Options options = optionsRepository.findByOptionKey(optionKey);
        if (options == null){
            options = new Options();
        }
        options.setOptionValue(optionValue);
        options.setOptionKey(optionKey);
        optionsRepository.save(options);
        log.debug("数据库内配置已刷新 [{}] <---> [{}]", optionKey, optionValue);
    }

    @Override
    public void insertOrUpdateBySet(Map<String, String> set) {

    }

    @Override
    public boolean deleteByKey(String key) {
        return false;
    }

    public OptionsServiceImplImpl(OptionsRepository optionsRepository,
                                  ApplicationContext applicationContext,
                                  CacheStoreHandler cacheStoreHandler,
                                  ApplicationEventPublisher eventPublisher,
                                  Validator validator){
        super(optionsRepository);
        this.applicationContext = applicationContext;
        this.optionsRepository = optionsRepository;
        this.eventPublisher = eventPublisher;
        this.validator = validator;

        propertiesEnumMap = Collections.unmodifiableMap(EnumUtil.getValuePropertyEnumMap());
    }


    private void publishOptionUpdatedEvent(){
        flush();
        //cleanCache();;
    }
}