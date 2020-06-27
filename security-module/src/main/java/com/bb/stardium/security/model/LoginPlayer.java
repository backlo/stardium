package com.bb.stardium.security.model;

import com.bb.stardium.domain.player.PlayerProfileImage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginPlayer {
    private final String email;
    private final String password;
    private final String nickname;
    private final PlayerProfileImage profileImage;
    private final String role;

    @Builder
    public LoginPlayer(String email, String password, String nickname, PlayerProfileImage profileImage, String role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.role = role;
    }
}
