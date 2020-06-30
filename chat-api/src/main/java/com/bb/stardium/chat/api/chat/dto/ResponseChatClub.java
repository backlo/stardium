package com.bb.stardium.chat.api.chat.dto;

import com.bb.stardium.chat.domain.ChatClub;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseChatClub {
    private final ChatClub chatInfo;

    @Builder
    public ResponseChatClub(ChatClub chatInfo) {
        this.chatInfo = chatInfo;
    }
}
