package com.bb.stardium.domain.club.exception;

public class PlayerNotExistClubException extends RuntimeException {
    public PlayerNotExistClubException() {
        super("해당 플레이어는 방에 없습니다.");
    }
}
