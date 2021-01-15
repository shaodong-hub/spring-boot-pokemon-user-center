package com.github.pokemon.user.center.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出登录成功处理器。
 *
 * <p>
 * 创建时间为 下午4:27 2020/3/27
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
public class SystemLogoutSuccessHandler implements LogoutSuccessHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        int status = HttpServletResponse.SC_OK;
        log.info("用户退出登录成功处理器 - SystemLogoutSuccessHandler");
        String result = objectMapper.writeValueAsString(("退出登录成功!"));
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(result);
        response.getWriter().flush();
    }
}
