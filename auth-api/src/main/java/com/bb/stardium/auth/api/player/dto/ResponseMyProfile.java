package com.bb.stardium.auth.api.player.dto;

import com.bb.stardium.chat.domain.player.Player;
import com.bb.stardium.chat.domain.player.PlayerProfileImage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseMyProfile {
    private String email;
    private String nickname;
    private PlayerProfileImage profile;
    private String statusMessage;

    @Builder
    public ResponseMyProfile(Player player) {
        this.email = player.getEmail();
        this.nickname = player.getNickname();
        this.profile = player.getProfile();
        this.statusMessage = player.getStatusMessage();
    }
}
