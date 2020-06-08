package com.bb.stardium.auth.security.filter.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginViewModel {
    private String email;
    private String password;
}
