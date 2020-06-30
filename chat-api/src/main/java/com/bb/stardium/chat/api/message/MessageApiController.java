package com.bb.stardium.chat.api.message;

import com.bb.stardium.chat.api.message.dto.RequestChatMessage;
import com.bb.stardium.chat.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
public class MessageApiController {
    private final SimpMessageSendingOperations messagingTemplate;
    private final MessageService messageService;

    public MessageApiController(SimpMessageSendingOperations messagingTemplate, MessageService messageService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
    }

    @MessageMapping("/chat/message")
    public void message(RequestChatMessage chatMessage) {
        if (chatMessage.isJoinType()) {
            chatMessage.setMessage(chatMessage.getSender() + "님이 입장하셨습니디.");
            messageService.enterChatClub(chatMessage.toEntity());
            return;
        }

        if (chatMessage.isExitType()) {
            // todo 나가기 기능
//            messageService.exitChatClub(chatMessage.toEntity());
//            chatMessage.setMessage(chatMessage.getSender() + "님이 퇴장하셨습니다.");
        }

        messageService.publish(chatMessage.toEntity());
    }

}
