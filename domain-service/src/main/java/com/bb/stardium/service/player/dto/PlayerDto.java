package com.bb.stardium.service.player.dto;

import com.bb.stardium.domain.player.PlayerProfileImage;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class PlayerDto {
    private final String email;
    private final String password;
    private final String nickname;
    private final String role;
    private final String statusMessage;
    private final PlayerProfileImage profile;

    @Builder
    public PlayerDto(String email, String password, String nickname, String role, String statusMessage, String profile) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.statusMessage = statusMessage;
        this.profile = convertPlayerProfileImage(profile);
    }

    private PlayerProfileImage convertPlayerProfileImage(String profile) {
        return PlayerProfileImage.builder()
                .profileUrl(profile)
                .build();
    }

    public boolean isEmptyPassword() {
        return StringUtils.isEmpty(this.password);
    }

    public boolean isEmptyNickname() {
        return StringUtils.isEmpty(this.nickname);
    }

    public boolean isEmptyStatusMessage() {
        return StringUtils.isEmpty(this.statusMessage);
    }

    public boolean isEmptyProfile() {
        return StringUtils.isEmpty(this.profile.getProfileUrl());
    }
}
