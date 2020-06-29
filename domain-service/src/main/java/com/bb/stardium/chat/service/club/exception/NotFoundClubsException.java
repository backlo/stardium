package com.bb.stardium.chat.service.club.exception;

public class NotFoundClubsException extends RuntimeException {

    public NotFoundClubsException() {
        super("해당 방을 찾을 수 없습니다.");
    }
}
