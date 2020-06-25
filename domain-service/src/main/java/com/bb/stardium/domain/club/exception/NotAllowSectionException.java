package com.bb.stardium.domain.club.exception;

public class NotAllowSectionException extends RuntimeException {

    public NotAllowSectionException() {
        super("허용 되지 않는 구 입니다.");
    }
}
