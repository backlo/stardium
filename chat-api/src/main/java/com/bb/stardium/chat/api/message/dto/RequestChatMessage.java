package com.bb.stardium.chat.api.message.dto;

import com.bb.stardium.chat.service.dto.ChatMessageDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RequestChatMessage {
    private Long clubId;
    private String sender;
    private String message;
    private LocalDateTime dateTime;
    private String type;

    public ChatMessageDto toEntity() {
        return ChatMessageDto.builder()
                .clubId(clubId)
                .sender(sender)
                .message(message)
                .dateTime(dateTime)
                .type(checkValidType().getType())
                .build();
    }

    private ChatType checkValidType() {
        return ChatType.checkType(this.type);
    }

    public boolean isJoinType() {
        return this.type.equals(ChatType.JOIN.getType());
    }

    public boolean isExitType() {
        return this.type.equals(ChatType.EXIT.getType());
    }
}
