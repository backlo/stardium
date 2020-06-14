package com.bb.stardium.aws.api.profile.handler;

import com.bb.stardium.error.exception.FileConvertException;
import com.bb.stardium.error.exception.FileUploadException;
import com.bb.stardium.error.exception.MultipartFileEmptyException;
import com.bb.stardium.error.exception.NotImageFormException;
import com.bb.stardium.error.handler.AbstractApiExceptionHandler;
import com.bb.stardium.error.model.ErrorResponse;
import com.bb.stardium.service.player.exception.InvalidProfileUrlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends AbstractApiExceptionHandler {

    @ExceptionHandler({FileConvertException.class, FileUploadException.class, MultipartFileEmptyException.class, NotImageFormException.class})
    public ResponseEntity<Object> handleS3FileUploadError(Exception ex) {
        return buildResponseEntity(
                new ErrorResponse(HttpStatus.FORBIDDEN, ex)
        );
    }

    @ExceptionHandler(InvalidProfileUrlException.class)
    public ResponseEntity<Object> handlePlayerProfileDomainError(Exception ex) {
        return buildResponseEntity(
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex)
        );
    }

}
