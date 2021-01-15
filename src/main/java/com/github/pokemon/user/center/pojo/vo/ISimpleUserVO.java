package com.github.pokemon.user.center.pojo.vo;

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
public interface ISimpleUserVO {

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

}
