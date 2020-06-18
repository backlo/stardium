package com.bb.stardium.domain.match;

import com.bb.stardium.domain.club.Club;
import com.bb.stardium.domain.player.Player;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(MatchId.class)
@EqualsAndHashCode(of = {"player", "club"})
public class Match {
    @Id
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Id
    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @Builder
    public Match(Player player, Club club) {
        this.player = player;
        this.club = club;
    }

    public boolean isJoinPlayer(Player player) {
        return this.player.equals(player);
    }

    public boolean isSamePlayerAndClub(Club club, Player authPlayer) {
        return club.equals(this.club) && authPlayer.equals(this.player);
    }
}
