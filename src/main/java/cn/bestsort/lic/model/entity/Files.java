package cn.bestsort.lic.model.entity;

import cn.bestsort.lic.model.enums.FileStoreType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@EqualsAndHashCode(callSuper = true)
public class Files extends BaseEntity {
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
     * 文件所使用的文件系统
     */
    private FileStoreType storeType;
}