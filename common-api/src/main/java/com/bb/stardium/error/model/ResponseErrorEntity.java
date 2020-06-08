package com.bb.stardium.error.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
class ResponseErrorEntity {
    private String timestamp;
    private int code;
    private String message;

    private ResponseErrorEntity() {
        timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Builder
    public ResponseErrorEntity(int code, String message) {
        this();
        this.code = code;
        this.message = message;
    }
}
