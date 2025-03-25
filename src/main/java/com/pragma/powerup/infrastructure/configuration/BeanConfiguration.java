package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.out.feign.IUserFeignClient;
import com.pragma.powerup.infrastructure.out.jpa.adapter.UserJpaAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserFeignClient userFeignClient;


    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userFeignClient);
    }

}