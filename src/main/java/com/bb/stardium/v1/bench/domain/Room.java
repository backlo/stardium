package com.bb.stardium.v1.bench.domain;

import com.bb.stardium.v1.player.domain.Player;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Room {
    private static final int EMPTY_SEAT = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    private String title;

    private String intro;

    @Embedded
    private Address address;

    @Future
    private LocalDateTime startTime;

    @Future
    private LocalDateTime endTime;

    private int playersLimit;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id")
    private Player master;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "player_room",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private List<Player> players = new ArrayList<>();


    public boolean isUnexpiredRoom() {
        return this.getStartTime().isAfter(LocalDateTime.now());
    }

    public boolean isReady() {
        return !hasRemainingSeat();
    }

    public boolean hasRemainingSeat() {
        return this.playersLimit - players.size() > EMPTY_SEAT;
    }
}
