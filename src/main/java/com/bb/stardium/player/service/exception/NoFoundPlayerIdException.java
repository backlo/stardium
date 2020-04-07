package com.bb.stardium.player.service.exception;

public class NoFoundPlayerIdException extends RuntimeException {
    public NoFoundPlayerIdException() {
        super("Player 고유 아이디를 찾을 수 없습니다.");
    }
}
