package com.bb.stardium.service.match;

import com.bb.stardium.domain.club.Club;
import com.bb.stardium.domain.club.exception.PlayerNotExistClubException;
import com.bb.stardium.domain.match.Match;
import com.bb.stardium.domain.match.repository.MatchRepository;
import com.bb.stardium.domain.player.Player;
import com.bb.stardium.service.match.dto.MatchDto;
import com.bb.stardium.service.player.exception.PlayerNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly =  true)
    public List<Player> getMatchTeams(Long id, Player authPlayer) {
        List<Match> findMatchesByClubId = checkMatchInPlayer(authPlayer, matchRepository.findAllByClubId(id));

        return findMatchesByClubId.stream()
                .map(Match::getPlayer)
                .collect(Collectors.toList());
    }

    public Player getPlayerOfTeams(Long id, Long playerId, Player authPlayer) {
        List<Match> findMatchesByClubId = checkMatchInPlayer(authPlayer, matchRepository.findAllByClubId(id));

        return findMatchesByClubId.stream()
                .filter(match -> match.isEqualPlayerId(playerId))
                .findFirst()
                .map(Match::getPlayer)
                .orElseThrow(PlayerNotFoundException::new);
    }

    private List<Match> checkMatchInPlayer(Player authPlayer, List<Match> findMatchesByClubId) {
        boolean flag = findMatchesByClubId.stream()
                .anyMatch(match -> match.isJoinPlayer(authPlayer));

        if (!flag) {
            throw new PlayerNotExistClubException();
        }

        return findMatchesByClubId;
    }
}
