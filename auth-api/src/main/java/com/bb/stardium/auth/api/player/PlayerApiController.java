package com.bb.stardium.auth.api.player;

import com.bb.stardium.auth.api.player.dto.RequestEditPlayer;
import com.bb.stardium.auth.api.player.dto.RequestPlayer;
import com.bb.stardium.auth.api.player.dto.ResponseMyProfile;
import com.bb.stardium.auth.api.player.dto.ResponsePlayer;
import com.bb.stardium.resolver.annotation.AuthorizePlayer;
import com.bb.stardium.domain.player.Player;
import com.bb.stardium.service.player.PlayerService;
import com.bb.stardium.service.player.dto.PlayerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerApiController {
    private static final String ROLE_USER = "ROLE_USER";
    private final PlayerService playerService;

    public PlayerApiController(final PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<ResponsePlayer> registrationPlayer(@RequestBody RequestPlayer requestPlayer,
                                                             @RequestAttribute("encodedPassword") String encodedPassword) {
        PlayerDto playerDto = requestPlayer.toEntity(encodedPassword, ROLE_USER);

        Player registered = playerService.registrationPlayer(playerDto);
        return ResponseEntity.ok(
                ResponsePlayer.builder()
                        .email(registered.getEmail())
                        .build()
        );
    }

    @GetMapping("/profile")
    public ResponseEntity<ResponseMyProfile> getMyProfile(@AuthorizePlayer Player player) {
        return ResponseEntity.ok(
                ResponseMyProfile.builder()
                        .player(player)
                        .build());
    }

    @PutMapping("/profile")
    public ResponseEntity<ResponseMyProfile> editMyProfile(@RequestBody RequestEditPlayer requestEditPlayer,
                                                           @AuthorizePlayer Player player) {

        Player updatedPlayer = playerService.editPlayer(
                requestEditPlayer.toEntity(player)
        );

        return ResponseEntity.ok(
                ResponseMyProfile.builder()
                        .player(updatedPlayer)
                        .build()
        );
    }
}
