package com.github.pokemon.user.center.pojo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

import static com.github.pokemon.user.center.pojo.common.TableName.SYSTEM_ROLE;
import static com.github.pokemon.user.center.pojo.common.TableName.SYSTEM_ROLE_RESOURCE;


/**
 * <p>
 * 创建时间为 下午3:22 2020/5/10
 * 项目名称 spring-boot-security
 * </p>
 *
 * @author shishaodong
 * @version 0.0.1
 * @since 0.0.1
 */

@Getter
@Setter
@Builder
@ToString(exclude = {"users", "resources"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = SYSTEM_ROLE, indexes = {
        @Index(columnList = "authority", name = "UK_AUTHORITY"),
})
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class SystemRoleDO extends BaseEntity implements GrantedAuthority {

    private static final long serialVersionUID = -3157807413812174641L;

    @Column(name = "role_name", columnDefinition = "VARCHAR(20) COMMENT '角色名称'")
    private String roleName;

    @Column(name = "authority", columnDefinition = "VARCHAR(20) COMMENT '角色编码'")
    private String authority;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<SystemUserDO> users;

    @ManyToMany(targetEntity = SystemResourceDO.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(
            name = SYSTEM_ROLE_RESOURCE,
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")}
    )
    @JsonManagedReference
    private List<SystemResourceDO> resources;

}
