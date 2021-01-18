package com.github.pokemon.user.center.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * TODO
 * <p>
 * create in 2021/1/14 7:46 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@SuppressWarnings("unused")
@JsonDeserialize(as = IAntPathRequestMatcherVO.AntPathRequestMatcherVO.class)
public interface IAntPathRequestMatcherVO extends Serializable {

    /**
     * url 路径
     *
     * @return String
     */
    Collection<String> getUrls();

    /**
     * Http 方法
     *
     * @return HttpMethod
     */
    Collection<HttpMethod> getMethods();

    /**
     * 获取所有的角色
     *
     * @return List
     */
    List<ISimpleRoleVO> getRoles();

    /**
     * 当前资源对应的 AntPathRequestMatcher
     *
     * @return AntPathRequestMatcher
     */
    @Value("#{@systemAntPathRequestMatcher.getAntPathRequestMatcher(target.urls, target.methods)}")
    List<AntPathRequestMatcher> getAntPathRequestMatcher();

    @Getter
    @Setter
    @ToString
    class AntPathRequestMatcherVO implements IAntPathRequestMatcherVO {

        private static final long serialVersionUID = -4172448132004937575L;

        Collection<String> urls;

        /**
         * Http 方法
         */
        Collection<HttpMethod> methods;

        /**
         * 获取所有的角色
         */
        List<ISimpleRoleVO> roles;

        /**
         * 当前资源对应的 AntPathRequestMatcher
         */
        @Value("#{@systemAntPathRequestMatcher.getAntPathRequestMatcher(target.urls, target.methods)}")
        List<AntPathRequestMatcher> antPathRequestMatcher;
    }

}
