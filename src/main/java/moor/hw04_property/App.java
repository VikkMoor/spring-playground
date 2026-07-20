package moor.hw04_property;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        PlannerService plannerService =
                context.getBean(PlannerService.class);

        plannerService.printConfig();

        context.close();
    }
}
