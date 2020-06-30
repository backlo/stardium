package com.bb.stardium.chat.service;

import com.bb.stardium.chat.service.dto.ChatMessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisSubscriberService implements MessageListener {
    private static final Logger log = LoggerFactory.getLogger(RedisSubscriberService.class);

    private final ObjectMapper objectMapper;
    private final RedisTemplate template;
    private final SimpMessageSendingOperations messagingTemplate;

    public RedisSubscriberService(ObjectMapper objectMapper, RedisTemplate<String, Object> template, SimpMessageSendingOperations messagingTemplate) {
        this.objectMapper = objectMapper;
        this.template = template;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String publishMessage = (String) template.getStringSerializer().deserialize(message.getBody());
            ChatMessageDto clubMessage = objectMapper.readValue(publishMessage, ChatMessageDto.class);
            messagingTemplate.convertAndSend("/sub/club/" + clubMessage.getClubId() + "/chat", clubMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
