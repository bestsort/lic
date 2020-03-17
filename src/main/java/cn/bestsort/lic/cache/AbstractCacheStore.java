package cn.bestsort.dubai.cache;

import cn.bestsort.dubai.service.OptionsService;
import cn.bestsort.dubai.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author bestsort
 * @version 1.0
 * @date 3/12/20 9:47 AM
 */

@Slf4j
public abstract class AbstractCacheStore<K, V> implements CacheStoreInterface<K, V> {

    @Resource
    OptionsService optionsService;

    /**
     * get cache wrapper by key
     * @param key not null
     * @return cache wrapper
     */
    @NonNull
    abstract Optional<CacheWrapper<V>> getInternal(@NonNull K key);
    /**
     * Puts the cache wrapper.
     *
     * @param key          key must not be null
     * @param cacheWrapper cache wrapper must not be null
     */
    abstract void putInternal(@NonNull K key, @NonNull CacheWrapper<V> cacheWrapper);

    /**
     * Puts the cache wrapper if the key is absent.
     *
     * @param key          key must not be null
     * @param cacheWrapper cache wrapper must not be null
     * @return true if the key is absent and the value is set, false if the key is present before, or null if any other reason
     */
    abstract Boolean putInternalIfAbsent(@NonNull K key, @NonNull CacheWrapper<V> cacheWrapper);

    /**
     * remove all cache to prevent happens OOM on change cache store
     */
    @Override
    public abstract void clearCache();

    @Override
    public Optional<V> get(K key) {
        Assert.notNull(key, "Cache key must not be blank");

        return getInternal(key).map(cacheWrapper -> {
            // Check expiration
            if (cacheWrapper.getExpireAt() != null && cacheWrapper.getExpireAt().before(TimeUtils.now())) {
                // Expired then delete it
                log.warn("Cache key: [{}] has been expired", key);
                // Delete the key
                delete(key);

                // Return null
                return null;
            }
            return cacheWrapper.getData();
        });
    }

    @Override
    public void put(K key, V value, long timeout, TimeUnit timeUnit) {
        putInternal(key, buildCacheWrapper(value, timeout, timeUnit));
    }

    @Override
    public Boolean pubIfAbsent(@NotNull K key, @NotNull V value, long timeout, @NotNull TimeUnit timeUnit) {
        return putInternalIfAbsent(key, buildCacheWrapper(value, timeout, timeUnit));
    }

    @Override
    public void put(K key, V value) {
        putInternal(key, buildCacheWrapper(value, 0, null));
    }

    /**
     * Builds cache wrapper.
     *
     * @param value    cache value must not be null
     * @param timeout  the key expiry time, if the expiry time is less than 1, the cache won't be expired
     * @param timeUnit timeout unit must
     * @return cache wrapper
     */
    @NonNull
    private CacheWrapper<V> buildCacheWrapper(@NonNull V value, long timeout, @Nullable TimeUnit timeUnit) {
        Assert.notNull(value, "Cache value must not be null");
        Assert.isTrue(timeout >= 0 && timeout < Long.MAX_VALUE,
                "Cache expiration timeout must be bigger than 0 and less than Long.MAX_VALUE");

        Timestamp now = TimeUtils.now();
        Timestamp expireAt = null;

        if (timeout > 0 && timeUnit != null) {
            expireAt = TimeUtils.add(now, timeout, timeUnit);
        }

        // Build cache wrapper
        CacheWrapper<V> cacheWrapper = new CacheWrapper<>();
        cacheWrapper.setCreateAt(now);
        cacheWrapper.setExpireAt(expireAt);
        cacheWrapper.setData(value);

        return cacheWrapper;
    }
}
