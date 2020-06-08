package com.bb.stardium.auth.api.player;

import com.bb.stardium.auth.api.player.dto.RequestPlayer;
import com.bb.stardium.auth.api.player.dto.ResponsePlayer;
import com.bb.stardium.domain.player.Player;
import com.bb.stardium.service.player.PlayerService;
import com.bb.stardium.service.player.dto.PlayerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerApiController {
    private final PlayerService playerService;

    public PlayerApiController(final PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<ResponsePlayer> registrationPlayer(@RequestBody RequestPlayer requestPlayer,
                                                     @RequestAttribute("encodedPassword") String encodedPassword) {
        PlayerDto playerDto = requestPlayer.toEntity(encodedPassword);

        Player registered = playerService.registrationPlayer(playerDto);
        return ResponseEntity.ok(
                ResponsePlayer.builder()
                        .email(registered.getEmail())
                .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findPlayer(@PathVariable Long id) {
        Player foundPlayer = playerService.findPlayer(id);
        return ResponseEntity.ok(foundPlayer);
    }
}
