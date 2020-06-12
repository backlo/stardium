package com.bb.stardium.domain.club.exception;

public class MasterAndClubNotMatchedException extends RuntimeException {
    public MasterAndClubNotMatchedException() {
        super("해당 플레이어가 개설한 방이 아닙니다");
    }
}
