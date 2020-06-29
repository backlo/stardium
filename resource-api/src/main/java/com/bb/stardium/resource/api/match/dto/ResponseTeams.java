package com.bb.stardium.resource.api.match.dto;

import com.bb.stardium.chat.domain.player.Player;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResponseTeams {
    private final List<ResponseOfTeam> teams;

    @Builder
    public ResponseTeams(List<Player> getTeamPlayers) {
        this.teams = getTeamPlayers.stream()
                .map(
                        player -> ResponseOfTeam.builder()
                                .player(player)
                                .build()
                )
                .collect(Collectors.toList());
    }

}
