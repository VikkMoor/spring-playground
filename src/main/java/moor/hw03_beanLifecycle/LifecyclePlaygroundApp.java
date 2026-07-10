package moor.hw03_beanLifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LifecyclePlaygroundApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        AppLogger logger1 = context.getBean(AppLogger.class);
        AppLogger logger2 = context.getBean(AppLogger.class);
        System.out.println("logger1 == logger2 ? " + (logger1 == logger2));

        OperationContext ctx1 = context.getBean(OperationContext.class);
        OperationContext ctx2 = context.getBean(OperationContext.class);
        OperationContext ctx3 = context.getBean(OperationContext.class);
        System.out.println("ctx1 == ctx2 ? " + (ctx1 == ctx2));

        context.close();
    }
}
