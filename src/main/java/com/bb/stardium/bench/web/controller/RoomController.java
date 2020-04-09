package com.bb.stardium.bench.web.controller;

import com.bb.stardium.bench.domain.Room;
import com.bb.stardium.bench.dto.RoomResponseDto;
import com.bb.stardium.bench.service.RoomService;
import com.bb.stardium.common.web.annotation.LoggedInPlayer;
import com.bb.stardium.player.domain.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    private static final Logger log = LoggerFactory.getLogger(RoomController.class);

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String mainRoomList(Model model) {
        List<RoomResponseDto> rooms = roomService.findAllUnexpiredRooms();
        model.addAttribute("rooms", rooms);
        return "main-my-room";
    }

    @GetMapping("/create-room")
    public String createRoom() {
        return "create-room";
    }

    @GetMapping("/update-room/{roomId}")
    public String updateRoom(@PathVariable Long roomId, Model model, @LoggedInPlayer final Player loggedInPlayer) {
        Room room = roomService.findRoom(roomId);

        if (room.isNotMaster(loggedInPlayer)) {
            return "redirect:/";
        }

        model.addAttribute("room", room);
        return "update-room";
    }

    @GetMapping("/{roomId}")
    public String get(@PathVariable Long roomId, Model model, @LoggedInPlayer final Player loggedInPlayer) {
        Room room = roomService.findRoom(roomId);

        if (!room.hasPlayer(loggedInPlayer)) {
            return "redirect:/";
        }

        model.addAttribute("room", room);

        log.info("room info : {}", room.toString());

        return "room";    }

    @GetMapping("/{roomId}/details")
    public String getDetail(@PathVariable Long roomId, Model model) {
        Room room = roomService.findRoom(roomId);
        model.addAttribute("room", room);
        return "room-detail";
    }

}
