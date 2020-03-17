package cn.bestsort.lic.model.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 消息表(Message)实体类
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
 */
public class Message implements Serializable {
    private static final long serialVersionUID = -15214213522653209L;
    
    private Long id;
    /**
    * 发送人id,若为0则此条消息为系统消息
    */
    private Long sendFormId;
    /**
    * 接受人id
    */
    private Long sendToId;
    /**
    * 消息具体内容
    */
    private String content;
    /**
    * 消息发送时间
    */
    private Date gmtCreate;
    /**
    * 已阅标记
    */
    private int isRead;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSendFormId() {
        return sendFormId;
    }

    public void setSendFormId(Long sendFormId) {
        this.sendFormId = sendFormId;
    }

    public Long getSendToId() {
        return sendToId;
    }

    public void setSendToId(Long sendToId) {
        this.sendToId = sendToId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

}