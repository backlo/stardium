package com.bb.stardium.v1.player.web.controller;

import com.bb.stardium.v1.player.dto.PlayerRequestDto;
import com.bb.stardium.v1.player.service.LoginService;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@EnableRedisHttpSession
public class LoginController {
    private static final String LOGIN = "login";

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login.html";
    }

    @PostMapping("/login")
    public String login(PlayerRequestDto requestDto, HttpSession session) {
        session.setAttribute(LOGIN, loginService.login(requestDto));
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(final HttpSession session) {
        session.removeAttribute("login");
        session.invalidate();
        return "redirect:/";
    }
}
