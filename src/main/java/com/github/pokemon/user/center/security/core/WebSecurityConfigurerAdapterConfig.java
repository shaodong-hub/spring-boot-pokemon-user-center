package com.github.pokemon.user.center.security.core;


import com.github.pokemon.user.center.security.common.SecurityConstant;
import com.github.pokemon.user.center.security.handler.SystemAccessDeniedHandler;
import com.github.pokemon.user.center.security.handler.SystemSessionExpiredStrategy;
import com.github.pokemon.user.center.security.handler.SystemSessionInvalidStrategy;
import com.github.pokemon.user.center.security.handler.SystemUnauthorizedEntryPoint;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;


/**
 * <p>
 * 创建时间为 下午8:34 2019/9/17
 * 项目名称 spring-boot-security
 * </p>
 *
 * @author shishaodong
 * @version 0.0.1
 * @since 0.0.1
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class WebSecurityConfigurerAdapterConfig extends WebSecurityConfigurerAdapter {

    private final LogoutSuccessHandler logoutSuccessHandler;

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationFailureHandler failureHandler;

    private final AuthenticationSuccessHandler successHandler;

    private final SystemAccessDeniedHandler accessDeniedHandler;

    private final SystemSessionInvalidStrategy sessionInvalidStrategy;

    private final SystemSessionExpiredStrategy sessionExpiredStrategy;

    private final SystemUnauthorizedEntryPoint unauthorizedEntryPoint;

    @Override
    protected void configure(@NotNull AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(@NotNull HttpSecurity http) throws Exception {
        // 自定义的登录接口,使用表单登录
        // 自定义登录/登出url，自定义登录成功/失败处理器
        http.formLogin().loginProcessingUrl(SecurityConstant.SYSTEM_LOGIN_USERNAME).permitAll()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()
                .logout()
                .logoutUrl(SecurityConstant.SYSTEM_LOGIN_LOGOUT).permitAll()
                .logoutSuccessHandler(logoutSuccessHandler);
        // 所有的接口均需认证后才能访问
        // 配置自定义决策器，url 数据源
        http.authorizeRequests().anyRequest().authenticated()
                .withObjectPostProcessor(getObjectPostProcessor())
        ;
        // 对 Session 的管理
        http.sessionManagement()
                // 登录超时处理
                .invalidSessionStrategy(sessionInvalidStrategy)
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                // 异地登录处理
                .expiredSessionStrategy(sessionExpiredStrategy);
        http.exceptionHandling()
                .authenticationEntryPoint(unauthorizedEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
//        http.addFilterBefore(imageCaptchaVerifyFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
//        http.apply(smsSecurityConfigurerAdapter);
    }

    @Override
    @SneakyThrows(Exception.class)
    public void configure(@NotNull WebSecurity web) {
        web.ignoring().antMatchers("/");
    }

    @Resource
    private ObjectProvider<AffirmativeBased> accessDecisionManager;

    @Resource
    private ObjectProvider<FilterInvocationSecurityMetadataSource> metadataSource;

    private ObjectPostProcessor<FilterSecurityInterceptor> getObjectPostProcessor() {
        return new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                fsi.setSecurityMetadataSource(metadataSource.getIfAvailable());
                fsi.setAccessDecisionManager(accessDecisionManager.getIfAvailable());
                return fsi;
            }
        };
    }


}
