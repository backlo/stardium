package com.bb.stardium.error.model;

import com.bb.stardium.domain.club.exception.NotAllowCityException;
import com.bb.stardium.error.exception.FieldsEmptyException;
import com.bb.stardium.service.player.exception.EmailAlreadyExistException;
import com.bb.stardium.service.player.exception.NicknameAlreadyExistException;
import com.bb.stardium.service.player.exception.PlayerNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Arrays;

public enum ErrorCode {
    NOT_ALLOW_AUTHORIZE_EXCEPTION(BadCredentialsException.class, 100),
    PLAYER_NOT_FOUND_EXCEPTION(PlayerNotFoundException.class, 101),
    EMAIL_ALREADY_EXIST_EXCEPTION(EmailAlreadyExistException.class, 102),
    NICKNAME_ALREADY_EXIST_EXCEPTION(NicknameAlreadyExistException.class, 103),
    FIELD_EMPTY_EXCEPTION(FieldsEmptyException.class, 104),

    NOT_ALLOW_CITY_EXCEPTION(NotAllowCityException.class, 200);

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
                .orElse(ErrorCode.NOT_ALLOW_AUTHORIZE_EXCEPTION.code);
    }
}
