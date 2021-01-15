//package com.github.pokemon.user.center.security.initializer;
//
//import com.github.pokemon.user.center.pojo.entity.SystemResourceDO;
//import com.github.pokemon.user.center.repository.ISystemResourceRepository;
//import com.github.pokemon.user.center.security.resource.Menu;
//import com.github.pokemon.user.center.security.resource.Module;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
//import java.lang.reflect.Method;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * TODO
// * <p>
// * create in 2021/1/14 10:41 下午
// *
// * @author shishaodong
// * @version 0.0.1
// */
//
//
//@Slf4j
//@Order(3)
//@Component
//@RequiredArgsConstructor
//public class RequestMappingRunner implements CommandLineRunner {
//
//    private final ISystemResourceRepository repository;
//
//    private final RequestMappingHandlerMapping mapping;
//
//    private static final Map<String, SystemResourceDO> PARENT_MAP = Maps.newHashMap();
//
//    private static final Map<String, SystemResourceDO> RESOURCE_MAP = Maps.newHashMap();
//
//    @Override
//    public void run(String... args) throws Exception {
//        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
//        map.forEach(this::collect);
//        PARENT_MAP.forEach((k, v) -> {
//            SystemResourceDO resource = RESOURCE_MAP.get(k);
//            if (Objects.nonNull(resource)) {
//                List<SystemResourceDO> childResource = resource.getChildResources();
//                if (null == childResource) {
//                    resource.setChildResources(Lists.newArrayList(v));
//                } else {
//                    childResource.add(v);
//                }
//            }
//        });
//        saveResource(RESOURCE_MAP.get("root"));
//    }
//
//    private void saveResource(SystemResourceDO root) {
//        SystemResourceDO resource = repository.save(root);
//        root.getChildResources().forEach(one -> {
//            one.setParentResource(resource);
//            saveResource(one);
//        });
//    }
//
//    private void collect(RequestMappingInfo k, HandlerMethod v) {
//        // 取出控制器对象
//        Class<?> clz = v.getBeanType();
//        menu(clz);
//
//        Set<String> urls = k.getPatternsCondition().getPatterns();
//        Set<HttpMethod> requestTypes = k.getMethodsCondition().getMethods().stream()
//                .map(one -> HttpMethod.valueOf(one.name()))
//                .collect(Collectors.toSet());
//
//        Method method = v.getMethod();
//        module(method, urls, requestTypes);
//    }
//
//
//    private void menu(Class<?> clz) {
//        Menu menu = AnnotationUtils.findAnnotation(clz, Menu.class);
//        if (!Objects.isNull(menu)) {
//            for (Module module : menu.value()) {
//                SystemResourceDO resource = resource(module);
//                PARENT_MAP.put(module.parentCode(), resource);
//                RESOURCE_MAP.put(module.resourceCode(), resource);
//            }
//        }
//
//        Module module = AnnotationUtils.findAnnotation(clz, Module.class);
//        if (!Objects.isNull(module)) {
//            SystemResourceDO resource = resource(module);
//            PARENT_MAP.put(module.parentCode(), resource);
//            RESOURCE_MAP.put(module.resourceCode(), resource);
//        }
//    }
//
//    private void module(Method method, Set<String> urls, Set<HttpMethod> requestTypes) {
//        Module module = AnnotationUtils.findAnnotation(method, Module.class);
//        if (!Objects.isNull(module)) {
//            SystemResourceDO resource = resource(module);
//            resource.setUrls(urls);
//            resource.setMethods(requestTypes);
//            PARENT_MAP.put(module.parentCode(), resource);
//            RESOURCE_MAP.put(module.resourceCode(), resource);
//        }
//    }
//
//    private SystemResourceDO resource(Module module) {
//        return SystemResourceDO.builder()
//                .resourceName(module.resourceName())
//                .resourceCode(module.resourceCode())
//                .priority(module.priority())
//                .icon(module.icon())
//                .build();
//    }
//}
