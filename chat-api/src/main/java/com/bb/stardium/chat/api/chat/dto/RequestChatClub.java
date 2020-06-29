package com.bb.stardium.chat.api.chat.dto;

import com.bb.stardium.chat.service.dto.ChatDto;
import com.bb.stardium.error.exception.MismatchMasterAndNicknameException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestChatClub {
    private String master;

    public ChatDto toEntity(Long clubId, String nickname) {
        if (!nickname.equals(master)) {
            throw new MismatchMasterAndNicknameException();
        }

        return ChatDto.builder()
                .clubId(clubId)
                .master(master)
                .build();
    }
}
