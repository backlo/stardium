package com.bb.stardium.v1.player.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@Setter
public class PlayerSessionDto implements Serializable {
    private Long playerId;
    private String email;

    @Builder
    public PlayerSessionDto(Long playerId, String email) {
        this.playerId = playerId;
        this.email = email;
    }
}
