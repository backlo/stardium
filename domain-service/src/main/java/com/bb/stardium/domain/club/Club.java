package com.bb.stardium.domain.club;

import com.bb.stardium.domain.club.exception.MasterAndClubNotMatchedException;
import com.bb.stardium.domain.match.Match;
import com.bb.stardium.domain.player.Player;
import com.bb.stardium.service.club.dto.ClubDto;
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

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "master_id")
    private Player master;

    @OneToMany(mappedBy = "player",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Match> players = new ArrayList<>();

    @Builder
    public Club(ClubDto clubDto) {
        this.title = clubDto.getTitle();
        this.intro = clubDto.getIntro();
        this.address = clubDto.getAddress();
        this.playersLimit = clubDto.getPlayersLimit();
        this.startTime = clubDto.getStartTime();
        this.endTime = clubDto.getEndTime();
        this.master = clubDto.getMaster();
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
}
