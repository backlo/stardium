package com.bb.stardium.auth.handler;

import com.bb.stardium.error.model.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.info("Authentication Success Handler : 실패");
        String responseError = new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(
                        new ErrorResponse(HttpStatus.FORBIDDEN, exception)
                );

        response.setContentType("application/json");
        response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

        response.getWriter().write(responseError);
    }
}
