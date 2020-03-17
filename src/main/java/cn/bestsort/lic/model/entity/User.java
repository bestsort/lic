package cn.bestsort.lic.model.entity;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
 */
public class User implements Serializable {
    private static final long serialVersionUID = 709672741812657288L;
    
    private Long id;
    /**
    * 用户名
    */
    private String name;
    /**
    * 用户帐号
    */
    private String account;
    /**
    * 用户密码
    */
    private String password;
    /**
    * 用户邮箱
    */
    private String eMail;
    /**
    * 用户网盘容量,-1表示无穷
    */
    private Long totalCapacity;
    /**
    * 用户已使用的容量
    */
    private Long usedCapacity;
    /**
    * 容量单位
    */
    private String capacityUnit;
    /**
    * 用户所属读等级,等级越大权限越大
    */
    private Integer readLevel;
    /**
    * 用户所属写等级
    */
    private Integer writeLevel;
    /**
    * 用户头像地址
    */
    private String avatar;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public Long getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(Long totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public Long getUsedCapacity() {
        return usedCapacity;
    }

    public void setUsedCapacity(Long usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    public String getCapacityUnit() {
        return capacityUnit;
    }

    public void setCapacityUnit(String capacityUnit) {
        this.capacityUnit = capacityUnit;
    }

    public Integer getReadLevel() {
        return readLevel;
    }

    public void setReadLevel(Integer readLevel) {
        this.readLevel = readLevel;
    }

    public Integer getWriteLevel() {
        return writeLevel;
    }

    public void setWriteLevel(Integer writeLevel) {
        this.writeLevel = writeLevel;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}