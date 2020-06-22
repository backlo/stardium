package com.bb.stardium.auth.handler;

import com.bb.stardium.auth.handler.dto.TokenResponse;
import com.bb.stardium.security.model.AuthenticationPlayer;
import com.bb.stardium.security.service.SecurityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JwtAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private static final String TOKEN_TYPE = "bearer ";
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationSuccessHandler.class);

    private final SecurityService securityService;

    public JwtAuthenticationSuccessHandler(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("Authentication Success Handler : 성공");
        AuthenticationPlayer player = (AuthenticationPlayer) authentication.getPrincipal();
        String token = securityService.generateToken(player);

        String responseToken = new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(
                        TokenResponse.builder()
                                .token(TOKEN_TYPE + token)
                                .build()
                );

        response.setContentType("application/json");
        response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

        response.getWriter().write(responseToken);
    }
}
