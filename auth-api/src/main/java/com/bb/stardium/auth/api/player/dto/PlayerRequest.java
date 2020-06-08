package com.bb.stardium.auth.api.player.dto;

import com.bb.stardium.service.player.dto.PlayerDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PlayerRequest {

    @NotBlank(message = "email를 적어주세요!")
    private String email;

    @NotBlank(message = "nickname을 적어주세요!")
    private String nickname;

    @NotBlank(message = "password를 적어주세요!")
    private String password;

    private String statusMessage;
    private String profile;

    public PlayerDto toEntity(String encodePassword) {
        return PlayerDto.builder()
                .email(this.email)
                .password(encodePassword)
                .nickname(this.nickname)
                .statusMessage(this.statusMessage)
                .profile(this.profile)
                .build();
    }
}
