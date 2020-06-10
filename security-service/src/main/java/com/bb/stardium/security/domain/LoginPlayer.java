package com.bb.stardium.security.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class LoginPlayer {
    private String email;
    private String password;
    private String role;

    @Builder
    public LoginPlayer(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
