package cn.bestsort.lic.model.enums.propertys;

import cn.bestsort.lic.model.enums.CacheType;
import cn.bestsort.lic.model.enums.StorageType;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 2020/3/17 上午10:30
 */
public enum LicProperty implements PropertyEnum {

    /**
     * 所采用的缓存策略(内存模式/Redis)
     */
    CACHE_STRATEGY("lic_cache_strategy", String.class, CacheType.DEFAULT.toString()),

    /**
     * 所采用的文件系统(本地服务器/阿里云OSS)
     */
    STORAGE_STRATEGY("lic_storage_strategy", String.class, StorageType.DEFAULT.toString());
    private final  String value;
    private final Class<?> type;
    private final String defaultValue;

    LicProperty(String value, Class<?> type, String defaultValue) {
        if (!PropertyEnum.isSupportedType(type)) {
            throw new IllegalArgumentException("Unsupported blog property type: " + type);
        }
        this.defaultValue = defaultValue;
        this.value = value;
        this.type = type;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public String defaultValue() {
        return "DEFAULT";
    }


    @Override
    public String getValue() {
        return value;
    }
}
