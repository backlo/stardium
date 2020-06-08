package com.bb.stardium.auth.api.player.handler;

import com.bb.stardium.error.ErrorResponse;
import com.bb.stardium.service.player.exception.EmailAndNickNameAlreadyExistException;
import com.bb.stardium.service.player.exception.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EmailAndNickNameAlreadyExistException.class, PlayerNotFoundException.class})
    public ResponseEntity<Object> handlePlayerNotFound(Exception ex) {
        return buildResponseEntity(
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex)
        );
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse.getErrorEntity(), errorResponse.getStatus());
    }
}
