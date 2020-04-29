package com.bb.stardium.v1.bench.domain;

public class IllegalPlayTimeException extends RuntimeException {
    public IllegalPlayTimeException() {
    }

    public IllegalPlayTimeException(String message) {
        super(message);
    }
}
