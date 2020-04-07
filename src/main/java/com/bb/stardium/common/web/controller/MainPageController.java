package com.bb.stardium.common.web.controller;

import com.bb.stardium.bench.domain.Section;
import com.bb.stardium.bench.dto.RoomResponseDto;
import com.bb.stardium.bench.service.RoomService;
import com.bb.stardium.common.web.annotation.LoggedInPlayer;
import com.bb.stardium.player.domain.Player;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@EnableRedisHttpSession
public class MainPageController {

    private final RoomService roomService;

    public MainPageController(RoomService roomService) {
        this.roomService = roomService;
    }

    private String homepageRooms(final Model model, final List<RoomResponseDto> rooms) {
        model.addAttribute("rooms", rooms);
        model.addAttribute("sections", Section.getAllSections());
        return "main-all-room";
    }

    @GetMapping("/")
    public String homepage(Model model) {
        return homepageRooms(model, roomService.findAllUnexpiredRooms());
    }

    @GetMapping("/{section}")
    public String filteredHomePage(@PathVariable String section, Model model) {
        return homepageRooms(model, roomService.findRoomsFilterBySection(section));
    }

    @GetMapping("/search/{searchKeyword}")
    public String searchRooms(@PathVariable String searchKeyword, Model model) {
        return homepageRooms(model, roomService.findRoomBySearchKeyword(searchKeyword));
    }

    @GetMapping("/my-room")
    public String myRoomPage(Model model, @LoggedInPlayer Player loggedInPlayer) {
        List<RoomResponseDto> myRooms = roomService.findPlayerJoinedRoom(loggedInPlayer);
        model.addAttribute("rooms", myRooms);
        return "main-my-room";
    }

}
