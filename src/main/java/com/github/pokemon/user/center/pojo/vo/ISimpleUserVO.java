package com.github.pokemon.user.center.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 * <p>
 * create in 2021/1/13 11:21 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@SuppressWarnings("unused")
@JsonDeserialize(as = ISimpleUserVO.SimpleUserVO.class)
public interface ISimpleUserVO extends Serializable {


    /**
     * getUsername
     *
     * @return String
     */
    String getUsername();

    /**
     * getPhone
     *
     * @return String
     */
    String getPhone();

    /**
     * getEmail
     *
     * @return String
     */
    String getEmail();

    /**
     * getRoles
     *
     * @return List
     */
    List<ISimpleRoleVO> getRoles();

    @Getter
    @Setter
    @ToString
    class SimpleUserVO implements ISimpleUserVO {

        private static final long serialVersionUID = -3103630728664723533L;

        private String username;

        private String phone;

        private String password;

        private String email;

        private List<ISimpleRoleVO> roles;

    }

}
