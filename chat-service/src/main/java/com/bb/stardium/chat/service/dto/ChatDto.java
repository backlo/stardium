package com.bb.stardium.chat.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatDto {
    private final Long clubId;
    private final String master;

    @Builder
    public ChatDto(Long clubId, String master) {
        this.clubId = clubId;
        this.master = master;
    }
}
