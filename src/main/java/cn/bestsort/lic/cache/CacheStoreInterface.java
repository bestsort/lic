package cn.bestsort.lic.cache;

import cn.bestsort.lic.exception.NotFoundException;
import cn.bestsort.lic.model.enums.CacheStoreType;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;

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
    V get(@NotNull K key);

    /**
     * 根据key查找, 结果为 null 时抛出异常
     * @param key  key
     * @return     value
     * @throws     NotFoundException throws when item is null
     */
    V getWithNonNull(@NonNull K key);

    /**
     * 根据 key 获取 value, 若 value 不存在则返回 defaultValue
     * @param key               key
     * @param defaultValue      默认值
     * @return                  v
     */
    V get(@NotNull K key, V defaultValue);
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
