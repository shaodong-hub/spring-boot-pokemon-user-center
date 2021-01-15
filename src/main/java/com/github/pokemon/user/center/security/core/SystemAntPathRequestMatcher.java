package com.github.pokemon.user.center.security.core;

import com.google.common.collect.Lists;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * TODO
 * <p>
 * create in 2021/1/14 7:34 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Component
public class SystemAntPathRequestMatcher {

    private static final List<AntPathRequestMatcher> EMPTY_LIST = Collections.emptyList();

    public List<AntPathRequestMatcher> getAntPathRequestMatcher(Collection<String> urls, Collection<HttpMethod> methods) {
        List<AntPathRequestMatcher> list = Lists.newArrayList();
        if (CollectionUtils.isEmpty(urls) || CollectionUtils.isEmpty(methods)) {
            return EMPTY_LIST;
        }
        for (String url : urls) {
            for (HttpMethod method : methods) {
                list.add(new AntPathRequestMatcher(url, method.name()));
            }
        }
        return list;
    }
}
