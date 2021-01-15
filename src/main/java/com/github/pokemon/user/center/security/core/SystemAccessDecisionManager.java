package com.github.pokemon.user.center.security.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 生成决策器到容器中会自动生效，
 * <p>
 * create in 2021/1/14 7:00 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class SystemAccessDecisionManager {

    @Bean
    public AffirmativeBased affirmativeBased(List<AccessDecisionVoter<?>> decisionVoters) {
        return new AffirmativeBased(decisionVoters);
    }

    @Bean
    public AccessDecisionVoter<Object> accessDecisionVoter() {
        return new RoleVoter();
    }

}
