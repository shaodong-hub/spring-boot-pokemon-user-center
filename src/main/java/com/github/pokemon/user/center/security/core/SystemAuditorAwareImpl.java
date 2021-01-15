package com.github.pokemon.user.center.security.core;

import com.github.pokemon.user.center.pojo.entity.SystemUserDO;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * TODO
 * <p>
 * create in 2021/1/14 9:11 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */


@Configuration
public class SystemAuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public @NotNull Optional<Long> getCurrentAuditor() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (null == securityContext) {
            return Optional.empty();
        }
        Authentication authentication = securityContext.getAuthentication();
        if (null == authentication) {
            return Optional.empty();
        }
        SystemUserDO userDO = (SystemUserDO) authentication.getPrincipal();
        if (null == userDO) {
            return Optional.empty();
        }
        return Optional.ofNullable(userDO.getId());
    }

}
