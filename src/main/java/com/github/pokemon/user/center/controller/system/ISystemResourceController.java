package com.github.pokemon.user.center.controller.system;

import com.github.pokemon.common.support.pojo.vo.ResultVO;
import com.github.pokemon.user.center.pojo.vo.ISimpleResourceVO;
import com.github.pokemon.user.center.security.resource.Module;

import java.util.List;

/**
 * TODO
 * <p>
 * create in 2021/1/13 7:26 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Module(resourceName = "资源管理", resourceNote = "资源管理", resourceCode = "manager_resource", parentCode = "manager_center", priority = 3)
public interface ISystemResourceController<V extends ISimpleResourceVO> {

    /**
     * @return List
     */
    @Module(resourceName = "资源列表", resourceNote = "资源列表", resourceCode = "manager_resource_list", parentCode = "manager_resource", priority = 1)
    ResultVO<List<V>> listChildResource(long resourceId);

    /**
     * @return Void
     */
    @Module(resourceName = "更新资源", resourceNote = "更新资源", resourceCode = "manager_resource_put", parentCode = "manager_resource", priority = 2)
    ResultVO<Void> updateResource();

}
