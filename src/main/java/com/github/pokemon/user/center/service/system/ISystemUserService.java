package com.github.pokemon.user.center.service.system;

import com.github.pokemon.user.center.pojo.dto.SimpleUserDTO;
import com.github.pokemon.user.center.pojo.dto.SystemUserQueryDTO;
import com.github.pokemon.user.center.pojo.entity.SystemUserDO;
import com.github.pokemon.user.center.pojo.vo.ISimpleUserVO;
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


public interface ISystemUserService<T extends ISimpleUserVO> {

    /**
     * 按照条件查找所有的用户
     *
     * @param condition 查询条件
     * @param pageable  分页信息
     * @return Page
     */
    Page<SystemUserDO> listUsers(SystemUserQueryDTO condition, Pageable pageable);

    /**
     * 创建用户服务
     *
     * @param user 用户信息
     */
    void createUser(SimpleUserDTO user);

    /**
     * 更新用户信息
     *
     * @param oldData 待更新的用户信息
     * @param newData 要更新的用户信息
     */
    void updateUser(SystemUserDO oldData, SimpleUserDTO newData);

    /**
     * 删除用户信息
     *
     * @param user 用户信息
     */
    void deleteUser(SystemUserDO user);

}
