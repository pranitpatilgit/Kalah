package com.pranitpatil.kalah.exception;

public class KalahException extends RuntimeException{

    private String userMessage;

    public KalahException(String userMessage) {
        super(userMessage);
        this.userMessage = userMessage;
    }

    public KalahException(Throwable cause, String userMessage) {
        super(cause);
        this.userMessage = userMessage;
    }

    public KalahException(Throwable cause) {
        super(cause);
        this.userMessage = "Something went wrong please try again.";
    }

    public String getUserMessage() {
        return userMessage;
    }
}
