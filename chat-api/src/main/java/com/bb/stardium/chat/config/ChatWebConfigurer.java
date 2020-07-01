package com.bb.stardium.chat.config;

import com.bb.stardium.chat.handler.StompHandler;
import com.bb.stardium.chat.resolver.PlayerNicknameArgumentResolver;
import com.bb.stardium.security.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class ChatWebConfigurer implements WebMvcConfigurer {
    private final SecurityService securityService;

    public ChatWebConfigurer(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(playerNicknameArgumentResolver());
    }

    @Bean
    public PlayerNicknameArgumentResolver playerNicknameArgumentResolver() {
        return new PlayerNicknameArgumentResolver(securityService);
    }

    @Bean
    public StompHandler stompHandler() {
        return new StompHandler(securityService);
    }
}
