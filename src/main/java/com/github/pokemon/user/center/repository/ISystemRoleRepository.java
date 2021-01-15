package com.github.pokemon.user.center.repository;


import com.github.pokemon.user.center.pojo.entity.SystemRoleDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>
 * 创建时间为 下午3:21 2020/5/10
 * 项目名称 spring-boot-security
 * </p>
 *
 * @author shishaodong
 * @version 0.0.1
 * @since 0.0.1
 */

public interface ISystemRoleRepository extends JpaSpecificationExecutor<SystemRoleDO>, JpaRepository<SystemRoleDO, Long> {

    <V> Page<V> findAllBy(Pageable pageable, Class<V> clz);

    <V> V findById(long id, Class<V> clz);
}
