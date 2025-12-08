package dev.alexgiou.device_service.aspect;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExecutionTimeAspect {

  @Pointcut("execution(* dev.alexgiou.device_service.controller.*.*(..))")
  public void controllerMethods() {

  }

  @Around("controllerMethods()")
  public Object measureExecutionTime(org.aspectj.lang.ProceedingJoinPoint joinPoint)
      throws Throwable {
    long start = System.nanoTime();
    try {
      return joinPoint.proceed();
    } finally {
      long executionTime = System.nanoTime() - start;
      long executionTimesMs = TimeUnit.NANOSECONDS.toMillis(executionTime);
      String signature = joinPoint.getSignature().toShortString();
      log.info("Controller method {} executed in {} ms", signature, executionTimesMs);
    }
  }

}
