package com.bb.stardium.resource.api.club;

import com.bb.stardium.domain.club.Club;
import com.bb.stardium.domain.player.Player;
import com.bb.stardium.error.exception.IllegalPageFormException;
import com.bb.stardium.resolver.annotation.AuthorizePlayer;
import com.bb.stardium.resource.api.club.dto.RequestClub;
import com.bb.stardium.resource.api.club.dto.ResponseClub;
import com.bb.stardium.resource.api.club.dto.ResponseSections;
import com.bb.stardium.resource.api.common.dto.RequestPage;
import com.bb.stardium.resource.api.common.dto.ResponseClubPageImpl;
import com.bb.stardium.resource.api.common.dto.ResponsePage;
import com.bb.stardium.service.club.ClubService;
import com.bb.stardium.service.club.dto.ClubDto;
import org.springframework.data.domain.Page;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubApiController {
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
    public ResponseEntity<ResponsePage> getAllClubList(RequestPage pageable) {
        try {
            Page<Club> clubs = clubService.findAllUnexpiredClubs(pageable.of());
            return ResponseEntity.ok(
                    ResponseClubPageImpl.builder()
                            .clubPageInfo(clubs)
                            .build()
            );
        } catch (IllegalArgumentException | PropertyReferenceException e) {
            throw new IllegalPageFormException();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseClub> getClubById(@PathVariable Long id) {
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

    @GetMapping("/sections")
    public ResponseEntity<ResponseSections> getSections() {
        List<String> sections = clubService.findAllSections();

        return ResponseEntity.ok(
                ResponseSections.builder()
                        .sections(sections)
                        .build()
        );
    }

    @GetMapping("/sort")
    public ResponseEntity<ResponsePage> getSortClub(@RequestParam(value = "section") String section, RequestPage pageable) {
        try {
            Page<Club> findAllClubs = clubService.findAllClubsFilterBySection(section, pageable.of());

            return ResponseEntity.ok(
                    ResponseClubPageImpl.builder()
                            .clubPageInfo(findAllClubs)
                            .build()
            );
        } catch (IllegalArgumentException | PropertyReferenceException e) {
            throw new IllegalPageFormException();
        }
    }
}
