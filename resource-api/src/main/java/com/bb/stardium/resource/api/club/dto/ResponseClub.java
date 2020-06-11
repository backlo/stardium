package com.bb.stardium.resource.api.club.dto;

import com.bb.stardium.domain.club.Address;
import com.bb.stardium.domain.club.Club;
import com.bb.stardium.domain.player.Player;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseClub {
    private Long id;
    private String title;
    private String intro;
    private Address address;
    private Integer playerLimit;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ResponseClubMaster master;
    private Integer joinPlayerCount;

    @Builder
    public ResponseClub(Club club) {
        this.id = club.getId();
        this.title = club.getTitle();
        this.intro = club.getIntro();
        this.address = club.getAddress();
        this.playerLimit = club.getPlayersLimit();
        this.startTime = club.getStartTime();
        this.endTime = club.getEndTime();
        this.master = ResponseClubMaster.builder()
                    .master(club.getMaster())
                .build();
        this.joinPlayerCount = club.getPlayers().size();
    }

    @Getter
    private static class ResponseClubMaster {
        private String email;
        private String profileUrl;
        private String nickname;

        @Builder
        public ResponseClubMaster(Player master) {
            this.email = master.getEmail();
            this.profileUrl = master.getProfile().getProfileUrl();
            this.nickname = master.getNickname();
        }

    }
}
