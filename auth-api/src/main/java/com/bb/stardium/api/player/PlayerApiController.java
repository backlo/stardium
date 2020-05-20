package com.bb.stardium.api.player;

import com.bb.stardium.api.player.dto.PlayerDto;
import com.bb.stardium.domain_service.club.player.domain.Player;
import com.bb.stardium.domain_service.club.player.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerApiController {
    private static final Logger log = LoggerFactory.getLogger(PlayerApiController.class);

    
    private final PlayerService playerService;

    public PlayerApiController(final PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<Boolean> registrationPlayer(PlayerDto playerDto) {
        log.info("player info: {}, {}", playerDto.getEmail(), playerDto.getPassword());
        boolean flag = playerService.registrationPlayer(playerDto.toEntity());
        return ResponseEntity.ok(flag);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findPlayer(@PathVariable Long id) {
        Player foundPlayer = playerService.findPlayer(id);
        return ResponseEntity.ok(foundPlayer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id,
                                               @RequestBody PlayerDto playerDto) {
        Player updatedPlayer = playerService.editPlayer(id, playerDto.toEntity());
        return ResponseEntity.ok(updatedPlayer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePlayer(@PathVariable Long id) {
        boolean flag = playerService.withdrawPlayer(id);

        return ResponseEntity.ok(flag);
    }
}
