package org.example;

import org.example.exception.EmailSendingException;

public interface EmailSender {
    void sendEmail(String recipient, String subject, String body) throws EmailSendingException;
    void sendEmail(String recipient, String subject, String body, String extraSubject) throws EmailSendingException;
}
