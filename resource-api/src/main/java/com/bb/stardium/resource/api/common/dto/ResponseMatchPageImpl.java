package com.bb.stardium.resource.api.common.dto;

import com.bb.stardium.domain.match.Match;
import com.bb.stardium.resource.api.club.dto.ResponseClub;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

public class ResponseMatchPageImpl extends ResponsePage {

    @Builder
    public ResponseMatchPageImpl(Page<Match> matchPageInfo) {
        this.content = matchPageInfo.getContent()
                .stream()
                .map(club -> ResponseClub.builder()
                        .club(club.getClub())
                        .build()
                ).collect(Collectors.toList());
        this.totalPages = matchPageInfo.getTotalPages();
        this.elementSize = matchPageInfo.getSize();
        this.pageNumber = matchPageInfo.getNumber();
        this.sortEnable = matchPageInfo.getSort().isSorted();
    }
}
