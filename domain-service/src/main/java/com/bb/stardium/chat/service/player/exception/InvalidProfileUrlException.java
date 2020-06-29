package com.bb.stardium.chat.service.player.exception;

public class InvalidProfileUrlException extends RuntimeException {
    public InvalidProfileUrlException() {
        super("프로필 url 형식이 잘못되었습니다.");
    }
}
