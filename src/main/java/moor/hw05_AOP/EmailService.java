package moor.hw05_AOP;

import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @NotNullArgs
    public void send(String to, String text) {
        System.out.println("Sending email to: " + to);
        System.out.println("Text: " + text);
    }
}
