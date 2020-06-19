package com.bb.stardium.service.match;

import com.bb.stardium.domain.club.Club;
import com.bb.stardium.domain.match.Match;
import com.bb.stardium.domain.match.repository.MatchRepository;
import com.bb.stardium.domain.player.Player;
import com.bb.stardium.service.match.dto.MatchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatchService {
    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Transactional
    public Boolean joinPlayerToClub(MatchDto matchDto) {
        Club matchClub = matchDto.getClub();

        return matchClub.joinMatch(matchDto.getPlayer());
    }

    @Transactional
    public Boolean exitPlayerToClub(MatchDto matchDto) {
        Club matchClub = matchDto.getClub();

        return matchClub.exitMatch(matchDto.getPlayer());
    }

    @Transactional(readOnly =  true)
    public Page<Match> getPlayerClubs(Pageable pageable, Player authPlayer) {
        return matchRepository.findAllByPlayerId(pageable, authPlayer.getId());
    }
}
