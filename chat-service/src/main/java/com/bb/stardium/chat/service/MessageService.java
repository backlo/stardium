package com.bb.stardium.chat.service;

import com.bb.stardium.chat.domain.repository.ChatMessageRepository;
import com.bb.stardium.chat.service.dto.ChatMessageDto;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final RedisPublisherService publisherService;
    private final RedisSubscriberService subscriberService;

    public MessageService(ChatMessageRepository chatMessageRepository, RedisPublisherService publisherService, RedisSubscriberService subscriberService) {
        this.chatMessageRepository = chatMessageRepository;
        this.publisherService = publisherService;
        this.subscriberService = subscriberService;
    }

    public void enterChatClub(ChatMessageDto messageDto) {
        ChannelTopic topic = chatMessageRepository.enterChatClub(messageDto.getClubId(), subscriberService);
        publisherService.publish(topic, messageDto);
    }

    public void publish(ChatMessageDto messageDto) {
        Long clubId = messageDto.getClubId();
        publisherService.publish(chatMessageRepository.getTopic(clubId), messageDto);
    }
}
