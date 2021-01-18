package com.github.pokemon.user.center.controller.system;

import com.github.pokemon.common.support.pojo.vo.ResultVO;
import com.github.pokemon.user.center.pojo.dto.SystemUserQueryDTO;
import com.github.pokemon.user.center.pojo.entity.SystemUserDO;
import com.github.pokemon.user.center.pojo.vo.ISimpleUserVO;
import com.github.pokemon.user.center.security.resource.Menu;
import com.github.pokemon.user.center.security.resource.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * TODO
 * <p>
 * create in 2021/1/13 7:26 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Menu({
        @Module(resourceName = "Pokemon", resourceNote = "Pokemon", resourceCode = "root", priority = 1),
        @Module(resourceName = "用户中心", resourceNote = "用户中心", resourceCode = "manager_center", parentCode = "root", priority = 1),
        @Module(resourceName = "用户管理", resourceNote = "用户管理", resourceCode = "manager_user", parentCode = "manager_center", priority = 1)
})
public interface ISystemUserController<T extends ISimpleUserVO, E> {

    /**
     * 分页查找所有的用户
     *
     * @param condition 查询条件
     * @param pageable  分页信息
     * @return Page
     */
    @Module(resourceName = "用户列表", resourceNote = "用户列表", resourceCode = "manager_user_list", parentCode = "manager_user", priority = 1)
    ResultVO<Page<SystemUserDO>> listUsers(SystemUserQueryDTO condition, Pageable pageable);

    /**
     * 创建用户
     *
     * @param user 用户信息
     * @return ResultVO
     */
    @Module(resourceName = "创建用户", resourceNote = "创建用户", resourceCode = "manager_user_post", parentCode = "manager_user", priority = 2)
    ResultVO<Void> createUser(E user);


    /**
     * 创建用户
     *
     * @param user 用户信息
     * @return ResultVO
     */
    @Module(resourceName = "更新用户", resourceNote = "更新用户", resourceCode = "manager_user_put", parentCode = "manager_user", priority = 3)
    ResultVO<Void> updateUser(SystemUserDO userInfo, E user);

    /**
     * 根据用户的 ID 删除用户
     *
     * @param user 用户信息
     * @return ResultVO
     */
    @Module(resourceName = "删除用户", resourceNote = "删除用户", resourceCode = "manager_user_delete", parentCode = "manager_user", priority = 4)
    ResultVO<Void> deleteUserById(SystemUserDO user);
}
