package com.bb.stardium.chat.service.player.exception;

public class NicknameAlreadyExistException extends RuntimeException {
    public NicknameAlreadyExistException() {
        super("닉네임이 존재 합니다.");
    }
}
