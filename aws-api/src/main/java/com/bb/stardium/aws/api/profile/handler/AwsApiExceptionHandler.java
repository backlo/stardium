package com.bb.stardium.aws.api.profile.handler;

import com.bb.stardium.error.exception.*;
import com.bb.stardium.error.handler.AbstractApiExceptionHandler;
import com.bb.stardium.error.model.ErrorResponse;
import com.bb.stardium.chat.service.player.exception.InvalidProfileUrlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AwsApiExceptionHandler extends AbstractApiExceptionHandler {

    @ExceptionHandler({FileConvertException.class, FileUploadException.class, MultipartFileEmptyException.class, NotImageFormException.class})
    public ResponseEntity<Object> handleForbiddenStaticRequest(Exception ex) {
        return buildResponseEntity(
                new ErrorResponse(HttpStatus.FORBIDDEN, ex)
        );
    }

    @ExceptionHandler({InvalidProfileUrlException.class, NotAllowImageFileException.class})
    public ResponseEntity<Object> handleBadRequestStaticRequest(Exception ex) {
        return buildResponseEntity(
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex)
        );
    }

}
