package com.bb.stardium.auth.api.player;

import com.bb.stardium.auth.api.player.dto.PlayerRequest;
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
    private static final Logger log = LoggerFactory.getLogger(PlayerApiController.class);

    private final PlayerService playerService;

    public PlayerApiController(final PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<Player> registrationPlayer(@RequestBody PlayerRequest playerRequest,
                                                     @RequestAttribute("encodedPassword") String encodedPassword) {
        PlayerDto playerDto = playerRequest.toEntity(encodedPassword);

        Player registered = playerService.registrationPlayer(playerDto);
        log.info("password : {}", registered.getPassword());
        return ResponseEntity.ok(registered);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findPlayer(@PathVariable Long id) {
        Player foundPlayer = playerService.findPlayer(id);
        return ResponseEntity.ok(foundPlayer);
    }

//    @PutMapping
//    public ResponseEntity<Player> updatePlayer(@RequestBody PlayerUpdateRequest updateRequest,) {
//        Player updatedPlayer = playerService.editPlayer(authPlayer.getId(), updateRequest.toEntity());
//        return ResponseEntity.ok(updatedPlayer);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Boolean> deletePlayer(@PathVariable Long id) {
//        boolean flag = playerService.withdrawPlayer(id);
//
//        return ResponseEntity.ok(flag);
//    }
}
