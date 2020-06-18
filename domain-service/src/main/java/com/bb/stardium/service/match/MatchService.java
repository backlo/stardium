package com.bb.stardium.service.match;

import com.bb.stardium.domain.club.Club;
import com.bb.stardium.service.match.dto.MatchDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatchService {

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
}
