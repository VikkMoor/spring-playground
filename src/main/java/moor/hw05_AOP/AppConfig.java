package moor.hw05_AOP;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "moor.hw05_AOP")
@EnableAspectJAutoProxy
public class AppConfig {

}