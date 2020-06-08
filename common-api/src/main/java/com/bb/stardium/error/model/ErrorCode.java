package com.bb.stardium.error.model;

import com.bb.stardium.service.player.exception.EmailAlreadyExistException;
import com.bb.stardium.service.player.exception.NicknameAlreadyExistException;
import com.bb.stardium.service.player.exception.PlayerNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Arrays;

public enum ErrorCode {
    AUTHORIZATION_EXCEPTION(IllegalAccessException.class, 100),
    PLAYER_NOT_FOUND_EXCEPTION(PlayerNotFoundException.class, 101),
    EMAIL_ALREADY_EXIST_EXCEPTION(EmailAlreadyExistException.class, 102),
    NICKNAME_ALREADY_EXIST_EXCEPTION(NicknameAlreadyExistException.class, 103),
    BAD_CREDENTIALS_EXCEPTION(BadCredentialsException.class, 104);

    private final Object exceptionClazz;
    private final int code;

    ErrorCode(Object exceptionClazz, int code) {
        this.exceptionClazz = exceptionClazz;
        this.code = code;
    }

    public static int findCode(Exception ex) {
        return Arrays.stream(values())
                .filter(clazz -> clazz.exceptionClazz.equals(ex.getClass()))
                .mapToInt(clazz -> clazz.code)
                .findFirst()
                .orElse(ErrorCode.AUTHORIZATION_EXCEPTION.code);
    }
}
