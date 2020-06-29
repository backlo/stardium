package com.bb.stardium.chat.api.chat.dto;

import com.bb.stardium.chat.domain.ChatInfoToClub;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseChatClub {
    private final ChatInfoToClub chatInfo;

    @Builder
    public ResponseChatClub(ChatInfoToClub chatInfo) {
        this.chatInfo = chatInfo;
    }
}
