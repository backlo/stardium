package com.bb.stardium.chat.api.message;

import com.bb.stardium.chat.api.message.dto.RequestChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class MessageApiController {
    private final SimpMessageSendingOperations messagingTemplate;

    public MessageApiController(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat/message")
    public void message(RequestChatMessage chatMessage) {
        if (chatMessage.isJoinType()) {
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장하셨습니디.");
        }

        if (chatMessage.isExitType()) {
            chatMessage.setMessage(chatMessage.getSender() + "님이 퇴장하셨습니다.");
        }

        messagingTemplate.convertAndSend("/sub/clubs/" + chatMessage.getClubId() + "/chats", chatMessage);
    }

}
