package moor.hw01_di;

import org.springframework.stereotype.Service;

@Service
public class FormalGreetingService implements GreetingService {

    @Override
    public String greet(String name) {
        return "Здравствуйте, " + name + ".";
    }
}
