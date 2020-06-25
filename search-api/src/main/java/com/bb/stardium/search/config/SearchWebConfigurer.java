package com.bb.stardium.search.config;

import com.bb.stardium.resolver.AuthorizePlayerArgumentResolver;
import com.bb.stardium.service.player.PlayerService;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

public class SearchWebConfigurer implements WebMvcConfigurer {

    private final PlayerService playerService;

    public SearchWebConfigurer(PlayerService playerService) {
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
