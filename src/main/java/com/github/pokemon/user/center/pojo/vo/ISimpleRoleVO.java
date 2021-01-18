package com.github.pokemon.user.center.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * TODO
 * <p>
 * create in 2021/1/13 11:49 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@SuppressWarnings("unused")
@JsonDeserialize(as = ISimpleRoleVO.SimpleRoleVO.class)
public interface ISimpleRoleVO extends Serializable {

    /**
     * getRoleName
     *
     * @return String
     */
    String getRoleName();

    /**
     * getAuthority
     *
     * @return String
     */
    String getAuthority();

    @Getter
    @Setter
    @ToString
    class SimpleRoleVO implements ISimpleRoleVO {

        private static final long serialVersionUID = -2583954131919736822L;

        private String roleName;

        private String authority;

    }

}
