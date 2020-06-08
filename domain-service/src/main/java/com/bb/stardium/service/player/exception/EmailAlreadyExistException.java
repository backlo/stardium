package com.bb.stardium.service.player.exception;

public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException() {
        super("이메일이 존재합니다.");
    }
}
