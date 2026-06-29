package moor.hw01_di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GreetingPrinterField {

    @Autowired
    private GreetingService greetingService;

    public void print(String name) {
        String text = greetingService.greet(name);
        System.out.println(text);
    }
}
