package moor.hw05_AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotNullArgsAspect {

    @Around("@annotation(moor.hw05_AOP.NotNullArgs)")
    public Object check(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();

        for (int i = 0; i < args.length; i++) {
            if (args[i] == null) {
                String method = pjp.getSignature().getName();
                throw new IllegalArgumentException(
                        method + " arg[" + i + "] is null"
                );
            }
        }
        return pjp.proceed();
    }
}
