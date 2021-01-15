package com.github.pokemon.user.center.controller.system.impl;

import com.github.pokemon.common.support.pojo.vo.ResultVO;
import com.github.pokemon.user.center.controller.system.ISystemResourceController;
import com.github.pokemon.user.center.pojo.vo.ISimpleResourceVO;
import com.github.pokemon.user.center.service.system.ISystemResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 * <p>
 * create in 2021/1/14 12:04 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */


@RestController
@RequiredArgsConstructor
@RequestMapping("/system/manager")
public class SystemResourceControllerImpl implements ISystemResourceController<ISimpleResourceVO> {

    private final ISystemResourceService service;

    @GetMapping("resources")
    @Override
    public ResultVO<List<ISimpleResourceVO>> listChildResource(@RequestParam(name = "id", defaultValue = "1") long id) {
        return ResultVO.success(service.listChildResource(id));
    }

    @PutMapping("resource")
    @Override
    public ResultVO<Void> updateResource() {
        return null;
    }
}
