package com.github.pokemon.user.center.security.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pokemon.common.support.pojo.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthenticationEntryPoint 用来解决匿名用户访问无权限资源时的异常
 *
 * <p>
 * 创建时间为 下午4:11 2020/3/11
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
public class SystemUnauthorizedEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.warn("用户未授权处理器-AuthenticationEntryPoint");
        int status = HttpServletResponse.SC_FORBIDDEN;
        ResultVO<Void> result = ResultVO.failure("对不起，您无权访问该页面");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(status);
        response.getWriter().println(objectMapper.writeValueAsString(result));
        response.getWriter().flush();
    }


}
