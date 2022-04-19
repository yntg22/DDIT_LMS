package kr.or.ddit.advice;

import org.aspectj.lang.ProceedingJoinPoint;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingAdvice {
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info(" 비즈니스 로직{} 시작 ==============>", joinPoint);
		long start = System.currentTimeMillis();
		// target 호출
		Object[] args = joinPoint.getArgs();
		Object retValue = joinPoint.proceed(args);
		long end = System.currentTimeMillis();
		log.info("  비즈니스 로직{} 종료 : {} ms ==============>"
					, joinPoint, (end-start));
		return retValue;
	}
}
