package com.github.pokemon.user.center.security.resource;


import com.github.pokemon.user.center.pojo.entity.SystemRoleDO;
import com.github.pokemon.user.center.pojo.entity.SystemUserDO;
import com.github.pokemon.user.center.repository.ISystemRoleRepository;
import com.github.pokemon.user.center.repository.ISystemUserRepository;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * <p>
 * 创建时间为 下午4:47 2020/5/10
 * 项目名称 spring-boot-security
 * </p>
 *
 * @author shishaodong
 * @version 0.0.1
 * @since 0.0.1
 */

@Slf4j
@Order(1)
@Configuration
@RequiredArgsConstructor
public class SystemContextInitializer2 implements CommandLineRunner {

    private final ISystemUserRepository userRepository;

    private final ISystemRoleRepository roleRepository;

    @Override
    public void run(String... args) {
        SystemRoleDO role = roleRepository.save(getRole());
        SystemUserDO user = getUser();
        user.setRoles(Lists.newArrayList(role));
        userRepository.save(user);
    }

    private SystemRoleDO getRole() {
        return SystemRoleDO.builder().roleName("user").authority("ROLE_USER").build();
    }

    private SystemUserDO getUser() {
        return SystemUserDO.builder().username("USER").phone("18867110337").password(("123456")).email("spring-boot2@qq.com").build();
    }

}
