package com.bb.stardium.error.exception;

public class MultipartFileEmptyException extends RuntimeException {
    public MultipartFileEmptyException() {
        super("Multipart File 이 없습니다.");
    }
}
