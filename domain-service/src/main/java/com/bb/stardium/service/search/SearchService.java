package com.bb.stardium.service.search;

import com.bb.stardium.domain.club.Club;
import com.bb.stardium.domain.club.repository.ClubRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SearchService {
    private final ClubRepository clubRepository;

    public SearchService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Transactional(readOnly = true)
    public List<Club> findAllBySearchKeyword(String keyword) {
        return clubRepository.findAllByTitleContainingOrIntroContaining(keyword, keyword);
    }
}
