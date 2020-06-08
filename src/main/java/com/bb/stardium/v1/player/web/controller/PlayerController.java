package com.bb.stardium.v1.player.web.controller;

import com.bb.stardium.v1.mediafile.domain.ProfileImage;
import com.bb.stardium.v1.mediafile.service.MediaFileService;
import com.bb.stardium.v1.player.dto.PlayerRequestDto;
import com.bb.stardium.v1.player.dto.PlayerResponseDto;
import com.bb.stardium.v1.player.dto.PlayerSessionDto;
import com.bb.stardium.v1.player.service.PlayerService;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@RequestMapping("/players")
@Controller
@EnableRedisHttpSession
public class PlayerController {

    private final PlayerService playerService;
    private final MediaFileService mediaFileService;

    public PlayerController(PlayerService playerService, MediaFileService mediaFileService) {
        this.playerService = playerService;
        this.mediaFileService = mediaFileService;
    }

    @GetMapping("/new")
    public String signUpPage() {
        return "signup.html";
    }

    @PostMapping("/new")
    public String register(PlayerRequestDto requestDto) {
        playerService.register(requestDto);
        return "redirect:/login";
    }

    @GetMapping("/edit")
    public String editPage(HttpSession session, Model model) {
        PlayerSessionDto sessionDto = (PlayerSessionDto) session.getAttribute("login");
        PlayerResponseDto playerResponseDto = playerService.findPlayerById(sessionDto.getPlayerId());

        model.addAttribute("model", playerResponseDto);
        return "stardium.player-edit.html";
    }

    @PostMapping("/edit")
    public String edit(PlayerRequestDto requestDto, HttpSession session,
                       @RequestParam("profile") MultipartFile file, RedirectAttributes redirectAttributes) {

        ProfileImage profileUrl = mediaFileService.updateProfile(file);


        PlayerSessionDto sessionDto = (PlayerSessionDto) session.getAttribute("login");
        playerService.update(requestDto, profileUrl, sessionDto);

        session.setAttribute("login", sessionDto);
        redirectAttributes.addFlashAttribute("message", "회원 정보가 수정되었습니다.");
        return "redirect:/";
    }
}
