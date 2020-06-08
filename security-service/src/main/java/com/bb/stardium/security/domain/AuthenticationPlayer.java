package com.bb.stardium.security.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Getter
public class AuthenticationPlayer implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private Collection<? extends GrantedAuthority> authorities;

    public AuthenticationPlayer(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isEnabled = true;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
    }

    public String getRoles() {
        try {
            return authorities.stream()
                    .findFirst()
                    .orElseThrow(IllegalAccessException::new)
                    .getAuthority();
        } catch (IllegalAccessException e) {
            return "ROLE_DENY";
        }
    }
}
