package com.bb.stardium.chat.service;

import com.bb.stardium.chat.domain.ChatClub;
import com.bb.stardium.chat.domain.repository.ChatMessageRepository;
import com.bb.stardium.chat.service.dto.ChatDto;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatClub createRoom(ChatDto chatDto) {
        return chatMessageRepository.createChat(chatDto);
    }

    public ChatClub findChatById(Long clubId) {
        return chatMessageRepository.findChatByClubId(clubId);
    }
}
