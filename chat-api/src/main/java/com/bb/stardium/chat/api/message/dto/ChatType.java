package com.bb.stardium.chat.api.message.dto;

import com.bb.stardium.error.exception.IllegalMessageTypeException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ChatType {
    JOIN("JOIN"),
    TALK("TALK"),
    EXIT("EXIT");

    private final String type;

    ChatType(String type) {
        this.type = type;
    }

    public static ChatType checkType(String requestType) {
        return Arrays.stream(ChatType.values())
                .filter(type -> type.getType().equals(requestType))
                .findFirst()
                .orElseThrow(IllegalMessageTypeException::new);
    }

}
