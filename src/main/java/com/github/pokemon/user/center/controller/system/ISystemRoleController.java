package com.github.pokemon.user.center.controller.system;

import com.github.pokemon.common.support.pojo.vo.ResultVO;
import com.github.pokemon.user.center.pojo.dto.SystemRoleQueryDTO;
import com.github.pokemon.user.center.pojo.entity.SystemRoleDO;
import com.github.pokemon.user.center.pojo.vo.ISimpleRoleVO;
import com.github.pokemon.user.center.security.resource.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

/**
 * TODO
 * <p>
 * create in 2021/1/13 7:26 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Module(resourceName = "角色管理", resourceNote = "角色管理", resourceCode = "manager_role", parentCode = "manager_center", priority = 2)
public interface ISystemRoleController<T extends ISimpleRoleVO, E> {

    /**
     * 查找所有的角色
     *
     * @param pageable 分页信息
     * @return Page
     */
    @Module(resourceName = "角色列表", resourceNote = "角色列表", resourceCode = "manager_role_list", parentCode = "manager_role", priority = 1)
    ResultVO<Page<SystemRoleDO>> findAllRoles(SystemRoleQueryDTO query, @PageableDefault(direction = Sort.Direction.DESC, sort = "createDate") Pageable pageable);

    /**
     * 创建新用户
     *
     * @param role 用户信息
     * @return SystemRoleVO
     */
    @Module(resourceName = "创建角色", resourceNote = "创建角色", resourceCode = "manager_role_post", parentCode = "manager_role", priority = 2)
    ResultVO<Void> createRole(E role);

    /**
     * 创建新用户
     *
     * @param role 用户信息
     * @return SystemRoleVO
     */
    @Module(resourceName = "更新角色", resourceNote = "更新角色", resourceCode = "manager_role_put", parentCode = "manager_role", priority = 3)
    ResultVO<Void> updateRole(SystemRoleDO oldData, E role);

    /**
     * 根据ID删除用户
     *
     * @param role 用户信息
     * @return Void
     */
    @Module(resourceName = "删除角色", resourceNote = "删除角色", resourceCode = "manager_role_delete", parentCode = "manager_role", priority = 4)
    ResultVO<Void> deleteRoleById(SystemRoleDO role);

}
