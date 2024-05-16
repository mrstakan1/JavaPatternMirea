package ru.example;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import ru.example.entities.Subject;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class CustomEmailSender{
    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(Subject subject) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("example@gmail.com");
        mailMessage.setTo("a_example@mail.ru");
        mailMessage.setSubject("Добавлено x");
        mailMessage.setText("Добавлено: " + subject.toString());
        javaMailSender.send(mailMessage);
    }
}
