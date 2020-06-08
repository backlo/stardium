package com.bb.stardium.auth.api.player.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponsePlayer {
    private String email;

    @Builder
    public ResponsePlayer(String email) {
        this.email = email;
    }
}
