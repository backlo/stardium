package com.bb.stardium.auth.oauth;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Resource(name = "securityService")
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public PasswordEncoder encoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new InMemoryTokenStore();
//    }
//
//    @Bean
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//    @Override
//    protected void security(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(encoder());
//    }
//
//    @Override
//    protected void security(HttpSecurity http) throws Exception {
//        http
//                .cors().disable()
//                .csrf().disable()
//                .anonymous().disable()
//                .authorizeRequests()
//                    .antMatchers("/api-docs/**").permitAll();
//    }
//
//    //Resource Server에도 열어줘야함
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("*"));
//        configuration.setAllowedMethods(List.of("*"));
//        configuration.setAllowedHeaders(List.of("*"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}
