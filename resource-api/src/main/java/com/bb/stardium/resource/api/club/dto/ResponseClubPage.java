package com.bb.stardium.resource.api.club.dto;

import com.bb.stardium.domain.club.Address;
import com.bb.stardium.domain.club.Club;
import com.bb.stardium.domain.player.Player;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class ResponseClubPage {
    private final List<ResponseClubList> content;
    private final Pageable pageable;
    private final Integer totalPages;
    private final Long totalElements;
    private final Boolean last;
    private final Integer size;
    private final Integer numberOfElements;
    private final Boolean first;
    private final Integer number;
    private final Sort sort;
    private final Boolean empty;

    @Builder
    public ResponseClubPage(Page<Club> clubs) {
        this.content = clubs.getContent()
                .stream()
                .filter(Objects::nonNull)
                .map(club -> ResponseClubList.builder()
                        .id(club.getId())
                        .title(club.getTitle())
                        .intro(club.getIntro())
                        .address(club.getAddress())
                        .playerLimit(club.getPlayersLimit())
                        .startTime(club.getStartTime())
                        .endTime(club.getEndTime())
                        .master(club.getMaster())
                        .joinPlayerCount(club.getPlayers().size())
                        .build()
                ).collect(Collectors.toList());
        this.pageable = clubs.getPageable();
        this.totalPages = clubs.getTotalPages();
        this.totalElements = clubs.getTotalElements();
        this.last = clubs.isLast();
        this.size = clubs.getSize();
        this.numberOfElements = clubs.getNumberOfElements();
        this.first = clubs.isFirst();
        this.number = clubs.getNumber();
        this.sort = clubs.getSort();
        this.empty = clubs.isEmpty();
    }

    @Getter
    private static class ResponseClubList {
        private Long id;
        private String title;
        private String intro;
        private Address address;
        private Integer playerLimit;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private ResponseClubListMaster master;
        private Integer joinPlayerCount;

        @Builder
        public ResponseClubList(Long id, String title, String intro, Address address,
                                Integer playerLimit, LocalDateTime startTime, LocalDateTime endTime,
                                Player master, Integer joinPlayerCount) {
            this.id = id;
            this.title = title;
            this.intro = intro;
            this.address = address;
            this.playerLimit = playerLimit;
            this.startTime = startTime;
            this.endTime = endTime;
            this.master = ResponseClubListMaster.builder()
                    .email(master.getEmail())
                    .nickname(master.getNickname())
                    .profileUrl(master.getProfile().getProfileUrl())
                    .build();
            this.joinPlayerCount = joinPlayerCount;
        }

        @Getter
        private static class ResponseClubListMaster {
            private String email;
            private String profileUrl;
            private String nickname;

            @Builder
            public ResponseClubListMaster(String email, String profileUrl, String nickname) {
                this.email = email;
                this.profileUrl = profileUrl;
                this.nickname = nickname;
            }

        }
    }
}
