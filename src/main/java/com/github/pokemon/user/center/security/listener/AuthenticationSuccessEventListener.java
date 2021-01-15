package com.github.pokemon.user.center.security.listener;


import com.github.pokemon.user.center.repository.ISystemUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * 用户登录成功以后更新最后登录时间
 * <p>
 * 创建时间为 下午11:19 2020/5/12
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
public class AuthenticationSuccessEventListener {

    private final ISystemUserRepository repository;

    @EventListener(value = InteractiveAuthenticationSuccessEvent.class)
    public void authenticationSuccessEvent() {
        repository.updateLastLoginDate();
    }

}
