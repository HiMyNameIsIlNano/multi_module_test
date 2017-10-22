package com.daniele.mylogger;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
	/* 
	 * Aspects are not instantiated by the spring container. Rather, they are instantiated based on their perclause 
	 *  (i.e. perthis, pertarget, and aspects without per-clauses are singletons). Since they are not instantiated by 
	 *  the spring container, field injection and constructor injection will not work for them.
	 * 
	 *  @Autowired
	 *  LoggerFactoryService loggerFactory;
	 *  
	 *  @Autowired
	 *  public LoggerAspect(LoggerFactoryService loggerFactory) {
	 *  	// constructor
	 *  }
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Before("@annotation(LogMethodExecution)")
	public void beforeMethodExecution(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Object[] parameters = joinPoint.getArgs();
		List<String> parameterList = Arrays.stream(parameters)
			.map(parameter -> parameter.toString() +  ", ")
			.collect(Collectors.toList());
		logger.info("Executing: " + signature);
		logger.info("Arguments: " + parameterList);
	}
				
	@Around("@annotation(LogExecutionTime)")
	public Object aroundFindAllLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
		Instant starts = Instant.now();
		
		Object result = null;
		
		try {
			result = proceedingJoinPoint.proceed();	
		} catch (Exception e) {
			// This could be good for a monitoring tool!
			logger.info("Method: " + signature + "threw an exception, Exception: " + e.getMessage());
			throw e;
		}
		
		Instant ends = Instant.now();
		logger.info("Method: " + signature + ", duration: " + Duration.between(starts, ends).toMillis());
		return result;
	}
}