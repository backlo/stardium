package com.bb.stardium.auth.security.filter.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class PlayerViewModel {
    String password;
    String nickname;

}
