package com.bb.stardium.v1.bench.service.exception;

public class FixedReadyRoomException extends RuntimeException {
    public FixedReadyRoomException() {
        super("풀방 상태인 방에서는 나갈 수 없습니다.");
    }
}
