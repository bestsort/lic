package cn.bestsort.lic;

import cn.bestsort.lic.cache.CacheStoreInterface;
import cn.bestsort.lic.config.properties.LicProperties;
import cn.bestsort.lic.handler.CacheHandler;
import cn.bestsort.lic.service.OptionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;

/**
 * @author bestsort
 * @version 1.0
 * @date 2020/3/16 上午11:44
 */

@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StartedListener implements ApplicationListener<ApplicationStartedEvent> {

    @Resource
    private LicProperties licProperties;
    @Resource
    private CacheHandler cacheHandler;
    @Resource
    private OptionsService optionsService;
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
    }
}
