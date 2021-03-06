package com.bb.stardium.resource.api.match.handler;

import com.bb.stardium.chat.domain.club.exception.NotFoundMatchException;
import com.bb.stardium.chat.domain.club.exception.OverLimitPlayersSizeException;
import com.bb.stardium.chat.domain.club.exception.PlayerNotExistClubException;
import com.bb.stardium.error.handler.AbstractApiExceptionHandler;
import com.bb.stardium.error.model.ErrorResponse;
import com.bb.stardium.chat.domain.club.exception.PlayerAlreadyJoinClubException;
import com.bb.stardium.chat.service.player.exception.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MatchApiExceptionHandler extends AbstractApiExceptionHandler {

    @ExceptionHandler(PlayerAlreadyJoinClubException.class)
    public ResponseEntity<Object> handleBadRequestMatchRequest(Exception ex) {
        return buildResponseEntity(
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex)
        );
    }

    @ExceptionHandler({NotFoundMatchException.class, PlayerNotExistClubException.class, OverLimitPlayersSizeException.class, PlayerNotFoundException.class})
    public ResponseEntity<Object> handleForbiddenMatchRequest(Exception ex) {
        return buildResponseEntity(
                new ErrorResponse(HttpStatus.FORBIDDEN, ex)
        );
    }
}
