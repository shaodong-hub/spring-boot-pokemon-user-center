package com.github.pokemon.user.center.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器。如账号密码错误，失败以后会调用这个,如果没有实现会默认使用 SimpleUrlAuthenticationFailureHandler
 * {@link AbstractAuthenticationProcessingFilter#setAuthenticationFailureHandler(org.springframework.security.web.authentication.AuthenticationFailureHandler)}
 *
 * <p>
 * 创建时间为 下午1:36 2019/11/29
 * 项目名称 spring-boot-security
 * </p>
 *
 * @author shishaodong
 * @version 0.0.1
 * @since 0.0.1
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class SystemAuthenticationFailHandlerImpl extends SimpleUrlAuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(exception));
        log.info("-------------------- 登录失败 --------------------");
        super.onAuthenticationFailure(request, response, exception);
    }
}
