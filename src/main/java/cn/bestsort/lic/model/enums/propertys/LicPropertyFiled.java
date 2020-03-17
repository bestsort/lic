package cn.bestsort.lic.model.enums.propertys;

import cn.bestsort.lic.model.enums.CacheStoreType;
import cn.bestsort.lic.model.enums.FileStoreType;
import cn.bestsort.lic.utils.TimeUtil;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 2020/3/17 上午10:30
 */
public enum LicPropertyFiled implements PropertyEnum {

    /**
     * 是否是第一次使用lic
     */
    IS_FIRST_USED(
        "is_first_used",
        Boolean.class,
        Boolean.toString(true)
    ),

    /**
     * 第一次使用的时间
     */
    BIRTHDAY(
        "lic_birthday",
        String.class,
        TimeUtil.now().toString()
    ),

    /**
     * 所采用的缓存策略, 详见{@link CacheStoreType}
     */
    CACHE_STRATEGY(
        "lic_cache_strategy",
        String.class,
        CacheStoreType.DEFAULT.toString()
    ),

    /**
     * 所采用的文件系统, 详见{@link FileStoreType ;}
     */
    STORAGE_STRATEGY(
        "lic_storage_strategy",
        String.class,
        FileStoreType.DEFAULT.toString()
    );

    private final  String value;
    private final Class<?> type;
    private final String defaultValue;

    LicPropertyFiled(String value, Class<?> type, String defaultValue) {
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
        return defaultValue;
    }

    @Override
    public String getValue() {
        return value;
    }
}
