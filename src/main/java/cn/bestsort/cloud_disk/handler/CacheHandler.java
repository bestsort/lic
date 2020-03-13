package cn.bestsort.cloud_disk.handler;

import cn.bestsort.cloud_disk.cache.CacheStoreInterface;
import cn.bestsort.cloud_disk.model.enums.CacheType;
import cn.bestsort.cloud_disk.model.enums.StorageType;
import cn.bestsort.cloud_disk.service.ActualFileSystemInterface;
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
public class CacheHandler {

    private HashMap<CacheType, CacheStoreInterface> cacheMap = new HashMap<>();

    private static final CacheType DEFAULT_STRATEGY = CacheType.DEFAULT;

    public CacheHandler(ApplicationContext applicationContext){
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

    public CacheStoreInterface getStrategy(CacheType type){
        Assert.notNull(cacheMap.get(CacheType.DEFAULT),"local file storage not found");
        return  cacheMap.getOrDefault(type,cacheMap.get(CacheType.DEFAULT));
    }
}
