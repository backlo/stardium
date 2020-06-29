package com.bb.stardium.search.api.dto;

import com.bb.stardium.chat.domain.club.Club;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResponseSearchClubList {
    private final List<ResponseSearchClub> searchClubs;

    @Builder
    public ResponseSearchClubList(List<Club> searchClubs) {
        this.searchClubs = searchClubs.stream()
                .map(
                        club -> ResponseSearchClub.builder()
                                .club(club)
                                .build()
                ).collect(Collectors.toList());
    }
}
