package cn.bestsort.lic.cache;

import cn.bestsort.lic.model.enums.CacheStoreType;
import cn.bestsort.lic.utils.TimeUtil;
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
    private static ConcurrentHashMap<String, CacheWrapper<String>> CACHE_CONTAINER;

    @Override
    Optional<CacheWrapper<String>> getInternal(String key) {
        Assert.hasText(key, "key must not be blank");
        CacheWrapper<String> cacheWrapper = CACHE_CONTAINER.get(key);
        if (cacheWrapper.getExpireAt().before(TimeUtil.now())){
            delete(key);
            return Optional.empty();
        }
        return Optional.ofNullable(cacheWrapper);
    }

    /**
     *
     * get cache wrapper by key
     * @param key           must not be null
     * @param cacheWrapper  must not be null
     * @param isPushed      {@code true} the K-V must be pushed to database;{@code false} otherwise;
     */
    void putInternal(String key, CacheWrapper<String> cacheWrapper, boolean isPushed){
        put(key, cacheWrapper, false, isPushed);
    }

    Boolean putInternalIfAbsent(String key, CacheWrapper<String> cacheWrapper, boolean isPushed){
        put(key, cacheWrapper, true, isPushed);
        return true;
    }

    @Override
    void putInternal(String key, CacheWrapper<String> cacheWrapper) {
        put(key, cacheWrapper, false, false);
    }

    @Override
    Boolean putInternalIfAbsent(String key, CacheWrapper<String> cacheWrapper) {
        put(key, cacheWrapper, true, false);
        return true;
    }



    private CacheWrapper<String> put(String key, CacheWrapper<String> cacheWrapper, boolean ifAbsent, boolean isPushed){
        Assert.hasText(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");
        if (CACHE_CONTAINER == null){
            tryInit();
        }
        // Put the cache wrapper
        CacheWrapper<String> putCacheWrapper = ifAbsent ?
            CACHE_CONTAINER.putIfAbsent(key, cacheWrapper):
            CACHE_CONTAINER.put(key, cacheWrapper);
        if (ifAbsent) {
            log.debug("Put [{}] cache result: [{}], original cache wrapper: [{}]",
                key, putCacheWrapper, cacheWrapper);
        }
        return putCacheWrapper;
    }

    @Override
    public void clearCache() {
        CACHE_CONTAINER = null;
    }

    @Override
    public void delete(@NotNull String key) {
        Assert.hasText(key, "Cache key must not be blank");
        CACHE_CONTAINER.remove(key);
        log.debug("Removed key: [{}]", key);
    }

    @Override
    public CacheStoreType getCacheType(){
        return CacheStoreType.DEFAULT;
    }

    private void tryInit(){
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
