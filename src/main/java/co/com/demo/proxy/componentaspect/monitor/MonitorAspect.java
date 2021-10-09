package co.com.demo.proxy.componentaspect.monitor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
//jdk dynamic proxy, cglibproxy
public class MonitorAspect {


    private static final Logger log = LoggerFactory.getLogger(MonitorAspect.class);

    //jackson 3
    private final ObjectMapper objectMapper = JsonMapper.builder().build();

    @Pointcut(value = "execution(* (@co.com.demo.proxy.componentaspect.monitor.Monitoreable *).*(..))")
    public void executeOnEveryMethodOfAClassAnnotatedWith() {
        //aspect
    }

    //SpEl
    //firma = Signature (inglish)
    @Before(value = "executeOnEveryMethodOfAClassAnnotatedWith()")
    public void executionBefore(JoinPoint joinPoint) throws JsonProcessingException {
        String targetObject = joinPoint.getSignature().getDeclaringTypeName();
        String request = objectMapper.writeValueAsString(joinPoint.getArgs());
        log.info("En Before Target Object: {} , Request: {}", targetObject, request);
        //esta mondá va para kinesis
    }

    @AfterReturning(value = "executeOnEveryMethodOfAClassAnnotatedWith()", returning = "response") //returning
    public void executionAfterReturning(JoinPoint joinPoint, Object response) throws JsonProcessingException {
        String responseAsJson = objectMapper.writeValueAsString(response);
        log.info("En AfterReturning Response Object: {} ", responseAsJson);
        //esta mondá va para kinesis
    }

    @AfterThrowing(value = "executeOnEveryMethodOfAClassAnnotatedWith()", throwing = "throwable") //finally
    public void executionAfter(JoinPoint joinPoint, Throwable throwable) {
        String methodThrows = joinPoint.getSignature().getDeclaringTypeName();
        log.info("En After Throwing Target Exception message: {} over method {}", throwable.getMessage(), methodThrows);
        //esta mondá va para kinesis
    }


    @After(value = "executeOnEveryMethodOfAClassAnnotatedWith()") //finally
    public void executionAfter(JoinPoint joinPoint) {

        String targetObject = joinPoint.getSignature().getDeclaringTypeName();
        log.info("En After Target Object: {} ", targetObject);
        //esta mondá va para kinesis

    }

    @Around(value = "executeOnEveryMethodOfAClassAnnotatedWith()")
    public Object executionAround(ProceedingJoinPoint pjp) throws Throwable {
        MDC.put("keyCustom", "TX-" + UUID.randomUUID());
        var request = objectMapper.writeValueAsString(pjp.getArgs());
        log.info("En Around Request {}", request);
        var proxyResult = pjp.proceed();
        var result = objectMapper.writeValueAsString(proxyResult);
        log.info("En Around Response {}", result);
        MDC.clear();
        return proxyResult;
    }


}
