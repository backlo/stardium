package com.bb.stardium.v1.player.domain;

public class MisMatchPasswordException extends RuntimeException {
    public MisMatchPasswordException() {
        super("비밀번호가 일치 하지 않습니다.");
    }
}
