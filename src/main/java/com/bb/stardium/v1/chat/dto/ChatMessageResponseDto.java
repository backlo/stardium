package com.bb.stardium.v1.chat.dto;

import com.bb.stardium.v1.chat.domain.ChatMessage;
import com.bb.stardium.v1.common.util.EscapedCharacters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ChatMessageResponseDto {
    private Long roomId;
    private String nickname;
    private String message;
    private OffsetDateTime timestamp;

    public ChatMessageResponseDto(final ChatMessage message) {
        this.roomId = message.getRoomId();
        this.nickname = EscapedCharacters.of(message.getPlayerNickname());
        this.message = EscapedCharacters.of(message.getContents());
        this.timestamp = message.getTimestamp();
    }
}
