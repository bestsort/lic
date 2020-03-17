package cn.bestsort.dubai.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户配置表(Options)实体类
 *
 * @author bestsort
 * @since 2020-03-12 09:33:33
 */
@Data
@Entity
@Table //TODO index
@ToString
@EqualsAndHashCode
public class Options {
    @Id
    private Long id;
    
    private String optionKey;
    
    private String optionValue;
    /**
    * 配置所对应的用户id,为0则表示为系统配置
    */
    private Long userId;
    /**
    * 创建时间
    */
    private Date gmtCreate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionKey() {
        return optionKey;
    }

    public void setOptionKey(String optionKey) {
        this.optionKey = optionKey;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }


}