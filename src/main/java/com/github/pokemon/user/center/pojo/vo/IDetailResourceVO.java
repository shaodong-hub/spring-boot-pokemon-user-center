package com.github.pokemon.user.center.pojo.vo;

import java.util.List;

/**
 * TODO
 * <p>
 * create in 2021/1/14 7:08 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@SuppressWarnings("unused")
public interface IDetailResourceVO extends ISimpleResourceVO {

    /**
     * getChildResources
     *
     * @return List
     */
    List<ISimpleResourceVO> getChildResources();

    /**
     * getParentResource
     *
     * @return ISimpleResourceVO
     */
    ISimpleResourceVO getParentResource();

    /**
     * getSystemRoles
     *
     * @return List
     */
    List<ISimpleRoleVO> getRoles();

}
