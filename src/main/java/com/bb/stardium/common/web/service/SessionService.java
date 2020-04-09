package com.bb.stardium.common.web.service;

import com.bb.stardium.player.domain.Player;
import com.bb.stardium.player.domain.repository.PlayerRepository;
import com.bb.stardium.player.dto.PlayerSessionDto;
import com.bb.stardium.player.service.exception.EmailNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionService {
    private static final Logger log = LoggerFactory.getLogger(SessionService.class);

    public boolean isLoggedIn(final HttpSession session) {
        log.info("sessionId : {}", session.getId());
        log.info("sessionAttribute : {}", session.getAttribute("login"));
        PlayerSessionDto sessionDto = (PlayerSessionDto) session.getAttribute("login");

        return sessionDto != null;
    }


}
