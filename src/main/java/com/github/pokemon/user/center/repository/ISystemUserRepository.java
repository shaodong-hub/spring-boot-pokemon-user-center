package com.github.pokemon.user.center.repository;


import com.github.pokemon.user.center.pojo.entity.SystemUserDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <p>
 * 创建时间为 上午10:58 2019/10/18
 * 项目名称 spring-boot-security
 * </p>
 *
 * @author shishaodong
 * @version 0.0.1
 * @since 0.0.1
 */

public interface ISystemUserRepository extends JpaSpecificationExecutor<SystemUserDO>, JpaRepository<SystemUserDO, Long>, QuerydslPredicateExecutor<SystemUserDO> {

    /**
     * 获取当前登录的用户密码（加密过后的密码）
     *
     * @return String
     */
    @Query("SELECT user.password FROM SystemUserDO AS user WHERE user.username = :#{principal.username}")
    String findCurrentPassword();

    /**
     * 更新当前登录用户的密码，会自动加密
     *
     * @param password 密码
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("UPDATE SystemUserDO AS user SET user.password = :password WHERE user.username = :#{principal.username}")
    void updateCurrentPassword(String password);

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return boolean
     */
    boolean existsByUsername(String username);

    /**
     * 判断手机号码是否存在
     *
     * @param phone 手机号码
     * @return boolean
     */
    boolean existsByPhone(String phone);

    /**
     * 获取当前用户的信息
     *
     * @param clz 泛型类型
     * @param <V> 泛型
     * @return v
     */
    @Query("SELECT user FROM SystemUserDO AS user WHERE user.username = :#{principal.username}")
    <V> V findCurrentUser(Class<V> clz);

    /**
     * 查找所有的用户
     *
     * @param pageable 分页
     * @param clz      泛型
     * @param <V>      泛型
     * @return Page
     */
    <V> Page<V> findAllBy(Pageable pageable, Class<V> clz);

    /**
     * 根据用户名查找用户信息
     *
     * @param username 用户名
     * @param <V>      泛型
     * @return v
     */
    <V> Optional<V> findByUsernameIs(String username);

    /**
     * 更新用户最后登录时间id
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("UPDATE SystemUserDO AS user SET user.lastLoginDate = CURRENT_TIMESTAMP WHERE user.username = :#{principal.username}")
    void updateLastLoginDate();


}
