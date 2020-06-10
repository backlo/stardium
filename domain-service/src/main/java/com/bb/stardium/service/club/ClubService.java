package com.bb.stardium.service.club;

import com.bb.stardium.domain.club.Club;
import com.bb.stardium.domain.club.repository.ClubRepository;
import com.bb.stardium.service.club.dto.ClubDto;
import com.bb.stardium.service.club.exception.NotFoundClubsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClubService {
    private final ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Transactional
    public Club createClub(ClubDto clubDto) {
        Club newClub = Club.builder()
                .clubDto(clubDto)
                .build();

        return clubRepository.save(newClub);
    }

    @Transactional(readOnly = true)
    public Page<Club> findAllClubs(Pageable pageable) {
        return clubRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Club findById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(NotFoundClubsException::new);
    }

}
