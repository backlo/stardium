package com.bb.stardium.error.exception;

public class NotAllowImageFileException extends RuntimeException {
    public NotAllowImageFileException() {
        super("허용 되지 않는 이미지 파일입니다.");
    }
}
