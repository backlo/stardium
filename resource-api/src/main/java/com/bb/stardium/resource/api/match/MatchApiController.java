package com.bb.stardium.resource.api.match;

import com.bb.stardium.chat.domain.match.Match;
import com.bb.stardium.chat.domain.player.Player;
import com.bb.stardium.error.exception.IllegalPageFormException;
import com.bb.stardium.resolver.annotation.AuthorizePlayer;
import com.bb.stardium.resource.api.common.dto.RequestPage;
import com.bb.stardium.resource.api.common.dto.ResponseMatchPageImpl;
import com.bb.stardium.resource.api.common.dto.ResponsePage;
import com.bb.stardium.resource.api.match.dto.ResponseMatch;
import com.bb.stardium.resource.api.match.dto.ResponsePlayerOfTeam;
import com.bb.stardium.resource.api.match.dto.ResponseTeams;
import com.bb.stardium.chat.service.club.ClubService;
import com.bb.stardium.chat.service.match.MatchService;
import com.bb.stardium.chat.service.match.dto.MatchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchApiController {

    private final MatchService matchService;
    private final ClubService clubService;

    public MatchApiController(MatchService matchService, ClubService clubService) {
        this.matchService = matchService;
        this.clubService = clubService;
    }

    @GetMapping("/{id}/in")
    public ResponseEntity<ResponseMatch> joinMatch(@PathVariable Long id, @AuthorizePlayer Player authPlayer) {
        MatchDto matchDto = MatchDto.builder()
                .club(clubService.findById(id))
                .player(authPlayer)
                .build();

        Boolean joinFlag = matchService.joinPlayerToClub(matchDto);

        return ResponseEntity.ok(
                ResponseMatch.builder()
                        .flag(joinFlag)
                        .build()
        );
    }

    @GetMapping("/{id}/out")
    public ResponseEntity<ResponseMatch> exitMatch(@PathVariable Long id, @AuthorizePlayer Player authPlayer) {
        MatchDto matchDto = MatchDto.builder()
                .club(clubService.findById(id))
                .player(authPlayer)
                .build();

        Boolean exitFlag = matchService.exitPlayerToClub(matchDto);

        return ResponseEntity.ok(
                ResponseMatch.builder()
                        .flag(exitFlag)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ResponsePage> getPlayerMatchClubs(RequestPage pageable, @AuthorizePlayer Player authPlayer) {
        try {
            Page<Match> matches = matchService.getPlayerClubs(pageable.of(), authPlayer);
            return ResponseEntity.ok(
                    ResponseMatchPageImpl.builder()
                            .matchPageInfo(matches)
                            .build()
            );
        } catch (IllegalArgumentException | PropertyReferenceException e) {
            throw new IllegalPageFormException();
        }
    }

    @GetMapping("/{id}/teams")
    public ResponseEntity<ResponseTeams> getMatchTeam(@PathVariable Long id, @AuthorizePlayer Player authPlayer) {
        List<Player> getTeamPlayers = matchService.getMatchTeams(id, authPlayer);

        return ResponseEntity.ok(
                ResponseTeams.builder()
                        .getTeamPlayers(getTeamPlayers)
                        .build()
        );
    }

    @GetMapping("{id}/teams/{playerId}")
    public ResponseEntity<ResponsePlayerOfTeam> getPlayerOfTeams(@PathVariable Long id,
                                                                 @PathVariable Long playerId,
                                                                 @AuthorizePlayer Player authPlayer) {
        Player findPlayerOfTeams = matchService.getPlayerOfTeams(id, playerId, authPlayer);
        return ResponseEntity.ok(
                ResponsePlayerOfTeam.builder()
                        .findPlayer(findPlayerOfTeams)
                        .build()
        );
    }

}
