package com.bb.stardium.chat.domain.repository;

import com.bb.stardium.chat.domain.ChatClub;
import com.bb.stardium.chat.service.RedisSubscriberService;
import com.bb.stardium.chat.service.dto.ChatDto;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ChatMessageRepository {
    private static final String CHAT_CLUB = "CHAT_CLUB";

    private final RedisMessageListenerContainer redisMessageListener;
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Long, ChatClub> opsHashChatClub;
    private Map<Long, ChannelTopic> topics;

    public ChatMessageRepository(RedisMessageListenerContainer redisMessageListener, RedisTemplate<String, Object> redisTemplate) {
        this.redisMessageListener = redisMessageListener;
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        opsHashChatClub = redisTemplate.opsForHash();
        topics = new HashMap<>();
    }

    public ChatClub createChat(ChatDto chatDto) {
        ChatClub chatInfo = ChatClub.builder()
                .clubId(chatDto.getClubId())
                .master(chatDto.getMaster())
                .build();

        opsHashChatClub.put(CHAT_CLUB, chatDto.getClubId(), chatInfo);
        return chatInfo;
    }

    public ChatClub findChatByClubId(Long clubId) {
        return opsHashChatClub.get(CHAT_CLUB, clubId);
    }

    public ChannelTopic enterChatClub(Long clubId, RedisSubscriberService redisSubscriber) {
        ChannelTopic topic = topics.get(clubId);

        if (topic == null) {
            topic = ChannelTopic.of(String.valueOf(clubId));
            redisMessageListener.addMessageListener(redisSubscriber, topic);
            topics.put(clubId, topic);
        }

        return topic;
    }

    public ChannelTopic getTopic(Long clubId) {
        return topics.get(clubId);
    }
}
