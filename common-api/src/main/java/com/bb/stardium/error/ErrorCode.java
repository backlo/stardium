package com.bb.stardium.error;

import com.bb.stardium.service.player.exception.EmailAndNickNameAlreadyExistException;
import com.bb.stardium.service.player.exception.PlayerNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Arrays;

public enum ErrorCode {
    AUTHORIZATION_EXCEPTION(IllegalAccessException.class, 100),
    PLAYER_NOT_FOUND_EXCEPTION(PlayerNotFoundException.class, 101),
    EMAIL_ALREADY_EXIST_EXCEPTION(EmailAndNickNameAlreadyExistException.class, 102),
    BAD_CREDENTIALS_EXCEPTION(BadCredentialsException.class, 103);

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
