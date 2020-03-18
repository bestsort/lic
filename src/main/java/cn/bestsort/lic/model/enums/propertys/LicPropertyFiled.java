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
     * 默认数据文件夹
     */
    DATA_DIR(
        "data_dir",
        String.class,
        "/data"
    ),
    /**
     * 缓存维持的时间(单位为S, 默认1天, 过期删除)
     */
    CACHE_DURATION(
        "cache_duration",
        Integer.class,
        String.valueOf(60 * 60 * 24)
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
            throw new IllegalArgumentException("Unsupported property type: " + type);
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
