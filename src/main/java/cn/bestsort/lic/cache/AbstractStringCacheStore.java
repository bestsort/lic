package cn.bestsort.lic.cache;

import java.util.Optional;

/**
 * @author bestsort
 * @version 1.0
 * @date 3/12/20 8:35 PM
 */
public abstract class AbstractStringCacheStore extends AbstractCacheStore<String, String>{
    @Override
    public Optional<String> getFromDb(String key){
        return Optional.ofNullable(optionsService.queryValueByKey(key));
    }
}
