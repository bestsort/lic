package cn.bestsort.cloud_disk.file.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * (File)实体类
 *
 * @author makejava
 * @since 2020-02-23 20:04:17
 */
@Data
public class File {
    private long id;
    /**
    * 文件名
    */
    private String name;
    /**
    * 文件类型（目录/文件）
    */
    private boolean isDir;
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
    private long size;
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
    private long ownerId;
    /**
    * 大于此用户等级的用户可以访问到该文件
    */
    private int userReadLimit;
    /**
    * 大于此用户等级的用户可以修改该文件
    */
    private int userWriteLimit;
    /**
    * 是否删除,1 表示该文件已经被移入回收站
    */
    private int isRemoved;
    /**
    * 文件创建时间
    */
    private Timestamp gmtCreate;
}