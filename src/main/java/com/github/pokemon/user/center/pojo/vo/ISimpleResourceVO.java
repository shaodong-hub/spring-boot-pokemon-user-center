package com.github.pokemon.user.center.pojo.vo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpMethod;

import java.io.Serializable;
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
@JsonDeserialize(as = ISimpleUserVO.SimpleUserVO.class)
public interface ISimpleResourceVO extends Serializable {

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

    @Getter
    @Setter
    @ToString
    class SimpleResourceVO implements ISimpleResourceVO {

        private static final long serialVersionUID = 3518269634500764364L;

        private Long id;

        private String resourceName;

        private Collection<String> urls;

        private Collection<HttpMethod> methods;

        private String resourceCode;

        private Integer priority;

        private String icon;

        private Long foreignKeyParentResourceId;


    }

}
