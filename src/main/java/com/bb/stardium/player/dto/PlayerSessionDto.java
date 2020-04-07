package com.bb.stardium.player.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class PlayerSessionDto implements Serializable {
    private Long playerId;

    @Builder
    public PlayerSessionDto(final Long playerId) {
        this.playerId = playerId;
    }
}
