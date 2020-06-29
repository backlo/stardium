package com.bb.stardium.error.exception;

public class IllegalMessageTypeException extends RuntimeException {
    public IllegalMessageTypeException() {
        super("메세지 타입이 알맞지 않습니다.");
    }
}
