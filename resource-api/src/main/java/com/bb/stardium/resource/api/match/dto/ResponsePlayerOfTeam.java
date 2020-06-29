package com.bb.stardium.resource.api.match.dto;

import com.bb.stardium.chat.domain.player.Player;
import com.bb.stardium.chat.domain.player.PlayerProfileImage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponsePlayerOfTeam {
    private final Long id;
    private final String email;
    private final String nickname;
    private final PlayerProfileImage profile;
    private final String statusMessage;

    @Builder
    public ResponsePlayerOfTeam(Player findPlayer) {
        this.id = findPlayer.getId();
        this.email = findPlayer.getEmail();
        this.nickname = findPlayer.getNickname();
        this.profile = findPlayer.getProfile();
        this.statusMessage = findPlayer.getStatusMessage();
    }
}
