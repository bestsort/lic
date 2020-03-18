package cn.bestsort.lic.cache;

import cn.bestsort.lic.model.enums.CacheStoreType;
import cn.bestsort.lic.utils.TimeUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bestsort
 * @version 1.0
 * @date 3/12/20 10:50 AM
 */
@Slf4j
@Component
public class MemoryCacheStore extends AbstractStringCacheStore {
    private static final int CACHE_INIT_SIZE = 32;
    private static ConcurrentHashMap<String, String> CACHE_CONTAINER;

    @Override
    void putInternal(String key, String value) {
        CACHE_CONTAINER.put(key, value);
        log.debug("缓存内配置已刷新   [{}] <---> [{}]", key, value);
    }

    @Override
    public void clearCache() {
        CACHE_CONTAINER = null;
    }

    @Override
    public Optional<String> getInternal(String key) {
        return Optional.ofNullable(CACHE_CONTAINER.get(key));
    }

    @Override
    public void deleteInternal(@NonNull String key){
        CACHE_CONTAINER.remove(key);
        log.debug("Removed key: [{}]", key);
    }

    @Override
    public CacheStoreType getCacheType(){
        return CacheStoreType.DEFAULT;
    }

    @Override
    public void tryInit(){
        // double check
        if (CACHE_CONTAINER == null){
            synchronized (this){
                if (CACHE_CONTAINER == null){
                    CACHE_CONTAINER = new ConcurrentHashMap<>(CACHE_INIT_SIZE);
                }
            }
        }
    }
}
