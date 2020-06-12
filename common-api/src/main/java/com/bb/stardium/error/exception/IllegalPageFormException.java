package com.bb.stardium.error.exception;

public class IllegalPageFormException extends RuntimeException {
    public IllegalPageFormException() {
        super("페이지 파라미터 형식이 옳지 않습니다.");
    }
}
