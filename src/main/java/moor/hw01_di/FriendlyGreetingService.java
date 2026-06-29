package moor.hw01_di;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class FriendlyGreetingService implements GreetingService{

    @Override
    public String greet(String name) {

        return "Привет, " + name + "! Рад тебя видеть)";
    }
}
