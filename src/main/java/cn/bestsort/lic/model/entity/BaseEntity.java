package cn.bestsort.lic.model.entity;

import cn.bestsort.lic.utils.TimeUtil;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 通用实体字段
 * @author bestsort
 * @version 1.0
 * @date 2020/3/17 下午3:16
 */

@Data
@ToString
@EqualsAndHashCode
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Timestamp gmtCreate;

    @Column
    private Timestamp gmtUpdate;

    @PrePersist
    protected void prePersist(){
        Timestamp now = TimeUtil.now();
        if (gmtCreate == null){
            gmtCreate = now;
        }
        if (gmtUpdate == null){
            gmtUpdate = now;
        }
    }
    @PreUpdate
    protected  void preUpdate(){
        gmtUpdate = TimeUtil.now();
    }
}
