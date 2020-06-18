package com.bb.stardium.resource.api.match;

import com.bb.stardium.domain.player.Player;
import com.bb.stardium.interceptor.annotation.AuthorizePlayer;
import com.bb.stardium.resource.api.match.dto.ResponseMatch;
import com.bb.stardium.service.club.ClubService;
import com.bb.stardium.service.match.MatchService;
import com.bb.stardium.service.match.dto.MatchDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
