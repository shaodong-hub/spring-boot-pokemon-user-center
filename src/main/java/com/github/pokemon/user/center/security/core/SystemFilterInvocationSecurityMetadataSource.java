package com.github.pokemon.user.center.security.core;

import com.github.pokemon.user.center.pojo.vo.ISimpleRoleVO;
import com.github.pokemon.user.center.service.system.ISystemResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * TODO
 * <p>
 * create in 2021/1/14 7:02 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class SystemFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final ISystemResourceService service;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        Collection<ConfigAttribute> configAttributes = service.findAllAntPathRequestMatcher().stream()
                .filter(one -> one.getAntPathRequestMatcher().stream().anyMatch(sin -> sin.matches(fi.getRequest())))
                .flatMap(one -> one.getRoles().stream().map(ISimpleRoleVO::getAuthority))
                .map(one -> (ConfigAttribute) () -> one)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(configAttributes)) {
            return Collections.singleton((ConfigAttribute) () -> "ROLE_ROOT");
        } else {
            configAttributes.add((ConfigAttribute) () -> "ROLE_ROOT");
            return configAttributes;
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
