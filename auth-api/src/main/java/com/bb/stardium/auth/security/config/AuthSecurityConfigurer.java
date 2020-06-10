package com.bb.stardium.auth.security.config;

import com.bb.stardium.auth.security.filter.JwtAuthenticationFilter;
import com.bb.stardium.auth.security.filter.JwtAuthorizationFilter;
import com.bb.stardium.auth.security.filter.PasswordEncoderFilter;
import com.bb.stardium.auth.security.handler.JwtAuthenticationFailureHandler;
import com.bb.stardium.auth.security.handler.JwtAuthenticationSuccessHandler;
import com.bb.stardium.auth.security.provider.JwtAuthenticationProvider;
import com.bb.stardium.security.service.SecurityService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.List;

@Configuration
@EnableWebSecurity
public class AuthSecurityConfigurer extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    private final SecurityService securityService;

    public AuthSecurityConfigurer(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider();
        jwtAuthenticationProvider.setUserDetailsService(securityService);
        jwtAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return jwtAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/players").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/players/**").hasRole("USER")
                .antMatchers("/*").denyAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilter(jwtAuthenticationFilter());
        http.addFilterAfter(jwtAuthorizationFilter(), JwtAuthenticationFilter.class);
    }

    @Bean
    public FilterRegistrationBean passwordEncoderFilter() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new PasswordEncoderFilter(passwordEncoder()));
        bean.setUrlPatterns(
                List.of("/players")
        );

        return bean;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        jwtAuthenticationFilter.setAuthenticationSuccessHandler(jwtAuthenticationSuccessHandler());
        jwtAuthenticationFilter.setAuthenticationFailureHandler(jwtAuthenticationFailureHandler());

        jwtAuthenticationFilter.afterPropertiesSet();
        return jwtAuthenticationFilter;
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(securityService);
    }

    @Bean
    public AuthenticationSuccessHandler jwtAuthenticationSuccessHandler() {
        return new JwtAuthenticationSuccessHandler(securityService);
    }

    @Bean
    public AuthenticationFailureHandler jwtAuthenticationFailureHandler() {
        return new JwtAuthenticationFailureHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
