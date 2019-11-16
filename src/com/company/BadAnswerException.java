package com.company;

public class BadAnswerException extends Exception {
    private String message;

    public BadAnswerException(String text) {
        message = text;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
