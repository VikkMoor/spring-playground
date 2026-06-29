package moor.hw01_di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        GreetingPrinterConstructor constructorPrinter =
                context.getBean(GreetingPrinterConstructor.class);

        GreetingPrinterField fieldPrinter =
                context.getBean(GreetingPrinterField.class);

        GreetingPrinterSetter setterPrinter =
                context.getBean(GreetingPrinterSetter.class);

        constructorPrinter.print("Vikky");
        fieldPrinter.print("Vikky");
        setterPrinter.print("Vikky");
    }
}