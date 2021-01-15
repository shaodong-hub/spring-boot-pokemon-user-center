package com.github.pokemon.user.center.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * TODO
 * <p>
 * create in 2021/1/13 7:28 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */


@Getter
@Setter
@MappedSuperclass
@Where(clause = "deleted = false or deleted is null")
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -2191753221757519036L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(columnDefinition = "VARCHAR(100) COMMENT '备注'")
    protected String note;

    @JsonIgnore
    @Column(columnDefinition = "INT(1) DEFAULT 0 COMMENT '是否为预置'")
    protected Boolean preset;

    @JsonIgnore
    @Column(columnDefinition = "INT(1) DEFAULT 1 COMMENT '是否启用'")
    protected Boolean enabled;

    @JsonIgnore
    @Column(columnDefinition = "INT(1) DEFAULT 0 COMMENT '改记录是否删除'")
    protected Boolean deleted;

    @JsonIgnore
    @Version
    @Column(columnDefinition = "BIGINT DEFAULT 0 COMMENT '改记录是否删除'")
    protected Long version;

    @CreatedDate
    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date createDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date lastModifiedDate;

    @CreatedBy
    @Column(name = "create_by")
    protected String createBy;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    protected String lastModifiedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SystemRoleDO that = (SystemRoleDO) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId().hashCode());
    }
}
