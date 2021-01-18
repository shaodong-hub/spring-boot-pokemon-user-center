package com.github.pokemon.user.center.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.pokemon.user.center.pojo.entity.converter.PasswordConverter;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static com.github.pokemon.user.center.pojo.common.TableName.SYSTEM_USER;
import static com.github.pokemon.user.center.pojo.common.TableName.SYSTEM_USER_ROLE;


/**
 * <p>
 * 创建时间为 下午7:08 2019/9/17
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
@ToString(exclude = {"roles"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = SYSTEM_USER, indexes = {
        @Index(columnList = "username", name = "UK_USERNAME"),
        @Index(columnList = "phone", name = "UK_PHONE"),
        @Index(columnList = "email", name = "UK_EMAIL"),
})
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@SuppressWarnings("unused")
public class SystemUserDO extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = 6949655530047745714L;

    @Column(columnDefinition = "VARCHAR(20) COMMENT '用户名'")
    private String username;

    @Column(columnDefinition = "VARCHAR(20) COMMENT '手机号码'")
    private String phone;

    @JsonIgnore
    @Column(columnDefinition = "VARCHAR(150) COMMENT '密码'")
    @Convert(converter = PasswordConverter.class)
    private String password;

    @Column(columnDefinition = "VARCHAR(50) COMMENT '邮箱'")
    private String email;

    @ManyToMany(targetEntity = SystemRoleDO.class, cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = SYSTEM_USER_ROLE,
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    @JsonManagedReference
    private List<SystemRoleDO> roles;

    @JsonIgnore
    @Column(name = "account_non_expired", columnDefinition = "VARCHAR(100) COMMENT '账户没有过期'")
    private LocalDateTime accountNonExpired;

    @JsonIgnore
    @Column(name = "account_non_locked", columnDefinition = "VARCHAR(100) COMMENT '账户没有被锁定'")
    private LocalDateTime accountNonLocked;

    @JsonIgnore
    @Column(name = "credentials_non_expired", columnDefinition = "VARCHAR(100) COMMENT '凭证没有过期'")
    private LocalDateTime credentialsNonExpired;

    @JsonIgnore
    @Column(name = "last_login_date", columnDefinition = "DATETIME COMMENT '最后登录时间'")
    private LocalDateTime lastLoginDate;

    @JsonIgnore
    @Override
    public Collection<SystemRoleDO> getAuthorities() {
        return roles;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @DomainEvents
    public List<SystemUserDO> domainEvents() {
        return Lists.newArrayList(this);
    }

    @AfterDomainEventPublication
    public void callbackMethod() {
        System.out.println("CallBackMethod");
    }

}
