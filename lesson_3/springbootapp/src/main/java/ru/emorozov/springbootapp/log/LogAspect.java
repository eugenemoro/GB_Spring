package ru.emorozov.springbootapp.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

	@Pointcut("execution(* ru.emorozov.springbootapp.controller..*.*(..))")
	private void getName(){
	}

	@Before("getName()")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println(joinPoint);
	}
}
