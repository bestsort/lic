package cn.bestsort.lic.model.entity;

import java.io.Serializable;

/**
 * 分享-文件关系表(ShareMap)实体类
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
 */
public class ShareMap implements Serializable {
    private static final long serialVersionUID = -38167892669528920L;
    
    private Long id;
    
    private Long shareId;
    
    private Long fileId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

}