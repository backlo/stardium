package com.bb.stardium.chat.service.club;

import com.bb.stardium.chat.domain.club.Club;
import com.bb.stardium.chat.domain.club.Section;
import com.bb.stardium.chat.domain.club.exception.NotAllowSectionException;
import com.bb.stardium.chat.domain.club.repository.ClubRepository;
import com.bb.stardium.chat.domain.player.Player;
import com.bb.stardium.chat.service.club.dto.ClubDto;
import com.bb.stardium.chat.service.club.exception.NotFoundClubsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
    public Page<Club> findAllUnexpiredClubs(Pageable pageable) {
        return clubRepository.findAllByStartTimeIsAfter(LocalDateTime.now(), pageable);
    }

    @Transactional(readOnly = true)
    public Club findById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(NotFoundClubsException::new);
    }

    @Transactional
    public Club editClub(ClubDto clubDto, Long id, Player loginPlayer) {
        Club club = clubRepository.findById(id)
                .orElseThrow(NotFoundClubsException::new);

        return club.checkMasterAndLoginPlayer(loginPlayer)
                .update(clubDto);
    }

    public List<String> findAllSections() {
        return Section.getAllSections();
    }

    @Transactional(readOnly = true)
    public Page<Club> findAllClubsFilterBySection(String section, Pageable pageable) {
        if (!Section.isExistSection(section)) {
            throw new NotAllowSectionException();
        }

        return clubRepository.findAllByAddress_Section(section, pageable);
    }
}
