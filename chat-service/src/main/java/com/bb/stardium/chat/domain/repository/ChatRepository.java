package com.bb.stardium.chat.domain.repository;

import com.bb.stardium.chat.domain.ChatInfoToClub;
import com.bb.stardium.chat.service.dto.ChatDto;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class ChatRepository {
    private final Map<Long, ChatInfoToClub> chatInfoToClubs = new LinkedHashMap<>();

    public ChatInfoToClub createChat(ChatDto chatDto) {
        ChatInfoToClub chatInfo = ChatInfoToClub.builder()
                .clubId(chatDto.getClubId())
                .master(chatDto.getMaster())
                .build();

        chatInfoToClubs.put(chatDto.getClubId(), chatInfo);
        return chatInfo;
    }

    public ChatInfoToClub findChatByClubId(Long clubId) {
        return chatInfoToClubs.get(clubId);
    }

}
