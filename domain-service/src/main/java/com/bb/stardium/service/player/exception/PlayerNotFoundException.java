package com.bb.stardium.service.player;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException() {
        super("사용자를 찾을 수 없습니다.");
    }
}
