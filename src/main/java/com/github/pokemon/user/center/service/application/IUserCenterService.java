package com.github.pokemon.user.center.service.application;

import com.github.pokemon.user.center.pojo.dto.SimpleUserDTO;
import com.github.pokemon.user.center.pojo.entity.SystemUserDO;
import com.github.pokemon.user.center.pojo.vo.ISimpleUserVO;

/**
 * TODO
 * <p>
 * create in 2021/1/13 11:24 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */


public interface IUserCenterService {

    /**
     * 获取当前用户信息
     *
     * @return ISimpleUserVO
     */
    ISimpleUserVO currentUser();

    /**
     * 更新用户信息
     *
     * @param systemUser 待更新的用户
     * @param userInfo   用户信息
     */
    void updateUserInfo(SystemUserDO systemUser, SimpleUserDTO userInfo);
}


