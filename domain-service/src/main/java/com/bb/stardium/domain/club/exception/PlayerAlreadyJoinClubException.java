package com.bb.stardium.domain.club.exception;

public class PlayerAlreadyJoinClubException extends RuntimeException {
    public PlayerAlreadyJoinClubException() {
        super("이미 방에 입장을 했습니다.");
    }
}
