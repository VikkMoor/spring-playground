package moor.hw04_property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PlannerService {
    private final boolean enabled;
    private final PlannerProperties properties;

    public PlannerService(
            @Value("${planner.enabled:false}") boolean enabled,
            PlannerProperties properties
    ) {
        this.enabled = enabled;
        this.properties = properties;
    }

    public void printConfig() {
        System.out.println("enabled=" + enabled);
        System.out.println("defaultDuration=" + properties.getDefaultDuration());
        System.out.println("batchSize=" + properties.getBatchSize());
    }
}
