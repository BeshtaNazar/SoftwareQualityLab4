package org.example.exception;

public class EmailSendingException extends RuntimeException {
    public EmailSendingException(String message){
        super(message);
    }
}
