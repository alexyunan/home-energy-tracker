package dev.alexgiou.device_service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

  @Pointcut("execution(* dev.alexgiou.device_service.service.*.*(..))")
  public void serviceMethods() {

  }

  @Before("serviceMethods()")
  public void logBeforeServiceMethods(org.aspectj.lang.JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    Object[] args = joinPoint.getArgs();
    log.info("Executing method: {} with arguments: {}", methodName, args);
  }

  @AfterReturning(pointcut = "serviceMethods()", returning = "result")
  public void logAfterReturningServiceMethods(org.aspectj.lang.JoinPoint joinPoint, Object result) {
    String methodName = joinPoint.getSignature().getName();
    log.info("Method {} executed successfully with result: {}", methodName, result);
  }
}
