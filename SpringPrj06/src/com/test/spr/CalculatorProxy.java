/*==============================================
	CalculatorProxy.java
	- 프록시 클래스
	- 보조 업무 적용 및 주 업무 호출 과정
===============================================*/

// ※ Proxy 클래스를 만드는 여러가지 방법들 중
//    비교적 쉽고 직관적인 방법은
//    InvocationHandler 인터페이스를 구현하는 클래스를 만드는 것이다.
//    ----------------------------
//     └→ like 실리콘 가면 만들어주는 거
/*
상황 예)
내가 태형이한테 짜장면 시켰는데, 아린이가 가로채서 만들어줌
아린이가 태형가면, 옷 입고 태형 행세하고 있는 상황
나는 태형이가 만든 줄 알고 있음
*/
package com.test.spr;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

// 대상과 똑같은 얼굴 실리콘 모형 만들어주는 007 가방 implements 함
//											--------
// 누구 얼굴 모형만들건지 너가 정의해서 쓰라고 interface 형식으로 만들어놓음
public class CalculatorProxy implements InvocationHandler
{			//--------------
			//   아린
	
	// 주요 속성 구성
	
	// target → 가짜가... 진짜 행세를 하게 될 대상
	private Object target;
	//			   ------
	//              태형
	
	
	// 생성자 정의
	public CalculatorProxy(Object target)
	{
		this.target = target;
	}

	// 보조 업무 적용 및 주 업무 호출 과정 추가
	// 실제 invoke() 어떻게 활용하는지 우리가 알 필요 없음
	//-- method 파라미터는 007가방-패널(태형혈액/머리카락/아빠사진 입력하는) 있으면
	//   007가방과 패널의 연결된 전선 피복 안에 있는 여러 선 중에 '빨간 선' 이다. 
	//   우리가 별로 알 필요 없는 정보
	//-- 우리는 invoke() 라는 메소드 호출하지 않는다.
	//   그럼 그 파라미터 값들도 우리가 넘기는게 아님
	//-- invoke() : 프록시에 의해서 움직이는 메소드. 주 업무 삽입하는 형태로 작업
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{					//		-----	------------- -------------
						//		아린 	내부적으로 			└→ Object가 가지고 있는 프로퍼티들 다 넘기는거
						// 				어떻게 되어있다는 
						//				설정값    
				
		// 보조 업무(cross-cutting concern) 설정
		//-- 시간 측정(Around Advice)
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("처리 시간 측정 시작 -----------------");
		
		
		// 주 업무(core concern) 실행 내용
		Object result = method.invoke(target, args);
		//-- CalculatorProxy()의 반환 타입이 Object 니까 result type을 Object로
		// ex. target: 태형 , args: 태형이의 유전자,혈액,아빠사진 같은 거
		
		sw.stop();
		log.info("처리 시간 측정 종료 -----------------");
		log.info(String.format("경과시간 : %s/1000초", sw.getTotalTimeMillis()));
	
		return result;
	}
	// +,-,x,/ 무슨 요청이 오던 얘를 만나는거임
	
	//→ 007 가방 뚜껑 열면 답 없어짐 ㅎㅎ
	//   그래서 뚜껑은 지금 살짝 닫자는 거
	
	/*
	특정 업무 처리해주는 A기계 있는데 그거랑 똑같이 만든 proxy 인 B 기계가 있다.
	A 기계는 1000원 내면 100원 갖고 900원 돌려주는건데,
	B 기계를 만든 이유는 거기서 20원의 수수료 떼기 위해서!
	
	A 기계(서버)에 (클라이언트의)요청 들어오면 B가 그거 가로채서 처리하는거 
	*/
	
}
