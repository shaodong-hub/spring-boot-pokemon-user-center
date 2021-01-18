package com.github.pokemon.user.center.controller.system.impl;

import com.github.pokemon.common.support.pojo.vo.ResultVO;
import com.github.pokemon.user.center.controller.system.ISystemUserController;
import com.github.pokemon.user.center.pojo.dto.SimpleUserDTO;
import com.github.pokemon.user.center.pojo.dto.SystemUserQueryDTO;
import com.github.pokemon.user.center.pojo.entity.SystemUserDO;
import com.github.pokemon.user.center.pojo.vo.ISimpleUserVO;
import com.github.pokemon.user.center.service.system.ISystemUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * TODO
 * <p>
 * create in 2021/1/14 4:06 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */


@RestController
@RequiredArgsConstructor
@RequestMapping("/system/manager")
public class SystemUserControllerImpl implements ISystemUserController<ISimpleUserVO, SimpleUserDTO> {

    private final ISystemUserService<ISimpleUserVO> service;

    @GetMapping("/users")
    @Override
    public ResultVO<Page<SystemUserDO>> listUsers(SystemUserQueryDTO condition, @PageableDefault(direction = Sort.Direction.DESC, sort = "createDate") Pageable pageable) {
        return ResultVO.success(service.listUsers(condition, pageable));
    }

    @PostMapping("user")
    @Override
    public ResultVO<Void> createUser(@RequestBody SimpleUserDTO user) {
        service.createUser(user);
        return ResultVO.success();
    }

    @PutMapping("user/{id}")
    @Override
    public ResultVO<Void> updateUser(@PathVariable("id") SystemUserDO oldData, @RequestBody SimpleUserDTO newData) {
        service.updateUser(oldData, newData);
        return ResultVO.success();
    }

    @DeleteMapping("/user/{id}")
    @Override
    public ResultVO<Void> deleteUserById(@PathVariable("id") SystemUserDO user) {
        service.deleteUser(user);
        return ResultVO.success();
    }

}
