package com.bb.stardium.resource.security.config;

import com.bb.stardium.resource.security.filter.JwtTokenVerifyFilter;
import com.bb.stardium.resource.security.resolver.AuthorizationArgumentResolver;
import com.bb.stardium.security.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebSecurity
public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final AuthorizationArgumentResolver authorizationArgumentResolver;
    private final SecurityService securityService;

    public AppSecurityConfigurer(AuthorizationArgumentResolver authorizationArgumentResolver, SecurityService securityService) {
        this.authorizationArgumentResolver = authorizationArgumentResolver;
        this.securityService = securityService;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authorizationArgumentResolver);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/clubs/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilterAfter(jwtTokenParsingFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public JwtTokenVerifyFilter jwtTokenParsingFilter() {
        return new JwtTokenVerifyFilter(securityService);
    }
}
