package com.github.pokemon.user.center.controller.system.impl;

import com.github.pokemon.common.support.pojo.vo.ResultVO;
import com.github.pokemon.user.center.controller.system.ISystemRoleController;
import com.github.pokemon.user.center.pojo.dto.SimpleRoleDTO;
import com.github.pokemon.user.center.pojo.dto.SystemRoleQueryDTO;
import com.github.pokemon.user.center.pojo.entity.SystemRoleDO;
import com.github.pokemon.user.center.pojo.vo.ISimpleRoleVO;
import com.github.pokemon.user.center.security.resource.Module;
import com.github.pokemon.user.center.service.system.ISystemRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 * <p>
 * create in 2021/1/14 7:25 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */


@RestController
@RequiredArgsConstructor
@RequestMapping("/system/manager")
public class SystemRoleControllerImpl implements ISystemRoleController<ISimpleRoleVO, SimpleRoleDTO> {

    private final ISystemRoleService<ISimpleRoleVO, SimpleRoleDTO> service;

    @GetMapping("roles")
    @Override
    public ResultVO<Page<SystemRoleDO>> findAllRoles(SystemRoleQueryDTO query, Pageable pageable) {
        return ResultVO.success(service.findAllRoles(query, pageable));
    }

    @PostMapping("role")
    @Override
    public ResultVO<Void> createRole(SimpleRoleDTO role) {
        return null;
    }

    @PutMapping("role")
    @Override
    public ResultVO<Void> updateRole(SimpleRoleDTO role) {
        return null;
    }

    @DeleteMapping("role/{id}")
    @Override
    public ResultVO<Void> deleteRoleById(@PathVariable Long id) {
        return null;
    }
}
