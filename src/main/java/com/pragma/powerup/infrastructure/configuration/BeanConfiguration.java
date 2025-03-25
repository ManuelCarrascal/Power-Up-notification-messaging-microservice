package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.INotificationServicePort;
import com.pragma.powerup.domain.spi.IMessagePersistencePort;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.domain.usecase.NotificationUseCase;
import com.pragma.powerup.infrastructure.out.feign.IUserFeignClient;
import com.pragma.powerup.infrastructure.out.jpa.adapter.MessageJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.repository.INotificationPinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserFeignClient userFeignClient;
    private final INotificationPinRepository notificationPinRepository;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userFeignClient);
    }


    @Bean
    public IMessagePersistencePort messagePersistencePort() {
        return new MessageJpaAdapter(notificationPinRepository);
    }

    @Bean
    public INotificationServicePort notificationServicePort() {
        return new NotificationUseCase(messagePersistencePort());
    }

}