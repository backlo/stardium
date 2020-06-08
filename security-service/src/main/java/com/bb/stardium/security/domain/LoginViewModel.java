package com.bb.stardium.security.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class LoginViewModel {
    private Long id;
    private String email;
    private String password;

    @Builder
    public LoginViewModel(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
