package com.bb.stardium.error.exception;

public class FileUploadException extends RuntimeException {
    public FileUploadException(String message) {
        super("파일 업로드 오류 : " + message);
    }
}
