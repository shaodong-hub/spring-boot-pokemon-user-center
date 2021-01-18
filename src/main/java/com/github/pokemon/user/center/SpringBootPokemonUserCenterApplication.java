package com.github.pokemon.user.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * @author shishaodong
 */
@EnableCaching
@EnableJpaAuditing
@EnableRedisHttpSession
@SpringBootApplication
public class SpringBootPokemonUserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPokemonUserCenterApplication.class, args);
    }

}
