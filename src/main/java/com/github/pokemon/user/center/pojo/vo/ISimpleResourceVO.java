package com.github.pokemon.user.center.pojo.vo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpMethod;

import java.util.Collection;


/**
 * TODO
 * <p>
 * create in 2021/1/13 11:49 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@SuppressWarnings("unused")
@JsonPropertyOrder({"id", "resourceName", "resourceCode", "urls", "methods", "priority", "icon", "foreignKeyParentResourceId"})
public interface ISimpleResourceVO {

    /**
     * 获取对应的 ID
     *
     * @return Long
     */
    Long getId();

    /**
     * getResourceName
     *
     * @return String
     */
    String getResourceName();

    /**
     * getResourceCode
     *
     * @return String
     */
    String getResourceCode();

    /**
     * getUrl
     *
     * @return String
     */
    Collection<String> getUrls();

    /**
     * getMethod
     *
     * @return HttpMethod
     */
    Collection<HttpMethod> getMethods();

    /**
     * getPriority
     *
     * @return Integer
     */
    Integer getPriority();

    /**
     * getIcon
     *
     * @return String
     */
    String getIcon();

    /**
     * getForeignKeyParentResourceId
     *
     * @return Long
     */
    Long getForeignKeyParentResourceId();

}
