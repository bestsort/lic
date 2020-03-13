package cn.bestsort.cloud_disk.cache;

import cn.bestsort.cloud_disk.model.enums.CacheType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author bestsort
 * @version 1.0
 * @date 3/12/20 10:50 AM
 */
@Slf4j
public class MemoryCacheStore extends AbstractStringCacheStore {

    private static final int CACHE_INIT_SIZE = 32;
    private static ConcurrentHashMap<String, CacheWrapper<String>> CACHE_CONTAINER = new ConcurrentHashMap<>(CACHE_INIT_SIZE);
    private Lock lock = new ReentrantLock();

    @Override
    Optional<CacheWrapper<String>> getInternal(String key) {
        Assert.hasText(key, "key must not be blank");
        return Optional.ofNullable(CACHE_CONTAINER.get(key));
    }

    @Override
    void putInternal(String key, CacheWrapper<String> cacheWrapper) {
        Assert.hasText(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");

        tryInitCacheContainer();

        // Put the cache wrapper
        CacheWrapper<String> putCacheWrapper = CACHE_CONTAINER.put(key, cacheWrapper);

        log.debug("Put [{}] cache result: [{}], original cache wrapper: [{}]",
                key, putCacheWrapper, cacheWrapper);
    }

    @Override
    Boolean putInternalIfAbsent(String key, CacheWrapper<String> cacheWrapper) {
        Assert.hasText(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");

        log.debug("Preparing to put key: [{}], value: [{}]", key, cacheWrapper);
        lock.lock();
        try {
            // Get the value before
            Optional<String> valueOptional = get(key);

            if (valueOptional.isPresent()) {
                log.warn("Failed to put the cache, because the key: [{}] has been present already", key);
                return false;
            }

            // Put the cache wrapper
            putInternal(key, cacheWrapper);
            log.debug("Put successfully");
            return true;
        } finally {
            lock.unlock();
        }
    }


    @Override
    public void clearCache() {
        lock.lock();
        try {
            CACHE_CONTAINER = null;
        }finally {
            lock.unlock();
            lock = null;
        }
    }

    @Override
    public void delete(@NotNull String key) {
        Assert.hasText(key, "Cache key must not be blank");
        log.debug("Removed key: [{}]", key);
    }

    @Override
    public CacheType getCacheType(){
        return CacheType.DEFAULT;
    }

    private void tryInitCacheContainer(){
        // double check
        if (CACHE_CONTAINER == null){
            lock.lock();
            try {
                if (CACHE_CONTAINER == null){
                    CACHE_CONTAINER = new ConcurrentHashMap<>(CACHE_INIT_SIZE);
                }
            }
            finally {
                lock.unlock();
            }
        }
    }
}
