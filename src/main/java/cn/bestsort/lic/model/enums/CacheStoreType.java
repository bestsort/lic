package cn.bestsort.lic.model.enums;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 3/13/20 8:04 AM
 */
public enum  CacheType {
    /**
     * 使用JVM堆进行缓存
     */
    DEFAULT,
    /**
     * 使用redis进行缓存
     */
    REDIS;
}
