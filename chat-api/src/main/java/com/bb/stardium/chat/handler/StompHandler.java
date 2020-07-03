package com.bb.stardium.chat.handler;

import com.bb.stardium.security.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.AuthenticationServiceException;

public class StompHandler implements ChannelInterceptor {
    private static final Logger log = LoggerFactory.getLogger(StompHandler.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final SecurityService securityService;

    public StompHandler(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        try {
            StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

            if (StompCommand.CONNECT == accessor.getCommand()) {
                log.info("Web Socket 연결 후 security 상태 확인");
                validateAuthorizationHeader(accessor);
            }
            return message;
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void validateAuthorizationHeader(StompHeaderAccessor accessor) throws IllegalAccessException{
        String authorizationHeader = accessor.getFirstNativeHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader == null || authorizationHeader.startsWith("Bearer ")) {
            throw new AuthenticationServiceException("Invalid Jwt Token : Can't Accessed");
        }
        String token = authorizationHeader.substring(7);
        String email = securityService.extractEmail(token);

        if (email == null || securityService.isTokenExpired(token)) {
            throw new AuthenticationServiceException("Invalid Jwt Token : not valid email or expire token");
        }
    }
}
