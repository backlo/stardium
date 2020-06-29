package com.bb.stardium.resource.api.match.dto;

import com.bb.stardium.chat.domain.player.Player;
import com.bb.stardium.chat.domain.player.PlayerProfileImage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseOfTeam {
    private final Long id;
    private final String nickname;
    private final PlayerProfileImage profile;

    @Builder
    public ResponseOfTeam(Player player) {
        this.id = player.getId();
        this.nickname = player.getNickname();
        this.profile = player.getProfile();
    }
}
