package com.bb.stardium.error.exception;

public class NotImageFormException extends RuntimeException {
    public NotImageFormException() {
        super("이미지 형식이 맞지 않습니다.");
    }
}
