package com.bb.stardium.player.service.exception;

public class AuthenticationFailException extends RuntimeException {
    public AuthenticationFailException(Exception e) {
        super(e.getMessage(), e.getCause());
    }
}
