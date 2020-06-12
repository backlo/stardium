package com.bb.stardium.resource.api.club;

import com.bb.stardium.domain.club.Club;
import com.bb.stardium.domain.player.Player;
import com.bb.stardium.error.exception.IllegalPageFormException;
import com.bb.stardium.resource.api.club.dto.RequestClub;
import com.bb.stardium.resource.api.club.dto.RequestClubPage;
import com.bb.stardium.resource.api.club.dto.ResponseClub;
import com.bb.stardium.resource.api.club.dto.ResponseClubPage;
import com.bb.stardium.resource.security.annotation.AuthorizePlayer;
import com.bb.stardium.service.club.ClubService;
import com.bb.stardium.service.club.dto.ClubDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clubs")
public class ClubApiController {
    private static final Logger log = LoggerFactory.getLogger(ClubApiController.class);
    private final ClubService clubService;

    public ClubApiController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping
    public ResponseEntity<ResponseClub> createClub(@RequestBody RequestClub requestClub,
                                                   @AuthorizePlayer Player authPlayer) {
        ClubDto clubDto = requestClub.toEntity(authPlayer);
        Club newClub = clubService.createClub(clubDto);

        return ResponseEntity.ok(
                ResponseClub.builder()
                        .club(newClub)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ResponseClubPage> getAllClubList(RequestClubPage pageable) {
        try {
            Page<Club> clubs = clubService.findAllClubs(pageable.of());
            return ResponseEntity.ok(
                    ResponseClubPage.builder()
                            .clubs(clubs)
                            .build()
            );
        } catch (IllegalArgumentException e) {
            throw new IllegalPageFormException();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseClub> getClubById(@PathVariable Long id) {
        log.info("들어옴");
        Club club = clubService.findById(id);

        return ResponseEntity.ok(
                ResponseClub.builder()
                        .club(club)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseClub> editClub(@PathVariable Long id, @AuthorizePlayer Player player,
                                                 @RequestBody RequestClub requestClub) {
        ClubDto editClubDto = requestClub.toEntity();
        Club editedClub = clubService.editClub(editClubDto, id, player);

        return ResponseEntity.ok(
                ResponseClub.builder()
                        .club(editedClub)
                        .build()
        );
    }


}
