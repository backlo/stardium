package com.bb.stardium.chat.service;

import com.bb.stardium.chat.domain.ChatInfoToClub;
import com.bb.stardium.chat.domain.repository.ChatRepository;
import com.bb.stardium.chat.service.dto.ChatDto;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public ChatInfoToClub createRoom(ChatDto chatDto) {
        return chatRepository.createChat(chatDto);
    }

    public ChatInfoToClub findChatById(Long clubId) {
        return chatRepository.findChatByClubId(clubId);
    }
}
