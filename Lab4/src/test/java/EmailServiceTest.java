import org.example.EmailSender;
import org.example.EmailService;
import org.example.exception.EmailSendingException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class EmailServiceTest {
    @Mock
    private EmailSender emailSender;

    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        emailService = new EmailService(emailSender);
    }

    @Test
    public void shouldSendEmailWithCorrectData() {
        String recipient = "recipient@gmail.com";
        String subject = "Test Subject";
        String body = "Test Body";
        emailService.sendEmail(recipient, subject, body);
        verify(emailSender).sendEmail(eq(recipient), eq(subject), eq(body));
    }

    @Test
    public void shouldThrowExceptionWhenSendingFailed() {
        String recipient = "recipient@gmail.com";
        String subject = "Test Subject";
        String body = "Test Body";
        doThrow(new EmailSendingException("Failed to send email")).when(emailSender).sendEmail(anyString(), anyString(), anyString());
        assertThrows(EmailSendingException.class, () -> emailService.sendEmail(recipient, subject, body));
    }

    @Test
    public void shouldNotInvokeSendEmailWhenBodyEmpty(){
        String recipient = "recipient@gmail.com";
        String subject = "Test Subject";
        String body = "";
        emailService.sendEmail(recipient,subject,body);
        verify(emailSender, never()).sendEmail(anyString(),anyString(),anyString());
    }

    @Test
    public void shouldSendEmailWithCorrectExtraSubject(){
        String recipient = "recipient@gmail.com";
        String subject = "Test Subject";
        String body = "body";
        String extraSubject = "extraSub";
        emailService.sendEmail(recipient,subject,body,extraSubject);
        verify(emailSender).sendEmail(eq(recipient),eq(subject),eq(body),eq(extraSubject));
    }
}
