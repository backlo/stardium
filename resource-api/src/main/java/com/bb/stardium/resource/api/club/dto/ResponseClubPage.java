package com.bb.stardium.resource.api.club.dto;

import com.bb.stardium.domain.club.Club;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class ResponseClubPage {
    private final List<ResponseClub> content;
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
                .map(club -> ResponseClub.builder()
                            .club(club)
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
}
