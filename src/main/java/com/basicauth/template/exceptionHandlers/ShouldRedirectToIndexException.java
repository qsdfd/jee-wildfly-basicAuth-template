package com.basicauth.template.exceptionHandlers;

public class ShouldRedirectToIndexException extends Exception {
    public ShouldRedirectToIndexException() {
    }

    public ShouldRedirectToIndexException(String message) {
        super(message);
    }
}
