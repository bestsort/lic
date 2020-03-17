package cn.bestsort.lic.model.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Share)实体类
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
 */
public class Share implements Serializable {
    private static final long serialVersionUID = -75580535320116632L;
    
    private Long id;
    /**
    * 随机一段字符,全路径为host/share/{url}
    */
    private String url;
    /**
    * 分享密码
    */
    private String password;
    /**
    * 文件分享过期时间,若结束时间==开始时间则为永久
    */
    private Date endTime;
    /**
    * 文件分享开始时间
    */
    private Date beginTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

}