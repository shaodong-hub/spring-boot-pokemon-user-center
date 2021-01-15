package com.github.pokemon.user.center.service.application.impl;

import com.github.pokemon.user.center.pojo.dto.SimpleUserDTO;
import com.github.pokemon.user.center.pojo.entity.SystemUserDO;
import com.github.pokemon.user.center.pojo.vo.ISimpleUserVO;
import com.github.pokemon.user.center.repository.ISystemUserRepository;
import com.github.pokemon.user.center.service.application.IUserCenterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * TODO
 * <p>
 * create in 2021/1/13 11:25 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCenterServiceImpl implements IUserCenterService {

    private final ISystemUserRepository repository;

    @Override
    public ISimpleUserVO currentUser() {
        return repository.findCurrentUser(ISimpleUserVO.class);
    }

    @Override
    public void updateUserInfo(SystemUserDO systemUser, SimpleUserDTO userInfo) {
        repository.save(updateFields(systemUser, userInfo));
    }

    private SystemUserDO updateFields(SystemUserDO systemUser, SimpleUserDTO userInfo) {
        systemUser.setUsername(userInfo.getUsername());
        systemUser.setPhone(userInfo.getPhone());
        systemUser.setEmail(userInfo.getEmail());
        return systemUser;
    }

}
