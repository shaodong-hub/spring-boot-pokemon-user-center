package com.github.pokemon.user.center.pojo.dto;

import com.github.pokemon.user.center.pojo.entity.SystemRoleDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 * TODO
 * <p>
 * create in 2021/1/14 8:21 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class SimpleRoleDTO {

    private String roleName;

    private String authority;

    public SystemRoleDO toSimpleRole() {
        SystemRoleDO user = new SystemRoleDO();
        BeanUtils.copyProperties(user, this);
        return user;
    }

}
