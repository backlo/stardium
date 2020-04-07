package com.bb.stardium.player.dto;

import com.bb.stardium.common.util.EscapedCharacters;
import com.bb.stardium.player.domain.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerRequestDto {
    private Long playerId;
    private String nickname;
    private String email;
    private String password;
    private String statusMessage;

    public Player toEntity() {
        return Player.builder()
                .nickname(EscapedCharacters.of(nickname))
                .email(EscapedCharacters.of(email))
                .password(password)
                .statusMessage(EscapedCharacters.of(statusMessage))
                .build();
    }
}
