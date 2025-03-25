package com.pragma.powerup;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.main.lazy-initialization=true",
        "logging.level.root=OFF"
})
class NotificationMessagingMicroserviceAppTests {

    @Test
    void contextLoads() {
        // This test is empty because it is only used to check if the context loads correctly
    }

}
