package com.yedam.app.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service	// 컨테이너에 등록
@Aspect		// advice + aspect
public class LogAdvice {
	
	@Pointcut("execution(* com.yedam..*Impl.*(..))")
	public void allpointcut() {}
	
	@Before("allpointcut()")
	public void printLog(JoinPoint jp) {
		
		// 메서드명
		String methodName = jp.getSignature().getName();
		
		// 인수(argument)
		Object[] args = jp.getArgs();
		Object arg1 = args != null ? args[0] : "";
		
		System.out.println("[사전처리] beforeLog " + methodName + "\n arg: " + arg1);
	}
}
