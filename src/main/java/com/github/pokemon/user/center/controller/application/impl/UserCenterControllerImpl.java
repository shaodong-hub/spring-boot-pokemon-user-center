package com.github.pokemon.user.center.controller.application.impl;

import com.github.pokemon.common.support.pojo.vo.ResultVO;
import com.github.pokemon.user.center.controller.application.IUserCenterController;
import com.github.pokemon.user.center.pojo.dto.SimpleUserDTO;
import com.github.pokemon.user.center.pojo.entity.SystemUserDO;
import com.github.pokemon.user.center.pojo.vo.ISimpleUserVO;
import com.github.pokemon.user.center.service.application.IUserCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 * <p>
 * create in 2021/1/13 11:23 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */


@RestController
@RequestMapping("/user/center")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class UserCenterControllerImpl implements IUserCenterController {

    private final IUserCenterService service;

    @GetMapping("/me")
    @Override
    public ResultVO<ISimpleUserVO> currentUser() {
        return ResultVO.success(service.currentUser());
    }

    @PutMapping("/me/{id}")
    @Override
    public ResultVO<Void> updateUserInfo(@PathVariable("id") SystemUserDO systemUser, SimpleUserDTO userInfo) {
        service.updateUserInfo(systemUser, userInfo);
        return ResultVO.success();
    }

    @Override
    public ResultVO<Void> updatePassword() {
        return null;
    }
}
