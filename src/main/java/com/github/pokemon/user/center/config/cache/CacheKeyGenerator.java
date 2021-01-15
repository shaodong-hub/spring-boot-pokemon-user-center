package com.github.pokemon.user.center.config.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 * <p>
 * create in 2021/1/14 9:38 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Configuration
public class CacheKeyGenerator {

    @Bean("DefaultGenerator")
    public KeyGenerator getDefaultGenerator() {
        System.out.println("getDefaultGenerator");
        return (target, method, params) -> params;
    }

}
