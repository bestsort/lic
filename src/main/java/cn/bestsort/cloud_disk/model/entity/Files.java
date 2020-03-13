package cn.bestsort.cloud_disk.file.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Files)实体类
 *
 * @author bestsort
 * @since 2020-02-29 15:41:23
 */
public class Files implements Serializable {
    private static final long serialVersionUID = -76460313441726488L;
    
    private Long id;
    /**
    * 文件名
    */
    private String name;
    /**
    * 文件类型（目录/文件）
    */
    private int isDir;
    /**
    * 文件url访问父路径（只记录一级）
    */
    private String parentPath;
    /**
    * 文件在磁盘中的真实路径
    */
    private String realPath;
    /**
    * 文件大小
    */
    private Long size;
    /**
    * 计量单位
    */
    private int unit;
    /**
    * md5校验和
    */
    private String md5;
    /**
    * 所属用户Id
    */
    private Long ownerId;
    /**
    * 大于此用户等级的用户可以访问到该文件
    */
    private Integer userReadLimit;
    /**
    * 大于此用户等级的用户可以修改该文件
    */
    private Integer userWriteLimit;
    /**
    * 是否删除,1 表示该文件已经被移入回收站
    */
    private int isRemoved;
    /**
    * 文件创建时间
    */
    private Date gmtCreate;
    /**
    *  修改时间 
    */
    private Date gmtUpdate;


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

    public int getIsDir() {
        return isDir;
    }

    public void setIsDir(int isDir) {
        this.isDir = isDir;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getUserReadLimit() {
        return userReadLimit;
    }

    public void setUserReadLimit(Integer userReadLimit) {
        this.userReadLimit = userReadLimit;
    }

    public Integer getUserWriteLimit() {
        return userWriteLimit;
    }

    public void setUserWriteLimit(Integer userWriteLimit) {
        this.userWriteLimit = userWriteLimit;
    }

    public int getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(int isRemoved) {
        this.isRemoved = isRemoved;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

}