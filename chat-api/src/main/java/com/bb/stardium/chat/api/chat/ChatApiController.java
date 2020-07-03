package com.bb.stardium.chat.api.chat;

import com.bb.stardium.chat.api.chat.dto.RequestChatClub;
import com.bb.stardium.chat.api.chat.dto.ResponseChatClub;
import com.bb.stardium.chat.domain.ChatClub;
import com.bb.stardium.chat.resolver.PlayerNickname;
import com.bb.stardium.chat.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clubs/{clubId}/chats")
public class ChatApiController {
    private final ChatService chatService;

    public ChatApiController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<ResponseChatClub> createChat(@PathVariable(name = "clubId") Long clubId,
                                                       @PlayerNickname String nickname,
                                                       @RequestBody RequestChatClub requestDto) {
        ChatClub createdChat = chatService.createRoom(requestDto.toEntity(clubId, nickname));

        return ResponseEntity.ok(
                ResponseChatClub.builder()
                        .chatInfo(createdChat)
                .build()
        );
    }

    @GetMapping
    public ChatClub findChatInfoClubById(@PathVariable(name = "clubId") Long clubId) {
        return chatService.findChatById(clubId);
    }
}
