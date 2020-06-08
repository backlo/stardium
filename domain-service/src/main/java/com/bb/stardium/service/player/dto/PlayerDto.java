package com.bb.stardium.service.player.dto;

import com.bb.stardium.domain.player.Player;
import com.bb.stardium.domain.player.PlayerProfileImage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PlayerDto {
    private final String email;
    private final String password;
    private final String nickname;
    private final String statusMessage;
    private final PlayerProfileImage profile;

    @Builder
    public PlayerDto(String email, String password, String nickname, String statusMessage, String profile) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.statusMessage = statusMessage;
        this.profile = convertPlayerProfileImage(profile);
    }

    private PlayerProfileImage convertPlayerProfileImage(String profile) {
        return PlayerProfileImage.builder()
                .profileUrl(profile)
                .build();
    }

    public Player toEntity() {
        return Player.builder()
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .build();
    }
}
