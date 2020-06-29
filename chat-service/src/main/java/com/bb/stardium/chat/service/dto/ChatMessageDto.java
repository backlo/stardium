package com.bb.stardium.chat.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatMessageDto {
    private final Long clubId;
    private final String sender;
    private final String message;
    private final LocalDateTime dateTime;
    private final String type;

    @Builder
    public ChatMessageDto(Long clubId, String sender, String message, LocalDateTime dateTime, String type) {
        this.clubId = clubId;
        this.sender = sender;
        this.message = message;
        this.dateTime = dateTime;
        this.type = type;
    }

}
