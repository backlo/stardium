package com.bb.stardium.v1.player.service.exception;

public class AuthenticationFailException extends RuntimeException {
    public AuthenticationFailException(Exception e) {
        super(e.getMessage(), e.getCause());
    }
}
