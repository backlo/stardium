package com.bb.stardium.v1.common.web.service;

import com.bb.stardium.v1.player.dto.PlayerSessionDto;
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
