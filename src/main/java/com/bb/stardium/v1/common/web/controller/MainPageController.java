package com.bb.stardium.v1.common.web.controller;

import com.bb.stardium.v1.bench.domain.Section;
import com.bb.stardium.v1.bench.dto.RoomResponseDto;
import com.bb.stardium.v1.bench.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MainPageController {
    private static final Logger log = LoggerFactory.getLogger(MainPageController.class);
    private final RoomService roomService;

    public MainPageController(RoomService roomService) {
        this.roomService = roomService;
    }

    private String homepageRooms(final Model model, final List<RoomResponseDto> rooms) {
        if (!rooms.isEmpty()) {
            log.info("home room , {}", rooms.get(0).getMaster().getProfile().getProfileUrl());
        }

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

}
