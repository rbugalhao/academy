package com.ctw.workstation.exceptions;

import jakarta.ws.rs.core.Response;

public class MyApplicationException extends RuntimeException {
    private final Response.Status status;

    public MyApplicationException(String message, Response.Status status) {
        super(message);
        this.status = status;
    }

    public Response.Status getStatus() {
        return status;
    }
}
