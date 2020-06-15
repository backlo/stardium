package com.bb.stardium.auth.security.handler.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LogoutResponse {
    private String flag;

    @Builder
    public LogoutResponse(String flag) {
        this.flag = flag;
    }
}
