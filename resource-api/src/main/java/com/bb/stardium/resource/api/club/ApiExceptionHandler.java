package com.bb.stardium.resource.api.club;

import com.bb.stardium.error.ErrorResponse;
import com.bb.stardium.service.player.exception.EmailAndNickNameAlreadyExistException;
import com.bb.stardium.service.player.exception.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.UnsupportedEncodingException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BadCredentialsException.class, UnsupportedEncodingException.class})
    public ResponseEntity<Object> handleCredentialException(Exception ex) {
        return buildResponseEntity(
                new ErrorResponse(HttpStatus.FORBIDDEN, ex)
        );
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse apiError) {
        return new ResponseEntity<>(apiError.getErrorEntity(), apiError.getStatus());
    }
}
