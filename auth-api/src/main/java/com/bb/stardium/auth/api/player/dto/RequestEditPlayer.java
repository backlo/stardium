package com.bb.stardium.auth.api.player.dto;

import com.bb.stardium.domain.player.Player;
import com.bb.stardium.service.player.dto.PlayerEditDto;
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

    public PlayerEditDto toEntity(Player player) {
        return PlayerEditDto.builder()
                .authPlayer(player)
                .password(checkRequestField(this.password, player.getPassword()))
                .nickname(checkRequestField(this.nickname, player.getNickname()))
                .statusMessage(checkRequestField(this.statusMessage, player.getStatusMessage()))
                .build();
    }

    private String checkRequestField(String requestField, String playerField) {
        return StringUtils.isEmpty(requestField) ? playerField : requestField;
    }
}
