package com.bb.stardium.service.player.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PlayerDto {
    private final String email;
    private final String password;
    private final String nickname;
    private final String role;
    private final String statusMessage;
    private final String profile;

    @Builder
    public PlayerDto(String email, String password, String nickname, String role, String statusMessage, String profile) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.statusMessage = statusMessage;
        this.profile = profile;
    }

}
