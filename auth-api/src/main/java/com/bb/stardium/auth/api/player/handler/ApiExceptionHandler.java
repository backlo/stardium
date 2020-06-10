package com.bb.stardium.auth.api.player.handler;

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

    @ExceptionHandler({EmailAlreadyExistException.class, NicknameAlreadyExistException.class, PlayerNotFoundException.class})
    public ResponseEntity<Object> handlePlayerNotFound(Exception ex) {
        return buildResponseEntity(
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex)
        );
    }

}
