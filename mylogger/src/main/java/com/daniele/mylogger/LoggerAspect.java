package com.daniele.mylogger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

        List<String> parameterList = getParameterList(parameters);

        logMethodInfo(signature, parameterList);
	}

    @Around("@annotation(LogExecutionTime)")
	public Object aroundFindAllLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
		Instant starts = Instant.now();

		Object result;

		try {
			result = proceedingJoinPoint.proceed();
		} catch (Exception e) {
			throw e;
		}
		
		Instant ends = Instant.now();

        List<String> parameterList = getParameterList(proceedingJoinPoint.getArgs());
        logMethodInfo(signature, parameterList);
        logMethodDuration(starts, ends);

        return result;
	}

	/*@Around("@annotation(LogMethodException)")
	public Object atMethodException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();

        Object result;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            logMethodException(signature, e);
            throw e;
        }

        List<String> parameterList = getParameterList(proceedingJoinPoint.getArgs());
        logMethodInfo(signature, parameterList);

        return result;
	}*/

    @AfterThrowing(pointcut="execution(@LogMethodException * *(..))", throwing="ex")
    public void atMethodException(JoinPoint jp, RuntimeException ex) throws Throwable {
        System.out.println("Exception thrown by: " + jp.getSignature().getName());
        throw ex;
    }

    private void logMethodInfo(@NotNull MethodSignature signature, @NotNull List<String> parameterList) {
        logger.info("Method: " + signature.getMethod().getName()
                + ", Class: " + signature.getDeclaringType().getName()
                + ", Return type: " + signature.getReturnType().getName());

        logger.info("Arguments: " + parameterList);
    }

    private void logMethodDuration(Instant starts, Instant ends) {
	    logger.info("Duration: " + Duration.between(starts, ends).toMillis());
    }

    private void logMethodException(MethodSignature signature, Throwable e) {
	    logger.info("An exception occurred during the execution of: " + signature.getMethod().getName()
                + ", Exception: " + e.getLocalizedMessage());
    }

    private List<String> getParameterList(Object[] parameters) {
        return Arrays.stream(parameters)
                .map(parameter -> parameters.length > 1 ? parameter.toString() +  ", " : parameter.toString())
                .collect(Collectors.toList());
    }
}