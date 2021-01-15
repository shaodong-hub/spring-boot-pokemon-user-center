package com.github.pokemon.user.center.service.system;

import com.github.pokemon.user.center.pojo.dto.SystemRoleQueryDTO;
import com.github.pokemon.user.center.pojo.entity.SystemRoleDO;
import com.github.pokemon.user.center.pojo.vo.ISimpleRoleVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * TODO
 * <p>
 * create in 2021/1/14 9:12 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */


public interface ISystemRoleService<T extends ISimpleRoleVO, E> {


    /**
     * 查找所有的角色
     *
     * @param pageable 分页信息
     * @return Page
     */
    Page<SystemRoleDO> findAllRoles(SystemRoleQueryDTO condition, Pageable pageable);

    /**
     * 创建新用户
     *
     * @param role 用户信息
     */
    void createRole(E role);

    /**
     * 创建新用户
     *
     * @param role 用户信息
     */
    void updateRole(E role);

    /**
     * 根据ID删除用户
     *
     * @param roleId 用户信息
     */
    void deleteRoleById(long roleId);


}
