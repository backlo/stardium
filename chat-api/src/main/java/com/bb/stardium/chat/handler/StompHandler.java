package com.bb.stardium.chat.handler;

import com.bb.stardium.security.service.SecurityService;
import lombok.SneakyThrows;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

import java.util.Objects;

public class StompHandler implements ChannelInterceptor {

    private final SecurityService securityService;

    public StompHandler(SecurityService securityService) {
        this.securityService = securityService;
    }

    @SneakyThrows
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT == accessor.getCommand()) {
            String token = Objects.requireNonNull(accessor.getFirstNativeHeader("Authorization")).substring(7);

            securityService.isTokenExpired(token);
        }
        return message;
    }
}
