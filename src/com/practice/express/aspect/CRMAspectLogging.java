package com.practice.express.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CRMAspectLogging {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Pointcut("execution(* com.practice.express.controller.*.*(..))")
	public void forControllerPackage() {
	}

	@Pointcut("execution(* com.practice.express.service.*.*(..))")
	public void forServicePackage() {
	}

	@Pointcut("execution(* com.practice.express.dao.*.*(..))")
	public void fordaoPackage() {
	}

	@Pointcut("forControllerPackage()||forServicePackage()||fordaoPackage()")
	public void forAppFlow() {
	}

	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toShortString();
		logger.info("====>>> in @Before: calling method" + method);

		Object[] args = joinPoint.getArgs();
		for (Object object : args) {
			System.out.println("==>>temp args :" + object);
		}
	}

	@AfterReturning(pointcut = "forAppFlow()", returning = "result")
	public void afterReturn(JoinPoint joinPoint, Object result) {

		String method = joinPoint.getSignature().toShortString();
		logger.info("====>>> in @Afterreturning: calling method" + method);

		logger.info("====>>> in @Afterreturning: calling method" + result);
	}
}
