package com.bb.stardium.chat.service;

import com.bb.stardium.chat.domain.repository.ChatMessageRepository;
import com.bb.stardium.chat.service.dto.ChatMessageDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisPublisherService {
    private final RedisTemplate<String, Object> template;
    private final ChatMessageRepository chatMessageRepository;

    public RedisPublisherService(RedisTemplate<String, Object> template, ChatMessageRepository chatMessageRepository) {
        this.template = template;
        this.chatMessageRepository = chatMessageRepository;
    }

    public void publish(ChannelTopic topic, ChatMessageDto messageDto) {
        template.convertAndSend(topic.getTopic(), messageDto);
    }
}
