package co.com.demo.proxy.aop.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InterfaceAspect {

    private static final Logger log = LoggerFactory.getLogger(InterfaceAspect.class);
    private final ObjectMapper objectMapper;

    public InterfaceAspect(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Pointcut(value = "execution(* co.com.demo.proxy.cash.CashService+.*(..))")
    public void onImplementationCashService() {
//pointcut to execute on every IMPLEMENTED methods of the interface declared as CashService
    }

    @Around(value = "onImplementationCashService()")
    public Object doOnExecution(ProceedingJoinPoint pjp) throws Throwable {
        var methodInvoked = pjp.getSignature().getDeclaringTypeName();
        var jsonArgs = objectMapper.writeValueAsString(pjp.getArgs());
        log.info("Method invoked {} with Given Args: {}", methodInvoked, jsonArgs);
        return pjp.proceed();
    }

}
