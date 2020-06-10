package com.bb.stardium.security.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class LoginViewModel {
    private String email;
    private String password;

    @Builder
    public LoginViewModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
