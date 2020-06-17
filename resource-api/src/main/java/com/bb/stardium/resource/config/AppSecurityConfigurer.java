package com.bb.stardium.resource.config;

import com.bb.stardium.interceptor.resolver.AuthorizePlayerArgumentResolver;
import com.bb.stardium.security.config.filter.JwtVerifyFilter;
import com.bb.stardium.security.service.SecurityService;
import com.bb.stardium.service.player.PlayerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final SecurityService securityService;
    private final PlayerService playerService;

    public AppSecurityConfigurer(SecurityService securityService, PlayerService playerService) {
        this.securityService = securityService;
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/clubs/**").hasRole("USER")
                .antMatchers("/matches/**").hasRole("USER")
                .antMatchers("/**").denyAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilterAfter(resourceJwtVerifyFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public JwtVerifyFilter resourceJwtVerifyFilter() {
        Map<String, String> ignoreUri = new HashMap<>();

        return new JwtVerifyFilter(securityService, ignoreUri);
    }
}
