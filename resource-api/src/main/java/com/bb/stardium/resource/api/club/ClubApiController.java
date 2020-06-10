package com.bb.stardium.resource.api.club;

import com.bb.stardium.domain.club.Club;
import com.bb.stardium.domain.player.Player;
import com.bb.stardium.resource.api.club.dto.RequestClub;
import com.bb.stardium.resource.api.club.dto.RequestClubPage;
import com.bb.stardium.resource.api.club.dto.ResponseClubPage;
import com.bb.stardium.resource.security.annotation.AuthorizePlayer;
import com.bb.stardium.service.club.ClubService;
import com.bb.stardium.service.club.dto.ClubDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clubs")
public class ClubApiController {
    private final ClubService clubService;

    public ClubApiController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody RequestClub requestClub, @AuthorizePlayer Player authPlayer) {
        ClubDto clubDto = requestClub.toEntity(authPlayer);
        Club newClub = clubService.createClub(clubDto);

        return ResponseEntity.ok(newClub);
    }

    @GetMapping
    public ResponseEntity<ResponseClubPage> getAllClubList(RequestClubPage pageable) {
        Page<Club> clubs = clubService.findAllClubs(pageable.of());

        return ResponseEntity.ok(
                ResponseClubPage.builder()
                        .clubs(clubs)
                        .build()
        );
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Club> getClub(@PathVariable Long id) {
//
//    }

}
