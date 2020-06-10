package com.bb.stardium.auth.api.player.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponsePlayer {
    private String email;

    @Builder
    public ResponsePlayer(String email) {
        this.email = email;
    }
}
