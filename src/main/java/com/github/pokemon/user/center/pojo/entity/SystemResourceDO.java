package com.github.pokemon.user.center.pojo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.http.HttpMethod;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import static com.github.pokemon.user.center.pojo.common.TableName.SYSTEM_RESOURCE;

/**
 * TODO
 * <p>
 * create in 2021/1/13 10:06 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */


@Getter
@Setter
@Builder
@ToString(exclude = {"childResources", "parentResource", "roles"})
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = SYSTEM_RESOURCE, indexes = {
        @Index(columnList = "resource_name", name = "IDX_RESOURCE_NAME"),
        @Index(columnList = "resource_code", name = "IDX_RESOURCE_CODE"),
})
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name = "JSON", typeClass = JsonStringType.class)
public class SystemResourceDO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1101310665812124141L;

    @Column(name = "resource_name", nullable = false, length = 100, columnDefinition = "VARCHAR(100) COMMENT '资源名称'")
    private String resourceName;

    @Type(type = "JSON")
    @Column(name = "urls", columnDefinition = "JSON COMMENT '资源URL'")
    private Collection<String> urls;

    @Type(type = "JSON")
    @Column(name = "methods", columnDefinition = "JSON COMMENT '资源请求方式'")
    private Collection<HttpMethod> methods;

    @Column(name = "resource_code", nullable = false, length = 100, columnDefinition = "VARCHAR(100) COMMENT '资源唯一名称'")
    private String resourceCode;

    @Column(nullable = false, columnDefinition = "INT(1) DEFAULT 0 COMMENT '菜单优先级'")
    private Integer priority;

    @Column(columnDefinition = "VARCHAR(100) COMMENT 'ICON 的路径'")
    private String icon;

    @Column(name = "foreign_key_parent_resource_id", insertable = false, updatable = false, columnDefinition = "BIGINT COMMENT '父资源的ID'")
    private Long foreignKeyParentResourceId;

    @JsonManagedReference
    @OrderBy("priority ASC")
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "parentResource")
    private List<SystemResourceDO> childResources;

    @JsonBackReference
    @ManyToOne(targetEntity = SystemResourceDO.class, cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "foreign_key_parent_resource_id", referencedColumnName = "id")
    private SystemResourceDO parentResource;

    @JsonBackReference
    @ManyToMany(mappedBy = "resources", fetch = FetchType.LAZY)
    private List<SystemRoleDO> roles;

}
