package com.github.pokemon.user.center.service.system;

import com.github.pokemon.user.center.pojo.dto.SimpleResourceDTO;
import com.github.pokemon.user.center.pojo.entity.SystemResourceDO;
import com.github.pokemon.user.center.pojo.vo.IAntPathRequestMatcherVO;
import com.github.pokemon.user.center.pojo.vo.ISimpleResourceVO;

import java.util.List;

/**
 * TODO
 * <p>
 * create in 2021/1/14 9:12 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */


public interface ISystemResourceService {


    /**
     * 查找这个资源的字菜单
     *
     * @param resourceId 资源的 ID
     * @return List
     */
    List<ISimpleResourceVO> listChildResource(long resourceId);

    void updateResource(SystemResourceDO oldData, SimpleResourceDTO newData);

    /**
     * 查找所有的匹配器
     *
     * @return List
     */
    List<IAntPathRequestMatcherVO> findAllAntPathRequestMatcher();


}
