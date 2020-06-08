package com.bb.stardium.auth.security.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class JwtAuthenticationProvider extends DaoAuthenticationProvider {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Authentication Provider : 사용자 인증 ");

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        String tokenUsername = Optional.ofNullable(String.valueOf(token.getPrincipal()))
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Username"));

        String tokenPassword = Optional.ofNullable(String.valueOf(token.getCredentials()))
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Password"));

        UserDetails userDetails = Optional.ofNullable(getUserDetailsService().loadUserByUsername(tokenUsername))
                .orElseThrow(() -> new UsernameNotFoundException("Not Found Email"));

        if (!getPasswordEncoder().matches(tokenPassword, userDetails.getPassword())) {
            throw new BadCredentialsException("비밀번호가 맞지 않습니다.");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        log.info("Authentication provider : 사용자 서포트 ");

        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
