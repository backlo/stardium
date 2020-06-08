package com.bb.stardium.error.handler;

import com.bb.stardium.error.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public abstract class AbstractApiExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}
