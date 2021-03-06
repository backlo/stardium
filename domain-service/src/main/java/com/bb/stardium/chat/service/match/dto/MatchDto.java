package com.bb.stardium.chat.service.match.dto;

import com.bb.stardium.chat.domain.club.Club;
import com.bb.stardium.chat.domain.player.Player;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MatchDto {
    private final Player player;
    private final Club club;

    @Builder
    public MatchDto(Player player, Club club) {
        this.player = player;
        this.club = club;
    }
}
