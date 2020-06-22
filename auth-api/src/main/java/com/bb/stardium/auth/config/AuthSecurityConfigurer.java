package com.bb.stardium.auth.config;

import com.bb.stardium.auth.filter.JwtAuthenticationFilter;
import com.bb.stardium.auth.filter.PasswordEncoderFilter;
import com.bb.stardium.auth.handler.JwtAuthenticationFailureHandler;
import com.bb.stardium.auth.handler.JwtAuthenticationSuccessHandler;
import com.bb.stardium.auth.handler.JwtLogoutSuccessHandler;
import com.bb.stardium.auth.provider.JwtAuthenticationProvider;
import com.bb.stardium.security.filter.JwtVerifyFilter;
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
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class AuthSecurityConfigurer extends WebSecurityConfigurerAdapter {
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
                .antMatchers(HttpMethod.GET, "/logout").hasRole("USER")
                .antMatchers("/*").denyAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(jwtLogoutSuccessHandler());

        http.addFilter(jwtAuthenticationFilter());
        http.addFilterAfter(authJwtVerifyFilter(), JwtAuthenticationFilter.class);
    }

    @Bean
    public FilterRegistrationBean passwordEncoderFilter() {
        Map<String, String> includeRequest = new HashMap<>();
        includeRequest.put("/players", "POST");
        includeRequest.put("/players/profile", "PUT");

        PasswordEncoderFilter filter = new PasswordEncoderFilter(passwordEncoder(), includeRequest);
        return new FilterRegistrationBean<Filter>(filter);
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
    public JwtVerifyFilter authJwtVerifyFilter() {
        Map<String, String> ignoreUri = new HashMap<>();
        ignoreUri.put("/players", "POST");

        return new JwtVerifyFilter(securityService, ignoreUri);
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
    public LogoutSuccessHandler jwtLogoutSuccessHandler() {
        return new JwtLogoutSuccessHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
