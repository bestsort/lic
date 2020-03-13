package cn.bestsort.cloud_disk.cache;

import cn.bestsort.cloud_disk.model.enums.CacheType;
import cn.bestsort.cloud_disk.model.enums.StorageType;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 3/12/20 9:48 AM
 */
public interface CacheStoreInterface<K, V> {
    /**
     * get value by key
     * @param key not null
     * @return value
     */
    Optional<V> get(@NotNull K key);

    /**
     * remove all cache to prevent happens OOM on change cache store
     */
    void clearCache();
    /**
     * put value with timeout
     * @param key       not null
     * @param value     not null
     * @param timeout   A positive number less than Long.MAX_VALUE
     * @param timeUnit  timeout unit must not be null
     * @return value
     */
    void put(@NotNull K key,@NotNull V value, long timeout,@NotNull TimeUnit timeUnit);

    /**
     * put value with default timeout
     * @param key       not null
     * @param value     not null
     * @return value
     */
    void put(@NotNull K key,@NotNull V value);

    /**
     * Puts a cache which will be expired if the key is absent.
     * @param key       not null
     * @param value     not null
     * @param timeout   A positive number less than Long.MAX_VALUE
     * @param timeUnit  timeout unit must not be null
     * @return true if the key is absent and the value is set,
     *         false if the key is present before, or null if any other reason
     */
    Boolean pubIfAbsent(@NotNull K key,@NotNull V value, long timeout,@NotNull TimeUnit timeUnit);

    /**
     * delete
     * @param key not null
     * @return success or not
     */
    void delete(@NotNull K key);

    /**
     * get cache type
     * @return CacheType
     */
    CacheType getCacheType();
}