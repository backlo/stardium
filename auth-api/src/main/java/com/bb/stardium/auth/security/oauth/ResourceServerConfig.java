package com.bb.stardium.auth.security.oauth;

import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

//@Configuration
//@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void config(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.resourceId("resource_id")
//                .stateless(false);
//    }
//
//    @Override
//    public void config(HttpSecurity http) throws Exception {
//        http
//                .anonymous().disable()
//                .authorizeRequests()
//                    .antMatchers("/players/**").authenticated()
//                    .and()
//                .exceptionHandling()
//                    .accessDeniedHandler(new OAuth2AccessDeniedHandler());
//    }
}
