package moor.hw01_di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GreetingPrinterSetter {
    private GreetingService greetingService;

    @Autowired
    public void setGreetingService(
            @Qualifier("formalGreetingService")
            GreetingService greetingService) {

        this.greetingService = greetingService;
    }

    public void print(String name) {
        String text = greetingService.greet(name);
        System.out.println(text);
    }
}
