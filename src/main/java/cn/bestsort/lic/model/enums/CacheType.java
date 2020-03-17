package cn.bestsort.dubai.model.enums;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 3/13/20 8:04 AM
 */
public enum  CacheType implements ValueEnum<Integer> {
    /**
     * 使用JVM堆进行缓存
     */
    DEFAULT(0),
    /**
     * 使用redis进行缓存
     */
    REDIS(1);


    private Integer value;


    CacheType(Integer value) {
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
