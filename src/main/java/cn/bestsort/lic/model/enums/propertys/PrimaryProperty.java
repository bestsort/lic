package cn.bestsort.lic.model.enums.propertys;

import cn.bestsort.lic.utils.TimeUtil;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 2020/3/17 下午5:24
 */
public enum  PrimaryProperty implements PropertyEnum {
    /**
     * 是否是第一次使用lic
     */
    IS_FIRST_USED(
        "is_first_used",
        Boolean.class,
        Boolean.toString(true)
    ),
    /**
     * 用户帐号
     */
    USER_ACCOUNT(
        "user_account",
        String.class,
        ""
    ),

    /**
     * 用户密码
     */
    USER_PASSWORD(
        "user_password",
        String.class,
        ""
    ),

    /**
     * 用户邮箱
     */
    USER_EMAIL(
        "user_email",
        String.class,
        ""
    ),

    /**
     * 第一次使用的时间
     */
    BIRTHDAY(
        "lic_birthday",
        String.class,
        TimeUtil.now().toString()
    );

    private String value;
    private String defaultValue;
    private Class<?> type;
    PrimaryProperty(String value, Class<?> type, String defaultValue) {
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
