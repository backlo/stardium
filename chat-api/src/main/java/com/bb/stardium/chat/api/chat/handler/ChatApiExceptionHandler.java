package com.bb.stardium.chat.api.chat.handler;

import com.bb.stardium.error.exception.IllegalMessageTypeException;
import com.bb.stardium.error.exception.MismatchMasterAndNicknameException;
import com.bb.stardium.error.handler.AbstractApiExceptionHandler;
import com.bb.stardium.error.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ChatApiExceptionHandler extends AbstractApiExceptionHandler {

    @ExceptionHandler({MismatchMasterAndNicknameException.class, IllegalMessageTypeException.class})
    public ResponseEntity<Object> handleBadRequestPlayerRequest(Exception ex) {
        return buildResponseEntity(
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex)
        );
    }
}
