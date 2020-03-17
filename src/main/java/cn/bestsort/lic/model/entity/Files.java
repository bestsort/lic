package cn.bestsort.dubai.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;

/**
 * (Files)实体类
 *
 * @author bestsort
 * @since 2020-02-29 15:41:23
 */
@Data
@Entity
@Table //TODO index
@ToString
@EqualsAndHashCode
public class Files implements Serializable {
    private static final long serialVersionUID = -76460313441726488L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    * 是否删除,1 表示该文件已经被移入回收站
    */
    private boolean isRemoved;
    /**
    * 文件创建时间
    */
    private Date gmtCreate;
    /**
    *  修改时间 
    */private Date gmtUpdate;

}