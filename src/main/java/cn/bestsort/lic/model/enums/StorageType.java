package cn.bestsort.dubai.model.enums;

public enum StorageType implements ValueEnum<Integer> {
    /**
     * 服务器本地做为文件系统
     */
    DEFAULT(0),

    /**
     * 阿里OSS作为文件系统
     */
    ALI_OSS(1),

    /**
     * 腾讯COS作为文件系统
     */
    TX_COS(2);

    private int value;

    StorageType(int value) {
        this.value = value;
    }

    /**
     * Get enum value.
     *
     * @return enum value
     */
    @Override
    public Integer getValue() {
        return value;
    }
}
