package com.bb.stardium.v1.bench.web.api;


import com.bb.stardium.v1.bench.service.RoomService;
import com.bb.stardium.v1.common.web.annotation.LoggedInPlayer;
import com.bb.stardium.v1.player.domain.Player;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/rooms")
public class RoomRestController {
    private final RoomService roomService;

    public RoomRestController(RoomService roomService) {
        this.roomService = roomService;
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity delete(@PathVariable Long roomId, @LoggedInPlayer final Player loggedInPlayer) {
        roomService.delete(roomId, loggedInPlayer);
        return ResponseEntity.ok().build();
    }
}
