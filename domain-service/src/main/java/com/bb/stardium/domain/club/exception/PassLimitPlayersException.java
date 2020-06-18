package com.bb.stardium.domain.club.exception;

public class PassLimitPlayersException extends RuntimeException {
    public PassLimitPlayersException() {
        super("방의 입장 제한 인원 수를 넘었습니다.");
    }
}
