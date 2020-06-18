package com.bb.stardium.error.model;

import com.bb.stardium.domain.club.exception.*;
import com.bb.stardium.error.exception.*;
import com.bb.stardium.service.club.exception.NotFoundClubsException;
import com.bb.stardium.service.player.exception.*;

import java.util.Arrays;

public enum ErrorCode {
    NOT_ALLOW_AUTHORIZE_EXCEPTION(RuntimeException.class, 100),
    PLAYER_NOT_FOUND_EXCEPTION(PlayerNotFoundException.class, 101),
    EMAIL_ALREADY_EXIST_EXCEPTION(EmailAlreadyExistException.class, 102),
    NICKNAME_ALREADY_EXIST_EXCEPTION(NicknameAlreadyExistException.class, 103),
    FIELD_EMPTY_EXCEPTION(FieldsEmptyException.class, 104),

    NOT_ALLOW_CITY_EXCEPTION(NotAllowCityException.class, 200),
    NOT_FOUND_CLUB_EXCEPTION(NotFoundClubsException.class, 201),
    MASTER_AND_CLUB_NOT_MATCHED_EXCEPTION(MasterAndClubNotMatchedException.class, 202),
    ILLEGAL_PAGE_FORM_EXCEPTION(IllegalPageFormException.class, 203),

    FILE_CONVERT_EXCEPTION(FileConvertException.class, 300),
    FILE_UPLOAD_EXCEPTION(FileUploadException.class, 301),
    MULTIPART_FILE_EMPTY_EXCEPTION(MultipartFileEmptyException.class, 302),
    NOT_IMAGE_FORM_EXCEPTION(NotImageFormException.class, 303),
    INVALID_PROFILE_URL_EXCEPTION(InvalidProfileUrlException.class, 304),

    PLAYER_ALREADY_JOIN_CLUB_EXCEPTION(PlayerAlreadyJoinClubException.class, 400),
    NOT_FOUND_MATCH_EXCEPTION(NotFoundMatchException.class, 401),
    PLAYER_NOT_EXIST_CLUB_EXCEPTION(PlayerNotExistClubException.class, 402),
    PASS_LIMIT_PLAYERS_EXCEPTION(PassLimitPlayersException.class, 403);

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
