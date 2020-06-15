package com.bb.stardium.auth.security.handler;

import com.bb.stardium.auth.security.handler.dto.LogoutResponse;
import com.bb.stardium.security.service.SecurityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {
    private static final Logger log = LoggerFactory.getLogger(JwtLogoutSuccessHandler.class);
    private static final Boolean SUCCESS_FLAG = true;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("logout handler : 성공");

        String responseFlag = new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(
                        LogoutResponse.builder()
                                .flag(SUCCESS_FLAG.toString())
                                .build()
                );
        response.setContentType("application/json");
        response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

        response.getWriter().write(responseFlag);
    }
}
