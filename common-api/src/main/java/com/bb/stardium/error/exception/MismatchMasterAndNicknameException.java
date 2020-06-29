package com.bb.stardium.error.exception;

public class MismatchMasterAndNicknameException extends RuntimeException {
    public MismatchMasterAndNicknameException() {
        super("플레이어와 마스터의 이름이 맞지 않습니다.");
    }
}
