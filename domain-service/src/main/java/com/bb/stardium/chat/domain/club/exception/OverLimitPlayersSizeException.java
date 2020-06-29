package com.bb.stardium.chat.domain.club.exception;

public class OverLimitPlayersSizeException extends RuntimeException {
    public OverLimitPlayersSizeException() {
        super("방의 입장 제한 인원 수를 넘었습니다.");
    }
}
