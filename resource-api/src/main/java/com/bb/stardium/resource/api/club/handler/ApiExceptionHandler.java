package com.bb.stardium.resource.api.club.handler;

import com.bb.stardium.domain.club.exception.NotAllowCityException;
import com.bb.stardium.error.handler.AbstractApiExceptionHandler;
import com.bb.stardium.error.model.ErrorResponse;
import com.bb.stardium.service.player.exception.EmailAlreadyExistException;
import com.bb.stardium.service.player.exception.NicknameAlreadyExistException;
import com.bb.stardium.service.player.exception.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends AbstractApiExceptionHandler {

    @ExceptionHandler(NotAllowCityException.class)
    public ResponseEntity<Object> handleNotAllowCreateClub(Exception ex) {
        return buildResponseEntity(
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex)
        );
    }

}
