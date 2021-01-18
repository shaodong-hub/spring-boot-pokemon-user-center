package com.github.pokemon.user.center.pojo.dto;

import com.github.pokemon.user.center.pojo.entity.SystemResourceDO;
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
public class SimpleResourceDTO {




    public SystemResourceDO toSystemResource() {
        SystemResourceDO user = new SystemResourceDO();
        BeanUtils.copyProperties(user, this);
        return user;
    }

}
