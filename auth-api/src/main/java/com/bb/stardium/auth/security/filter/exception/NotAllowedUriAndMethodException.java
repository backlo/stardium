package com.bb.stardium.auth.security.filter.exception;

public class NotAllowedUriAndMethodException extends RuntimeException{

    public NotAllowedUriAndMethodException() {
        super("허용하지 않는 Method 와 Uri 입니다");
    }
}
