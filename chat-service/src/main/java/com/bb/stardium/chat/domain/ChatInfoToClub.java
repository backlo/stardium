package com.bb.stardium.chat.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatInfoToClub {
    private final Long clubId;
    private final String master;

    @Builder
    public ChatInfoToClub(Long clubId, String master) {
        this.clubId = clubId;
        this.master = master;
    }

}
