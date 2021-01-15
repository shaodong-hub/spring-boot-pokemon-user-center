package com.github.pokemon.user.center.security.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pokemon.common.support.pojo.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AccessDeniedHandler 用来解决认证过的用户访问无权限资源时的异常
 *
 * <p>
 * 创建时间为 下午4:41 2020/3/11
 * 项目名称 phoenix-user-pro-spring-boot-starter
 * </p>
 *
 * @author shishaodong
 * @version 0.0.1
 * @since 0.0.1
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class SystemAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        log.warn("AccessDeniedHandler");
        ResultVO<Void> result = ResultVO.failure("对不起，您无权访问该页面");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(objectMapper.writeValueAsString(result));
        response.getWriter().flush();
    }

}
