package com.bb.stardium.security.service;

import com.bb.stardium.security.model.AuthenticationPlayer;
import com.bb.stardium.security.model.LoginPlayer;
import com.bb.stardium.security.repository.AuthenticationRepositorySupport;
import com.bb.stardium.security.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class SecurityService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(SecurityService.class);

    private final AuthenticationRepositorySupport authenticationRepositorySupport;
    private final JwtUtil jwtUtil;

    public SecurityService(AuthenticationRepositorySupport authenticationRepositorySupport, JwtUtil jwtUtil) {
        this.authenticationRepositorySupport = authenticationRepositorySupport;
        this.jwtUtil = jwtUtil;
    }

    public String generateToken(AuthenticationPlayer player) {
        return jwtUtil.generateToken(player);
    }

    public String extractEmail(String token) throws IllegalAccessException {
        try {
            return jwtUtil.extractSubject(token);
        } catch (JwtException e) {
            throw new IllegalAccessException(e.getMessage());
        }
    }

    public boolean isTokenExpired(String token) throws IllegalAccessException {
        try {
            return jwtUtil.isTokenExpired(token);
        } catch (JwtException e) {
            throw new IllegalAccessException(e.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            LoginPlayer player = authenticationRepositorySupport.findByEmail(email);
            return new AuthenticationPlayer(player.getEmail(),
                    player.getPassword(),
                    player.getNickname(),
                    player.getProfileImage(),
                    setAuthorities(player.getRole())
            );
        } catch (Exception ex) {
            log.error("Query Error : {}", ex.getMessage());
            throw new BadCredentialsException("이메일이 존재하지 않습니다.");
        }
    }

    private Collection<? extends GrantedAuthority> setAuthorities(String role) {
        return List.of(new SimpleGrantedAuthority(role));
    }

    public String extractNickname(String token) throws IllegalAccessException {
        try {
            return jwtUtil.extractNickname(token);
        } catch (JwtException e) {
            throw new IllegalAccessException(e.getMessage());
        }
    }
}
