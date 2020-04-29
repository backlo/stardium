package com.bb.stardium.v1.player.dto;

import com.bb.stardium.v1.player.domain.Player;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PlayerResponseDto {
    private Long playerId;
    private String nickname;
    private String email;
    private String statusMessage;
    private String profile;

    @Builder
    public PlayerResponseDto(final Player player) {
        this.playerId = player.getId();
        this.nickname = player.getNickname();
        this.email = player.getEmail();
        this.statusMessage = player.getStatusMessage();
        this.profile = player.getProfile().getProfileUrl();
    }
}
