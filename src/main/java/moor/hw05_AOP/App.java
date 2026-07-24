package moor.hw05_AOP;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {

        try (var context = new AnnotationConfigApplicationContext(AppConfig.class)) {

            EmailService emailService = context.getBean(EmailService.class);
            emailService.send("boss@mail.com", "Hello");
            emailService.send(null, "Hello");

        }
    }
}