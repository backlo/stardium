package com.bb.stardium.auth.security.filter.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlayerViewModel {
    private String email;
    private String password;
    private String nickname;

    public PlayerViewModel parsePlayerViewModel(String encodePassword) {
        this.password = encodePassword;
        return this;
    }
}