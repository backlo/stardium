package com.bb.stardium.chat.domain.club;

import com.bb.stardium.chat.domain.club.exception.*;
import com.bb.stardium.chat.domain.match.Match;
import com.bb.stardium.chat.domain.player.Player;
import com.bb.stardium.chat.service.club.dto.ClubDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@EqualsAndHashCode(of = "id")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private OffsetDateTime createTime;

    @UpdateTimestamp
    private OffsetDateTime updateTime;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "intro", nullable = false)
    private String intro;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "section", column = @Column(name = "section")),
            @AttributeOverride(name = "detail", column = @Column(name = "detail"))
    })
    private Address address;

    @Column(name = "players_limit", nullable = false)
    private int playersLimit;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id")
    private Player master;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> joinPlayers = new ArrayList<>();

    @Builder
    public Club(ClubDto clubDto) {
        this.title = clubDto.getTitle();
        this.intro = clubDto.getIntro();
        this.address = clubDto.getAddress();
        this.playersLimit = clubDto.getPlayersLimit();
        this.startTime = clubDto.getStartTime();
        this.endTime = clubDto.getEndTime();
        this.master = clubDto.getMaster();
        joinMatch(this.master);
    }

    public Club update(ClubDto editClubDto) {
        this.title = editClubDto.getTitle();
        this.intro = editClubDto.getIntro();
        this.address = editClubDto.getAddress();
        this.startTime = editClubDto.getStartTime();
        this.endTime = editClubDto.getEndTime();
        this.playersLimit = editClubDto.getPlayersLimit();

        return this;
    }

    public Club checkMasterAndLoginPlayer(Player loginPlayer) {
        if (StringUtils.isEmpty(loginPlayer.getEmail()) || this.master != loginPlayer) {
            throw new MasterAndClubNotMatchedException();
        }

        return this;
    }

    public boolean joinMatch(Player authPlayer) {
        Match match = createMatchByPlayerAndClub(authPlayer);

        return joinPlayers.add(match) & authPlayer.addMatch(match);
    }

    private Match createMatchByPlayerAndClub(Player authPlayer) {
        if (playersLimit == joinPlayers.size()) {
            throw new OverLimitPlayersSizeException();
        }

        return Match.builder()
                .player(authPlayer)
                .club(checkJoinPlayer(authPlayer))
                .build();
    }

    private Club checkJoinPlayer(Player authPlayer) {
        if (isJoinPlayer(authPlayer)) {
            throw new PlayerAlreadyJoinClubException();
        }

        return this;
    }

    public Boolean exitMatch(Player authPlayer) {
        Club club = checkExitPlayer(authPlayer);

        Match findMatch = joinPlayers.stream()
                .filter(match -> match.isSamePlayerAndClub(club, authPlayer))
                .findFirst()
                .orElseThrow(NotFoundMatchException::new);

        return joinPlayers.remove(findMatch) & authPlayer.removeMatch(findMatch);
    }

    private Club checkExitPlayer(Player authPlayer) {
        if (!isJoinPlayer(authPlayer)) {
            throw new PlayerNotExistClubException();
        }

        return this;
    }

    private boolean isJoinPlayer(Player authPlayer) {
        return joinPlayers.stream()
                .anyMatch(match -> match.isJoinPlayer(authPlayer));
    }

    public boolean hasRemainingSeat() {
        return this.playersLimit - joinPlayers.size() > 0;
    }
}
