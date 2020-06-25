package com.bb.stardium.resource.api.club.handler;

import com.bb.stardium.domain.club.exception.MasterAndClubNotMatchedException;
import com.bb.stardium.domain.club.exception.NotAllowCityException;
import com.bb.stardium.domain.club.exception.NotAllowSectionException;
import com.bb.stardium.error.exception.IllegalPageFormException;
import com.bb.stardium.error.handler.AbstractApiExceptionHandler;
import com.bb.stardium.error.model.ErrorResponse;
import com.bb.stardium.service.club.exception.NotFoundClubsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClubApiExceptionHandler extends AbstractApiExceptionHandler {

    @ExceptionHandler({NotAllowCityException.class, NotAllowSectionException.class, NotFoundClubsException.class, IllegalPageFormException.class})
    public ResponseEntity<Object> handleBadRequestClubRequest(Exception ex) {
        return buildResponseEntity(
                new ErrorResponse(HttpStatus.BAD_REQUEST, ex)
        );
    }

    @ExceptionHandler(MasterAndClubNotMatchedException.class)
    public ResponseEntity<Object> handleForbiddenClubRequest(Exception ex) {
        return buildResponseEntity(
                new ErrorResponse(HttpStatus.FORBIDDEN, ex)
        );
    }

}
