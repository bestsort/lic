package cn.bestsort.lic.model.entity;

import cn.bestsort.lic.utils.TimeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.sql.Timestamp;
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
@EqualsAndHashCode(callSuper = true)
public class Options extends BaseEntity{
    
    private String optionKey;
    
    private String optionValue;
    /**
    * 创建时间
    */
    private Timestamp gmtCreate = TimeUtil.now();
}