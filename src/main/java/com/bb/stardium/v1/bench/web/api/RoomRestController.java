package com.bb.stardium.v1.bench.web.api;


import com.bb.stardium.v1.bench.domain.Room;
import com.bb.stardium.v1.bench.dto.RoomRequestDto;
import com.bb.stardium.v1.bench.service.RoomService;
import com.bb.stardium.v1.bench.service.exception.FixedReadyRoomException;
import com.bb.stardium.v1.common.web.annotation.LoggedInPlayer;
import com.bb.stardium.v1.player.domain.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomRestController {
    private final RoomService roomService;

    public RoomRestController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/join/{roomId}")
    public ResponseEntity join(@PathVariable Long roomId, @LoggedInPlayer final Player loggedInPlayer) {
        final Room joinRoom = roomService.join(loggedInPlayer, roomId);
        return ResponseEntity.ok(joinRoom.getId());
    }

    @PostMapping("/quit/{roomId}")
    public ResponseEntity quit(@PathVariable Long roomId, @LoggedInPlayer final Player loggedInPlayer) {
        roomService.quit(loggedInPlayer, roomId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{roomId}")
    public ResponseEntity update(@PathVariable Long roomId,
                                 @RequestBody RoomRequestDto roomRequestDto,
                                 @LoggedInPlayer final Player loggedInPlayer) {
        Long updatedRoomId = roomService.update(roomId, roomRequestDto, loggedInPlayer);
        return ResponseEntity.ok(updatedRoomId);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity delete(@PathVariable Long roomId, @LoggedInPlayer final Player loggedInPlayer) {
        roomService.delete(roomId, loggedInPlayer);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(FixedReadyRoomException.class)
    public ResponseEntity badRequest() {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
