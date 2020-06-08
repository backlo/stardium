package com.bb.stardium.domain.club;

import com.bb.stardium.domain.match.Match;
import com.bb.stardium.domain.player.Player;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @OneToMany(mappedBy = "player",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Match> players = new ArrayList<>();

    @Builder
    public Club(String title, String intro, Address address, int playersLimit, LocalDateTime startTime, LocalDateTime endTime, Player master) {
        this.title = title;
        this.intro = intro;
        this.address = address;
        this.playersLimit = playersLimit;
        this.startTime = startTime;
        this.endTime = endTime;
        this.master = master;
    }

    public Club update(Club updateClub) {
        this.title = updateClub.getTitle();
        this.intro = updateClub.getIntro();
        this.address = updateClub.getAddress();
        this.startTime = updateClub.getStartTime();
        this.endTime = updateClub.getEndTime();
        this.playersLimit = updateClub.getPlayersLimit();

        return this;
    }

}
