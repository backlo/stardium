package com.bb.stardium.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
    private final HttpStatus status;
    private final ResponseErrorEntity errorEntity;

    public ErrorResponse(HttpStatus status, Exception ex) {
        this.status = status;
        this.errorEntity = ResponseErrorEntity.builder()
                .code(parseExceptionName(ex))
                .message(ex.getMessage())
                .build();
    }

    private int parseExceptionName(Exception ex) {
        return ErrorCode.findCode(ex);
    }

}
