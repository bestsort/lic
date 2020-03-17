package cn.bestsort.lic.listener;

import cn.bestsort.lic.listener.event.CacheEvent;
import cn.bestsort.lic.service.OptionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 * @author bestsort
 * @version 1.0
 * @date 2020/3/17 上午9:55
 */

@Slf4j
@Configuration
public class CacheListener implements ApplicationListener<CacheEvent> {

    @Resource
    OptionsService optionsService;

    @Override
    public void onApplicationEvent(CacheEvent event) {
        optionsService.inertOrUpdate(event.getKey(), event.getValue());
        log.debug("新的缓存修改 [{}] <---> [{}] 已经写入数据库", event.getKey(), event.getValue());
    }
}
