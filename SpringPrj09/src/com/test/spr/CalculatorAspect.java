/*=======================================
	CalculatorAspect.java
	- 보조 업무 수행 클래스
	- 보조 업무 적용, 주 업무 호출
========================================*/

// ※ Spring AOP Proxy 클래스를 만들기 위해서 
//    MethodInterceptor 인터페이스를 구현하는 클래스로 설계한다.
//    ----------------------------
//      └→ 스프링에서 제공해줌
package com.test.spr;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

//										가로채는 역할 수행하는 인터페이스
//										: 핵심메소드 → invoke()
//										-------------------
public class CalculatorAspect implements MethodInterceptor
{
	// 보조 업무 적용 및 주 업무 호출 과정 추가
	// 어제 쓴 invoke() 와 크게 다르지 않은데, 매개변수가 다름
	// 아래 invoke()에서 매개변수는 어제 invoke() 에서의 method 변수역할 수행함
	// → 그래서 비교하라고 method로 변수이름 설정함
	// 우리가 정의한 메소드 아님 → return 값은 우리가 얻으려는 값이 아님
	// 007 가방으로 넘겨주는 값!
 	@Override
	public Object invoke(MethodInvocation method) throws Throwable
	{
		// 보조 업무(cross-cutting concern) 설정
		//-- 시간 측정, 로그 기록(Around Advice)
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("처리 시간 측정 시작 -----------------------------");
		
		// 주 업무(core concern) 호출 부분~!!!
		Object result = method.proceed();
		
		
		sw.stop();
		log.info("처리 시간 측정 종료 -----------------------------");
		log.info(String.format("경과시간 : %s/1000초", sw.getTotalTimeMillis()));
		
		return result;
	
	}
	
}
