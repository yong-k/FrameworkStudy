/*==================================
	CalculatorAfterThrowing.java
	- After Throwing Advice 구성
===================================*/

package com.test.spr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.ThrowsAdvice;

// 예외가 어떻게 발생할 지 모르기 때문에, 
// override 할 메소드 없음
// 명시적 의미로 interface implements 한 거!

// 얘 사용하려면 주 업무 처리과정에서 예외 발생해야하므로
// CalculatorImpl로 가서 작업
public class CalculatorAfterThrowing implements ThrowsAdvice
{
	// 매개변수/파라미터 잘못 넘겼을 때의 예외처리
	public void afterThrowing(IllegalArgumentException e) throws Throwable
	{
		Log log = LogFactory.getLog(this.getClass());
		log.info("After Throwing Advice 수행 ---------------------------");
		log.info("주 업무 실행 과정에서 예외 발생 시 처리되는 사후 업무");
		log.info(e.toString());
		log.info("--------------------------- After Throwing Advice 수행");
	}
}
