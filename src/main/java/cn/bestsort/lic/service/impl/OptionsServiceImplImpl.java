package cn.bestsort.lic.service.impl;

import cn.bestsort.lic.cache.MemoryCacheStore;
import cn.bestsort.lic.model.entity.Options;
import cn.bestsort.lic.model.enums.propertys.PropertyEnum;
import cn.bestsort.lic.repository.OptionsRepository;
import cn.bestsort.lic.service.OptionsService;
import cn.bestsort.lic.service.base.AbstractBaseService;
import cn.bestsort.lic.utils.EnumUtil;
import cn.bestsort.lic.utils.ServiceUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 用户配置表(Options)表服务实现类
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
 */
@Service
public class OptionsServiceImpl extends AbstractBaseService<Options, Long> implements OptionsService {

    private final OptionsRepository optionsRepository;
    private final ApplicationContext applicationContext;
    private final MemoryCacheStore memoryCacheStore;
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
        return null;
    }

    @Override
    public String QueryValueByKeyOrDefault(String optionKey, String defaultOptionValue) {
        return null;
    }

    @Override
    public void inertOrUpdate(String optionKey, String optionValue) {

    }

    @Override
    public void insertOrUpdateBySet(Map<String, String> set) {
        return 0;
    }


    @Override
    public void insertOrUpdateByOptions(List<Options> options) {
        return 0;
    }

    @Override
    public boolean deleteByKey(String key) {
        return false;
    }

    public OptionsServiceImpl(OptionsRepository optionsRepository,
                              ApplicationContext applicationContext,
                              MemoryCacheStore memoryCacheStore,
                              ApplicationEventPublisher eventPublisher,
                              Validator validator){
        super(optionsRepository);
        this.applicationContext = applicationContext;
        this.optionsRepository = optionsRepository;
        this.memoryCacheStore = memoryCacheStore;
        this.eventPublisher = eventPublisher;
        this.validator = validator;

        propertiesEnumMap = Collections.unmodifiableMap(EnumUtil.getValuePropertyEnumMap());
    }


    private void publishOptionUpdatedEvent(){
        flush();
        //cleanCache();;
    }
}