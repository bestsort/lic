package cn.bestsort.lic.model.enums.propertys;

import cn.bestsort.lic.model.support.LicConst;

/**
 * 本地文件存储相关配置
 * @author bestsort
 * @version 1.0
 * @date 2020/3/17 下午8:47
 */
public enum  LocalFileProperties implements FilePropertyEnum,PropertyEnum{
    /**
     * 本地存储路径, 默认为{项目部署路径}/data
     */
    LOCAL_DATA_DIR("local_data_dir", String.class, LicConst.USER_NOW_DIR + "/data");
    private final String value;
    private final String defaultValue;
    private final Class<?> type;
    LocalFileProperties(String value, Class<?> type, String defaultValue) {
        this.defaultValue = defaultValue;
        if (!PropertyEnum.isSupportedType(type)) {
            throw new IllegalArgumentException("Unsupported blog property type: " + type);
        }

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

    @Override
    public String getDataDir(boolean isDefault) {
        return isDefault ?
            LOCAL_DATA_DIR.defaultValue :
            LOCAL_DATA_DIR.value;
    }
}
