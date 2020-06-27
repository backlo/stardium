package com.bb.stardium.auth.handler.dto;

import com.bb.stardium.domain.player.PlayerProfileImage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenResponse {
    private final String token;
    private final String email;
    private final String nickname;
    private final PlayerProfileImage profile;

    @Builder
    public TokenResponse(String token, String email, String nickname, PlayerProfileImage profile) {
        this.token = token;
        this.email = email;
        this.nickname = nickname;
        this.profile = profile;
    }
}