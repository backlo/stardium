package com.bb.stardium.v1.player.service.exception;

public class EmailNotExistException extends RuntimeException {
    public EmailNotExistException() {
        super("존재하지 않는 사용자 이메일입니다.");
    }
}
