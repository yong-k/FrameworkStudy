/*===========================================
	Main.java
	- main() 메소드가 포함된 테스트 클래스
============================================*/

package com.test.spr;

import java.lang.reflect.Proxy;

public class Main
{
	public static void main(String[] args)
	{
		// 주 업무 실행을 할 수 있는 객체 준비
		// 인터페이스 변수 = new 인터페이스구현클래스();
		// List list = new ArrayList();
		Calculator cal = new CalculatorImpl();
		
		
		// 주 업무 실행에 대한 테스트 → AOP 기법 적용 전
		/*
		int add = cal.add(10, 20);
		System.out.println(add);
		
		int sub = cal.sub(10, 20);
		System.out.println(sub);
		
		int multi = cal.multi(10, 20);
		System.out.println(multi);
		
		int div = cal.div(10, 20);
		System.out.println(div);
		*/
		//-- 보조업무와 강하게 결합되어 있는 주 업무 수행
		
		// AOP 들어오고 
		// - 주 업무와 보조 업무 나누기
		//   CalculatorImpl.java 에서 주 업무와 보조 업무 나눠서 주 업무만 걸러낸 거!
		//  
		
		
		// 주 업무 실행에 대한 테스트 → AOP 기법 적용 후
		
		//									┌→ 이 클래스, 메소드도 우리가 생성한 거 아님
		//									static → Proxy 객체 생성여부와 관련없이 메모리에 올라감
		//							  		-------------------
		Calculator proxy = (Calculator)Proxy.newProxyInstance(
				cal.getClass().getClassLoader()		// 주 업무 실행 클래스에 대한 정보 전달(제공)
													//-- ex) 태형이 정보 전달
				, cal.getClass().getInterfaces()	// 주 업무 실행 클래스의 인터페이스에 대한 정보 전달(제공)
													// (인터페이스는 여러 개 있을 수 있으니까)
													// └→ 프록시는 넘어오는 인터페이스들 다 분석해야 한다.
													//-- ex) 태형이 아버님 정보 전달
				, new CalculatorProxy(cal)			// 보조 업무 실행 클래스에 대한 정보 전달(제공)
				);
		//-- 보조업무를 품 안에 숨기고 있는 Calculator..(주 업무 행세를 하는 proxy)
		// Proxy.newProxyInstance() → Object type 반환한다. → 형변환 해줘야 한다.		
		// cal : 태형이객체 
		// cal.getClass() : 태형이객체의설계도 
		// cal.getClass().getClassLoader() : 태형이객체의설계도의정보를얻어오는과정
		// cal.getClass().getInterfaces()  : 태형이객체를만들어내는설계도의인터페이스
		// new CalculatorProxy(cal) : 아린이 객체 생성해서 태형이 객체 넘겨줌
		
		int add = proxy.add(10, 20);
		System.out.println(add);
		
		int sub = proxy.sub(10, 20);
		System.out.println(sub);
		
		int multi = proxy.multi(10, 20);
		System.out.println(multi);
		
		int div = proxy.div(10, 20);
		System.out.println(div);
	}
}
