package moor.hw02_beanscope;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope("prototype")
public class TaskContext {
    private final String id = UUID.randomUUID().toString();

    public String getID() {
        return id;
    }
}
