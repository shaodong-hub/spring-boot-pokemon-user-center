package com.github.pokemon.user.center.security.initializer;

import com.github.pokemon.user.center.pojo.entity.SystemResourceDO;
import com.github.pokemon.user.center.repository.ISystemResourceRepository;
import com.github.pokemon.user.center.security.resource.Menu;
import com.github.pokemon.user.center.security.resource.Module;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * TODO
 * <p>
 * create in 2021/1/14 10:41 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */


@Slf4j
@Order(3)
@Component
@RequiredArgsConstructor
public class RequestMappingRunner2 implements CommandLineRunner {

    private final ISystemResourceRepository resourceRepository;

    private final RequestMappingHandlerMapping mapping;

    /**
     * 父ID，自己key，value
     */
    private static final Table<String, String, SystemResourceDO> BASED_TABLE = HashBasedTable.create();

    @Override
    public void run(String... args) throws Exception {
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        map.forEach(this::collect);

        log.info("开始初始化资源!");
        BASED_TABLE.cellSet().forEach(sin -> save(sin.getRowKey(), Objects.requireNonNull(sin.getValue())));
    }

    private SystemResourceDO save(String parentCode, @NotNull SystemResourceDO childResource) {
        if (resourceRepository.existsByResourceCodeEquals(childResource.getResourceCode())) {
            return resourceRepository.findByResourceCodeEquals(childResource.getResourceCode()).orElse(null);
        }
        if (StringUtils.isBlank(parentCode)) {
            log.info("初始化资源: parentCode:{} - {}", parentCode, childResource);
            return resourceRepository.save(childResource);
        }
        if (!resourceRepository.existsByResourceCodeEquals(parentCode)) {
            Map.Entry<String, SystemResourceDO> entry = BASED_TABLE.column(parentCode).entrySet().stream().findFirst().orElse(null);
            assert entry != null;
            SystemResourceDO parentResource = save(entry.getKey(), entry.getValue());
            childResource.setParentResource(parentResource);
            return resourceRepository.save(childResource);
        }
        AtomicReference<SystemResourceDO> result = new AtomicReference<>();
        resourceRepository.findByResourceCodeEquals(parentCode).ifPresent(resourceDO -> {
            childResource.setParentResource(resourceDO);
            result.set(resourceRepository.save(childResource));
        });
        return result.get();
    }

    private void collect(RequestMappingInfo k, HandlerMethod v) {
        // 取出控制器对象
        Class<?> clz = v.getBeanType();
        menu(clz);

        Set<String> urls = k.getPatternsCondition().getPatterns();
        Set<HttpMethod> requestTypes = k.getMethodsCondition().getMethods().stream()
                .map(one -> HttpMethod.valueOf(one.name()))
                .collect(Collectors.toSet());

        Method method = v.getMethod();
        module(method, urls, requestTypes);
    }


    /**
     * 处理类上的注解，类上的注解主要包括 {@link Menu} 和 {@link Module}
     *
     * @param clz 类类型
     */
    private void menu(Class<?> clz) {
        // 类上 @Menu 注解
        Menu menu = AnnotationUtils.findAnnotation(clz, Menu.class);
        if (!Objects.isNull(menu)) {
            for (Module module : menu.value()) {
                SystemResourceDO resource = resource(module);
                BASED_TABLE.put(module.parentCode(), module.resourceCode(), resource);
            }
        }
        // 类上 @Module 注解
        Module module = AnnotationUtils.findAnnotation(clz, Module.class);
        if (!Objects.isNull(module)) {
            SystemResourceDO resource = resource(module);
            BASED_TABLE.put(module.parentCode(), module.resourceCode(), resource);
        }
    }

    /**
     * 处理方法上的注解
     *
     * @param method       方法
     * @param urls         URL 路径
     * @param requestTypes 请求类型
     */
    private void module(Method method, Set<String> urls, Set<HttpMethod> requestTypes) {
        Module module = AnnotationUtils.findAnnotation(method, Module.class);
        if (!Objects.isNull(module)) {
            SystemResourceDO resource = resource(module);
            resource.setUrls(urls);
            resource.setMethods(requestTypes);
            BASED_TABLE.put(module.parentCode(), module.resourceCode(), resource);
        }
    }

    private SystemResourceDO resource(Module module) {
        return SystemResourceDO.builder()
                .resourceName(module.resourceName())
                .resourceCode(module.resourceCode())
                .priority(module.priority())
                .icon(module.icon())
                .build();
    }
}
