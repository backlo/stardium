package com.bb.stardium.chat.domain.match;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
public class MatchId implements Serializable {
    private Long player;
    private Long club;

    public MatchId(Long player, Long club) {
        this.player = player;
        this.club = club;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchId that = (MatchId) o;
        return Objects.equals(player, that.player) &&
                Objects.equals(club, that.club);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, club);
    }
}
