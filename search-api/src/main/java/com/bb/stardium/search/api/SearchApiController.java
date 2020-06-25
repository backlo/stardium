package com.bb.stardium.search.api;

import com.bb.stardium.domain.club.Club;
import com.bb.stardium.search.api.dto.ResponseSearchClubList;
import com.bb.stardium.service.search.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchApiController {

    private final SearchService searchService;

    public SearchApiController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public ResponseEntity<ResponseSearchClubList> searchKeyword(@RequestParam(value = "keyword") String keyword) {
        List<Club> searchClubs = searchService.findAllBySearchKeyword(keyword);

        return ResponseEntity.ok(
                ResponseSearchClubList.builder()
                        .searchClubs(searchClubs)
                        .build()
        );
    }
}
