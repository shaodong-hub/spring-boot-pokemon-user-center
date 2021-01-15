package com.github.pokemon.user.center.security.password;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.pokemon.user.center.security.common.SecurityConstant.POST;
import static com.github.pokemon.user.center.security.common.SecurityConstant.SYSTEM_LOGIN_USERNAME;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;

/**
 * @author shishaodong
 * @date 2020-06-23 17:39
 * @since 1.0
 */

@Slf4j
@Component
@Order(Integer.MIN_VALUE)
@ConditionalOnBean(ILoginPasswordDecoder.class)
public class PasswordDecodeProcessingFilter extends OncePerRequestFilter {

    private final AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher(SYSTEM_LOGIN_USERNAME, POST);

    @Resource
    private ILoginPasswordDecoder passwordDecoder;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain)
            throws ServletException, IOException {
        if (requestMatcher.matches(request) && (null != passwordDecoder)) {
            filterChain.doFilter(new SecurityHttpServletRequestWrapper(request, passwordDecoder), response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private static class SecurityHttpServletRequestWrapper extends HttpServletRequestWrapper {

        private final HttpServletRequest request;

        private final ILoginPasswordDecoder passwordDecoder;

        SecurityHttpServletRequestWrapper(HttpServletRequest request, ILoginPasswordDecoder passwordDecoder) {
            super(request);
            this.request = request;
            this.passwordDecoder = passwordDecoder;
        }

        @Override
        public String getParameter(String name) {
            String value = super.getParameter(name);
            return StringUtils.equalsIgnoreCase(SPRING_SECURITY_FORM_PASSWORD_KEY, name) ? decodePassword(value) : value;
        }

        private String decodePassword(String password) {
            log.debug("进入密码解密方法 # RsaDecoderFilter # position to edit pass.");
            return passwordDecoder.decode(request, password);
        }
    }

}
