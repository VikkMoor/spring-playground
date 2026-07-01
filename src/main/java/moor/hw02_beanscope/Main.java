package moor.hw02_beanscope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        JobRunner runner = context.getBean(JobRunner.class);

        runner.runOnce();
        runner.runOnce();
        runner.runOnce();

        context.close();

    }
}
