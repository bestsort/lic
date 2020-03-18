package cn.bestsort.lic.utils;

import cn.bestsort.lic.handler.CacheStoreHandler;
import cn.bestsort.lic.model.enums.propertys.PrimaryProperty;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 2020/3/17 下午7:00
 */
public class UserUtil {
    public static boolean isUserOwn(String account, String password, CacheStoreHandler cacheStoreHandler){
        return cacheStoreHandler.fetchCacheStore().get(PrimaryProperty.USER_ACCOUNT.getValue()).equals(account) &&
            cacheStoreHandler.fetchCacheStore().get(PrimaryProperty.USER_PASSWORD.getValue()).equals(inCode(password));
    }

    public static String inCode(String password){
        return null;
    }
}
