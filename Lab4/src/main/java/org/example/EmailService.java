package org.example;

import org.example.exception.EmailSendingException;

public class EmailService {
    private final EmailSender emailSender;

    public EmailService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String recipient, String subject, String body) {
        try {
            if (body.isEmpty()) {
                return;
            }
            emailSender.sendEmail(recipient, subject, body);
        } catch (EmailSendingException e) {
            throw e;
        }
    }

    public void sendEmail(String recipient, String subject, String body, String extraSubject) {
        try {
            if (body.isEmpty()) {
                return;
            }
            emailSender.sendEmail(recipient, subject, body, extraSubject);
        } catch (EmailSendingException e) {
            throw e;
        }
    }
}
