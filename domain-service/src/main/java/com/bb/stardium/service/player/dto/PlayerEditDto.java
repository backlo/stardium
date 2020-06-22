package com.bb.stardium.service.player.dto;

import com.bb.stardium.domain.player.Player;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PlayerEditDto {
    private final Player authPlayer;
    private final String password;
    private final String nickname;
    private final String statusMessage;
    private final String profile;

    @Builder
    public PlayerEditDto(Player authPlayer, String password, String nickname,
                         String statusMessage, String profile) {
        this.authPlayer = authPlayer;
        this.password = password;
        this.nickname = nickname;
        this.statusMessage = statusMessage;
        this.profile = profile;
    }
}
