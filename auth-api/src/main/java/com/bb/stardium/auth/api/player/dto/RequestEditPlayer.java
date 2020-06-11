package com.bb.stardium.auth.api.player.dto;

import com.bb.stardium.domain.player.Player;
import com.bb.stardium.service.player.dto.PlayerDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
@NoArgsConstructor
public class RequestEditPlayer {
    private String password;
    private String nickname;
    private String statusMessage;
    private String profile;

    public PlayerDto toEntity(Player player) {
        return PlayerDto.builder()
                .email(player.getEmail())
                .password(checkRequestField(this.password, player.getPassword()))
                .nickname(checkRequestField(this.nickname, player.getNickname()))
                .statusMessage(checkRequestField(this.statusMessage, player.getStatusMessage()))
                .profile(checkRequestField(this.profile, player.getProfile().getProfileUrl()))
                .build();
    }

    private String checkRequestField(String requestField, String playerField) {
        return StringUtils.isEmpty(requestField) ? playerField: requestField;
    }
}
