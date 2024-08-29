package ru.netology.spring.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.spring.systemProfile.DevProfile;
import ru.netology.spring.systemProfile.ProductionProfile;
import ru.netology.spring.systemProfile.SystemProfile;

@Configuration
public class JavaConfig {
    @Bean
    @ConditionalOnProperty(prefix = "ru.netology.profile",name = "dev", havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(value = "ru.netology.profile.dev",  havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
