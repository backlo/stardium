package com.bb.stardium.chat.domain.club.exception;

public class NotFoundMatchException extends RuntimeException{

    public NotFoundMatchException() {
        super("플레이어와 방이 맺어진 경기를 찾을 수 없습니다.");
    }
}
