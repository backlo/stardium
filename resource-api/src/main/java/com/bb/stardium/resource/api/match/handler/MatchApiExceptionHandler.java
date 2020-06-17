package com.bb.stardium.resource.api.match.handler;

import com.bb.stardium.error.handler.AbstractApiExceptionHandler;
import com.bb.stardium.error.model.ErrorResponse;
import com.bb.stardium.domain.club.exception.PlayerAlreadyJoinClubException;
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
}
