package com.bb.stardium.search.api.dto;

import com.bb.stardium.chat.domain.club.Address;
import com.bb.stardium.chat.domain.club.Club;
import com.bb.stardium.chat.domain.player.Player;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseSearchClub {
    private Long id;
    private String title;
    private String intro;
    private Address address;
    private Integer playersLimit;
    private String startTime;
    private String endTime;
    private ResponseClubMaster master;
    private Integer joinPlayerCount;

    @Builder
    public ResponseSearchClub(Club club) {
        this.id = club.getId();
        this.title = club.getTitle();
        this.intro = club.getIntro();
        this.address = club.getAddress();
        this.playersLimit = club.getPlayersLimit();
        this.startTime = club.getStartTime().toString();
        this.endTime = club.getEndTime().toString();
        this.master = ResponseClubMaster.builder()
                .master(club.getMaster())
                .build();
        this.joinPlayerCount = club.getJoinPlayers().size();
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
