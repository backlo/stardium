package com.bb.stardium.auth.api.player.dto;

import com.bb.stardium.service.player.dto.PlayerDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerUpdateRequest {
    private String password;
    private String nickname;
    private String statusMessage;
    private String profile;

    public PlayerDto toEntity() {
        return PlayerDto.builder()
                .password(this.password)
                .nickname(this.nickname)
                .statusMessage(this.statusMessage)
                .profile(this.profile)
                .build();
    }
}
