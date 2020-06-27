package com.bb.stardium.security.model;

import com.bb.stardium.domain.player.PlayerProfileImage;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class AuthenticationPlayer implements UserDetails {
    private final String username;
    private final String password;
    private final String nickname;
    private final PlayerProfileImage profile;
    private final boolean isEnabled;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final Collection<? extends GrantedAuthority> authorities;

    @Builder
    public AuthenticationPlayer(String username, String password, String nickname, PlayerProfileImage profile, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.profile = profile;
        this.authorities = authorities;
        this.isEnabled = true;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
    }
}
