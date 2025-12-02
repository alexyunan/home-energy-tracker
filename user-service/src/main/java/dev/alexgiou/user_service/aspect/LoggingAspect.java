package dev.alexgiou.user_service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

  @Pointcut("execution(* dev.alexgiou.user_service.service.*.*(..))")
  public void serviceMethods() {
    // Pointcut for all methods in service package
  }

  @Before("serviceMethods()")
  public void logBeforeServiceMethods(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    Object[] args = joinPoint.getArgs();
    log.info("Executing method: {} with arguments: {}", methodName, args);
  }

  @AfterReturning(pointcut = "serviceMethods()", returning = "result")
  public void logAfterReturningServiceMethods(JoinPoint joinPoint, Object result) {
    String methodName = joinPoint.getSignature().getName();
    log.info("Method {} executed successfully with result: {}", methodName, result);
  }
}
