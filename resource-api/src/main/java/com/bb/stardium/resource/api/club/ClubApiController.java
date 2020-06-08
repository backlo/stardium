package com.bb.stardium.resource.api.club;

import com.bb.stardium.domain.club.Club;
import com.bb.stardium.domain.player.Player;
import com.bb.stardium.resource.api.club.dto.ClubRequest;
import com.bb.stardium.resource.config.annotation.AuthorizePlayer;
import com.bb.stardium.service.club.ClubService;
import com.bb.stardium.service.club.dto.ClubDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public ResponseEntity<Club> createClub(@RequestBody ClubRequest clubRequest, @AuthorizePlayer Player authPlayer) {

        ClubDto clubDto = clubRequest.toEntity(authPlayer);
        Club newClub = clubService.createClub(clubDto);

        return ResponseEntity.ok(newClub);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Club> getClub(@PathVariable Long id) {
//
//    }

}
