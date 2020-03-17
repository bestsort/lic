package cn.bestsort.lic.cache;

import cn.bestsort.lic.model.enums.CacheStoreType;

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
     * 初始化
     */
    void tryInit();

    boolean contain(K key);
    /**
     * remove all cache to prevent happens OOM on change cache store
     */
    void clearCache();

    /**
     * put value with default timeout
     * @param key       not null
     * @param value     not null
     * @return value
     */
    void put(@NotNull K key,@NotNull V value);

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
    CacheStoreType getCacheType();
}
