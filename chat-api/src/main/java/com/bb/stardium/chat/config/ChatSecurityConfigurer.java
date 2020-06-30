package com.bb.stardium.chat.config;

import com.bb.stardium.security.filter.JwtVerifyFilter;
import com.bb.stardium.security.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
@Configuration
public class ChatSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final SecurityService securityService;

    public ChatSecurityConfigurer(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilterAfter(chatJwtVerifyFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public JwtVerifyFilter chatJwtVerifyFilter() {
        Map<String, String> ignoreUri = new HashMap<>();

        return new JwtVerifyFilter(securityService, ignoreUri);
    }
}
