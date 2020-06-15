package com.bb.stardium.auth.security.filter.dto.core;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class PlayerViewModel {
    protected String email;
    protected String password;
    protected String nickname;
}
