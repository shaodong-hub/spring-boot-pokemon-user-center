package com.github.pokemon.user.center.controller.application;

import com.github.pokemon.common.support.pojo.vo.ResultVO;
import com.github.pokemon.user.center.pojo.dto.SimpleUserDTO;
import com.github.pokemon.user.center.pojo.entity.SystemUserDO;
import com.github.pokemon.user.center.pojo.vo.ISimpleUserVO;

/**
 * TODO
 * <p>
 * create in 2021/1/13 7:25 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */


public interface IUserCenterController {

    /**
     * 获取当前用户信息
     *
     * @return ResultVO
     */
    ResultVO<ISimpleUserVO> currentUser();

    /**
     * 更新用户信息
     *
     * @param systemUser 用户信息
     * @param userInfo   用户信息
     * @return Void
     */
    ResultVO<Void> updateUserInfo(SystemUserDO systemUser, SimpleUserDTO userInfo);


    ResultVO<Void> updatePassword();
}
