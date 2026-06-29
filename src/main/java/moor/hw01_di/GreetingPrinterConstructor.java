package moor.hw01_di;

import org.springframework.stereotype.Component;

@Component
public class GreetingPrinterConstructor {

    private final GreetingService greetingService;

    public GreetingPrinterConstructor(GreetingService greetingService) {

        this.greetingService = greetingService;
    }

    public void print (String name) {
        String text = greetingService.greet(name);
        System.out.println(text);
    }
}
