package com.bb.stardium.auth.security.oauth;

import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

/*
    Oauth2 토큰을 발급해 주는 부분
    Token Store은 인메모리 형태 -> 디비(Redis)로 바꿔줘야함
 */
//@Configuration
//@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

//    private final TokenStore tokenStore;
//    private final AuthenticationManager authenticationManager;
//    private final PasswordEncoder passwordEncoder;
//
//    public AuthorizationServerConfig(TokenStore tokenStore, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
//        this.tokenStore = tokenStore;
//        this.authenticationManager = authenticationManager;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void config(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients
//                .inMemory()
//                .withClient("stardium-client")
//                .secret(passwordEncoder.encode("stardium-password"))
//                .authorizedGrantTypes("password",
//                        "authorization_code",
//                        "refresh_token",
//                        "implicit")
//                .scopes("read", "write", "trust")
//                .accessTokenValiditySeconds(1*60*60)
//                .refreshTokenValiditySeconds(6*60*60);
//    }
//
//    @Override
//    public void config(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.tokenStore(tokenStore)
//                .authenticationManager(authenticationManager);
//    }

}
