package com.bb.stardium.player.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class PlayerSessionDto implements Serializable {
    private Long playerId;
    private String email;

    @Builder
    public PlayerSessionDto(Long playerId, String email) {
        this.playerId = playerId;
        this.email = email;
    }
}
