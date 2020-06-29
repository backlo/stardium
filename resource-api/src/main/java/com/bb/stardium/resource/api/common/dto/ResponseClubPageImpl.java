package com.bb.stardium.resource.api.common.dto;

import com.bb.stardium.chat.domain.club.Club;
import com.bb.stardium.resource.api.club.dto.ResponseClub;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

public class ResponseClubPageImpl extends ResponsePage {

    @Builder
    public ResponseClubPageImpl(Page<Club> clubPageInfo) {
        this.content = clubPageInfo.getContent()
                .stream()
                .map(club -> ResponseClub.builder()
                        .club(club)
                        .build()
                ).collect(Collectors.toList());
        this.totalPages = clubPageInfo.getTotalPages();
        this.elementSize = clubPageInfo.getSize();
        this.pageNumber = clubPageInfo.getNumber();
        this.sortEnable = clubPageInfo.getSort().isSorted();
    }
}
