package com.bb.stardium.auth.security.filter.exception;

public class FieldsEmptyException extends RuntimeException {
    public FieldsEmptyException() {
        super("이메일, 비밀번호, 닉네임 중 하나라도 비어있으면 안됩니다.");
    }
}
