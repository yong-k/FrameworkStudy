/*==============================================
	CalculatorImpl.java
	- 클래스
	- Calculator 인터페이스를 구현하는 클래스
	- 주 업무, 보조 업무가 함께 처리되는 형태
===============================================*/

package com.test.spr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;


// 보조업무의 코드 중복되는거 정도 보면 된다.
public class CalculatorImpl implements Calculator
{
	// 주 업무(core concern) 진행을 위한 메소드 구현
	@Override
	public int add(int x, int y)
	{
		int result = 0;
		
		// 보조 업무(cross-cutting concern) 설정
		//-- 시간 측정(Around Advice)
		//   └→ 이 log를 쓰려고 jar 파일 추가했다.
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("처리 시간 측정 시작 -----------------");
		//-- log 는 콘솔창에 빨간색으로 나온다.
		
		
		// 주 업무(core concern) 실행 내용
		result = x + y;
		
		System.out.printf("%d + %d = %d\n", x, y, result);
		
		sw.stop();
		log.info("처리 시간 측정 종료 -----------------");
		log.info(String.format("경과시간 : %s/1000초", sw.getTotalTimeMillis()));
	
		return result;
	}

	@Override
	public int sub(int x, int y)
	{
		int result = 0;
		
		// 보조 업무(cross-cutting concern) 설정
		//-- 시간 측정(Around Advice)
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("처리 시간 측정 시작 -----------------");
		
		
		// 주 업무(core concern) 실행 내용
		result = x - y;
		
		System.out.printf("%d - %d = %d\n", x, y, result);
		
		sw.stop();
		log.info("처리 시간 측정 종료 -----------------");
		log.info(String.format("경과시간 : %s/1000초", sw.getTotalTimeMillis()));
		
		return result;
	}

	@Override
	public int multi(int x, int y)
	{
		int result = 0;
		
		// 보조 업무(cross-cutting concern) 설정
		//-- 시간 측정(Around Advice)
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("처리 시간 측정 시작 -----------------");
		
		
		// 주 업무(core concern) 실행 내용
		result = x * y;
		
		System.out.printf("%d x %d = %d\n", x, y, result);
		
		sw.stop();
		log.info("처리 시간 측정 종료 -----------------");
		log.info(String.format("경과시간 : %s/1000초", sw.getTotalTimeMillis()));
		
		return result;
	}

	@Override
	public int div(int x, int y)
	{
		int result = 0;
		
		// 보조 업무(cross-cutting concern) 설정
		//-- 시간 측정(Around Advice)
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("처리 시간 측정 시작 -----------------");
		
		
		// 주 업무(core concern) 실행 내용
		result = x / y;
		
		System.out.printf("%d / %d = %d\n", x, y, result);
		
		sw.stop();
		log.info("처리 시간 측정 종료 -----------------");
		log.info(String.format("경과시간 : %s/1000초", sw.getTotalTimeMillis()));
		
		return result;
	}

}
