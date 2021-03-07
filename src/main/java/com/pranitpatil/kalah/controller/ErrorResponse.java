package com.pranitpatil.kalah.controller;

import org.slf4j.MDC;

public class ErrorResponse {

    private String errorMessage;
    private String userMessage;
    private String requestId;

    public ErrorResponse(String errorMessage, String userMessage) {
        this.errorMessage = errorMessage;
        this.userMessage = userMessage;
        this.requestId = MDC.get("requestID");
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getRequestId() {
        return requestId;
    }
}
