package cn.bestsort.lic.cache;

import cn.bestsort.lic.exception.NotFoundException;
import cn.bestsort.lic.service.OptionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author bestsort
 * @version 1.0
 * @date 3/12/20 9:47 AM
 */

@Slf4j
public abstract class AbstractCacheStore<K, V> implements CacheStoreInterface<K, V> {
    @Resource
    OptionsService optionsService;
    @Resource
    ApplicationEventPublisher applicationEventPublisher;
    /**
     * Puts the cache wrapper.
     * @param value        value must not be null
     * @param key          key must not be null
     */
    abstract void putInternal(@NonNull K key, @NonNull V value);

    /**
     * 删除缓存中的key
     * @param key key
     */
    abstract void deleteInternal(@NonNull K key);

    @Override
    public void delete(@NonNull K key){
        Assert.hasText(key.toString(), "Cache key must not be blank");
        deleteInternal(key);
        optionsService.deleteByKey(key.toString());
    }

    /**
     * remove all cache to prevent happens OOM on change cache store
     */
    @Override
    public abstract void clearCache();

    @Override
    public V get(K key) {
        return get(key, null);
    }

    @Override
    public V getWithNonNull(K key){
        V val = get(key);
        if (val == null){
            throw new NotFoundException(String.format(
                "the key [ %s ] was not found",
                key
            ));
        }
        return val;
    }

    @Override
    public V get(K key, V defaultValue){
        Assert.notNull(key, "Cache key must not be blank");
        Optional<V> optionalV = getInternal(key);
        if (!optionalV.isPresent()){
            optionalV = getFromDb(key);
        }
        return optionalV.orElse(defaultValue);
    }

    /**
     * 从 数据库 中查询
     * @param key option_key
     * @return option_value
     */
    protected abstract Optional<V> getFromDb(K key);

    /**
     * 尝试在缓存中获取 key 对应的 value
     * @param key key
     * @return 缓存中的 value
     */
    public abstract Optional<V> getInternal(K key);

    @Override
    public boolean contain(K key){
        return get(key) != null;
    }

    @Override
    public void put(K key, V value) {
        V oldValue = get(key);
        if (oldValue != null && oldValue.equals(value)){
            return;
        }
        optionsService.inertOrUpdate(key.toString(), value.toString());
        putInternal(key, value);
    }

}
