package com.bb.stardium.api.player.dto;

import com.bb.stardium.domain_service.club.player.domain.Player;
import com.bb.stardium.domain_service.club.util.EscapedCharacters;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerDto {
    private String email;
    private String nickname;
    private String password;
    private String statusMessage;
    private String profile;

    public Player toEntity() {
        return Player.builder()
                .email(EscapedCharacters.of(email))
                .nickname(EscapedCharacters.of(nickname))
                .password(password)
                .build();
    }

}
