package com.bb.stardium.player.service.exception;

public class MisMatchedPlayerAndSessionException extends RuntimeException {
    public MisMatchedPlayerAndSessionException() {
        super("로그인 된 아이디랑 세션 아이디가 서로 다릅니다.");
    }
}
