package cn.bestsort.lic.listener;

import cn.bestsort.lic.handler.CacheStoreHandler;
import cn.bestsort.lic.handler.FileStoreHandler;
import cn.bestsort.lic.model.enums.CacheStoreType;
import cn.bestsort.lic.model.enums.FileStoreType;
import cn.bestsort.lic.model.enums.propertys.LicPropertyFiled;
import cn.bestsort.lic.model.support.LicConst;
import cn.bestsort.lic.service.OptionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * 在启动时需要进行的一些工作
 * @author bestsort
 * @version 1.0
 * @date 2020/3/16 上午11:44
 */

@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StartedListener implements ApplicationListener<ApplicationStartedEvent> {
    @Resource
    private CacheStoreHandler cacheStoreHandler;
    @Resource
    private OptionsService optionsService;
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        loadCacheStore();
        loadFileStore();
        log.info("Lic [V{}] 启动成功, 点击链接打开主页{}",
            LicConst.VERSION,
            AnsiOutput.toString(AnsiColor.BLUE, " http://localhost:8080"));
    }

    /**
     * 加载文件系统配置, 若无则使用默认存储
     */
    private void loadFileStore() {
        FileStoreHandler.setStrategy(
            FileStoreType.valueOf(
                FileStoreType.class,
                optionsService.queryValueByKeyOrDefault(
                    LicPropertyFiled.STORAGE_STRATEGY.getValue(),
                    LicPropertyFiled.STORAGE_STRATEGY.defaultValue()
                )
            )
        );
        /**
         * 将配置写入缓存
         */
        cacheStoreHandler.fetchCacheStore().put(
            LicPropertyFiled.STORAGE_STRATEGY.getValue(),
            FileStoreHandler.getStrategy()
        );
        log.info("文件系统设置完毕, 将使用 {} 作为文件存储系统", FileStoreHandler.getStrategy());
    }

    /**
     * 加载缓存配置, 若无则使用 JVM 堆作为缓存
     */
    private void loadCacheStore(){
        cacheStoreHandler.setStrategy(
            CacheStoreType.valueOf(
                CacheStoreType.class,
                optionsService.queryValueByKeyOrDefault(
                    LicPropertyFiled.CACHE_STRATEGY.getValue(),
                    LicPropertyFiled.CACHE_STRATEGY.defaultValue()
                )
            ),
            false
        );
        Assert.notNull(cacheStoreHandler.fetchCacheStore(), "缓存系统未实现");
        /**
         * 将配置写入缓存
         */
        cacheStoreHandler.fetchCacheStore().put(
            LicPropertyFiled.CACHE_STRATEGY.getValue(),
            cacheStoreHandler.getStrategy()
        );
        log.info("缓存设置完毕, 将使用 {}  作为缓存", cacheStoreHandler.getStrategy());
    }
}
