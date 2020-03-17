package cn.bestsort.lic.cache;

import cn.bestsort.lic.listener.event.CacheEvent;
import cn.bestsort.lic.service.OptionsService;
import cn.bestsort.lic.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
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
    ApplicationEventPublisher applicationEventPublisher;
    /**
     * Puts the cache wrapper.
     * @param value        value must not be null
     * @param key          key must not be null
     */
    abstract void putInternal(@NonNull K key, @NonNull V value);

    /**
     * remove all cache to prevent happens OOM on change cache store
     */
    @Override
    public abstract void clearCache();

    @Override
    public Optional<V> get(K key) {
        Assert.notNull(key, "Cache key must not be blank");
        return getInternal(key);
    }

    /**
     * 尝试在缓存中获取 key 对应的 value
     * @param key key
     * @return 缓存中的 value
     */
    public abstract Optional<V> getInternal(K key);

    @Override
    public boolean contain(K key){
        if (get(key).isPresent()){
            return true;
        }
        return false;
    }
    @Override
    public void put(K key, V value) {
        Optional<V> oldValue = get(key);
        if (oldValue.isPresent() && oldValue.get().equals(value)){
            return;
        }
        applicationEventPublisher.publishEvent(new CacheEvent(this, key.toString(), value.toString()));
        putInternal(key, value);
    }

}
