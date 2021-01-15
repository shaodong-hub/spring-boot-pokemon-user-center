package com.github.pokemon.user.center.service.system.impl;

import com.github.pokemon.user.center.pojo.vo.IAntPathRequestMatcherVO;
import com.github.pokemon.user.center.pojo.vo.ISimpleResourceVO;
import com.github.pokemon.user.center.repository.ISystemResourceRepository;
import com.github.pokemon.user.center.service.system.ISystemResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 * <p>
 * create in 2021/1/14 9:13 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */


@Service
@RequiredArgsConstructor
public class SystemResourceServiceImpl implements ISystemResourceService {

    private final ISystemResourceRepository repository;

    @Override
    public List<ISimpleResourceVO> listChildResource(long id) {
        return repository.findAllByForeignKeyParentResourceIdIs(id, ISimpleResourceVO.class);
    }

    @Override
    public List<IAntPathRequestMatcherVO> findAllAntPathRequestMatcher() {
        return repository.findAllBy(IAntPathRequestMatcherVO.class);
    }
}
