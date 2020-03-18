package cn.bestsort.lic.handler;

import cn.bestsort.lic.cache.CacheStoreInterface;
import cn.bestsort.lic.model.enums.CacheStoreType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author bestsort
 * @version 1.0
 * @date 3/12/20 9:09 PM
 */

@Slf4j
@Component
public class CacheStoreHandler {

    private HashMap<CacheStoreType, CacheStoreInterface> cacheMap = new HashMap<>();

    private static CacheStoreType STRATEGY = null;

    public CacheStoreHandler(ApplicationContext applicationContext){
        addFileHandlers(applicationContext.getBeansOfType(CacheStoreInterface.class).values());
    }


    /**
     * @param cacheHandlers can be null
     */
    @NonNull
    public void addFileHandlers(@Nullable Collection<CacheStoreInterface> cacheHandlers){
        if (!CollectionUtils.isEmpty(cacheHandlers)){
            for (CacheStoreInterface cacheHandler : cacheHandlers) {
                this.cacheMap.put(cacheHandler.getCacheType(), cacheHandler);
            }
        }
    }

    public void setStrategy(CacheStoreType strategy){
        setStrategy(strategy, strategy != STRATEGY);
    }

    public void setStrategy(CacheStoreType strategy, boolean isChanged){
        Assert.notNull(strategy, "storage must be not null");
        if (isChanged){
            fetchCacheStore().clearCache();
        }
        STRATEGY = strategy;
        fetchCacheStore().tryInit();
    }

    public String getStrategy(){
        return STRATEGY.toString();
    }

    public CacheStoreInterface fetchCacheStore(){
        Assert.notNull(cacheMap.get(STRATEGY),"cache storage not found");
        return  cacheMap.get(STRATEGY);
    }
}
