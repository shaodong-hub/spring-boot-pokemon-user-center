package com.github.pokemon.user.center.repository;


import com.github.pokemon.user.center.pojo.entity.SystemResourceDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
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

public interface ISystemResourceRepository extends JpaSpecificationExecutor<SystemResourceDO>, JpaRepository<SystemResourceDO, Long>, QuerydslPredicateExecutor<SystemResourceDO> {

    /**
     * 根据资源编码判断资源是否存在
     *
     * @param resourceCode 资源编码
     * @return boolean
     */
    boolean existsByResourceCodeEquals(String resourceCode);

    /**
     * 根据资源编码查找对应的资源信息
     *
     * @param resourceCode 资源编码
     * @return Optional
     */
    Optional<SystemResourceDO> findByResourceCodeEquals(String resourceCode);

    /**
     * 查找所有的资源
     *
     * @param clz 待映射ID
     * @param <V> 映射泛型
     * @return List
     */
    <V> List<V> findAllBy(Class<V> clz);


    /**
     * 根据 ID 查找所有的子资源
     *
     * @param resourceId 资源ID
     * @param clz        待映射ID
     * @param <V>        映射泛型
     * @return List
     */
    <V> List<V> findAllByForeignKeyParentResourceIdIs(long resourceId, Class<V> clz);

}
