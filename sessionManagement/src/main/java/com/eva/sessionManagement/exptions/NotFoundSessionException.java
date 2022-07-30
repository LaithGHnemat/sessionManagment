package com.eva.sessionManagement.exptions;

public class NotFoundSessionException extends RuntimeException {
    public NotFoundSessionException(String messages) {
        super(messages);
    }
}
