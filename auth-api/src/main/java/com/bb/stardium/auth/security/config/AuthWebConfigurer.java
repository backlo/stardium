package com.bb.stardium.auth.security.config;

import com.bb.stardium.interceptor.resolver.AuthorizePlayerArgumentResolver;
import com.bb.stardium.service.player.PlayerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class AuthWebConfigurer implements WebMvcConfigurer {

    private final PlayerService playerService;

    public AuthWebConfigurer(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authorizePlayerArgumentResolver());
    }

    @Bean
    public AuthorizePlayerArgumentResolver authorizePlayerArgumentResolver() {
        return new AuthorizePlayerArgumentResolver(playerService);
    }
}
