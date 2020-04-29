package com.bb.stardium.v1.bench.domain.exception;

public class PlayerAlreadyExistException extends RuntimeException {
    public PlayerAlreadyExistException() {
        super("이미 방에 존재하는 플레이어입니다");
    }
}
