package com.bb.stardium.service.player.exception;

public class EmailAndNickNameAlreadyExistException extends RuntimeException {

    public EmailAndNickNameAlreadyExistException() {
        super("이메일이 존재합니다.");
    }
}
