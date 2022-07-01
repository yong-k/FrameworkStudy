/*====================================================
 	Main.java
 	- main() 메소드를 포함하고 있는 테스트 클래스 
=====================================================*/

// ctrl+f11 누르고 
package com.test.spr;

// ▒ Spring Framework 환경설정 메모도 보기

public class Main
{
	public static void main(String[] args)
	{
		// [스프링이 처리하고 관리하게 될 영역] → 사실 우리 눈에 안 보임
		// 우리 눈으로 보려고 
		// 지금은 코드로 작성하지만 나중에는
		// → 스프링 컨테이너 안에서 벌어지게 될 상황
		//---------------------------------------------------------------
		
		// ① RecordImpl1(Record) 객체 생성 과정
		// 인터페이스로 직접 객체/인스턴스 생성 불가능하니 아래 구문 불가
		//Record rec1 = new Record();
		//					Record interface를 implements한 Class
		//				  -------------
		Record rec1 = new RecordImpl1();	//-- ★업캐스팅	/ dependency (의존 객체)
		//-- 실제 생성한건 RecordImpl1 Type인데, 만들어진건 Record Type이니까
		//   → 업캐스팅
		
		// ② RecordImpl2(Record) 객체 생성 과정
		Record rec2 = new RecordImpl2();	//-- ★업캐스팅	/ dependency (의존 객체)
		
		
		// RecordViewImpl(RecordView) 객체 생성 과정
		// --------------
		// └→ 객체만 생성해두면 끝나는게 아니라 Record 객체가 필요하다.
		//      그런데 직접 Record객체는 없고,
		//      Record(의존 객체 - RecordImpl1 / RecordImpl2 객체 필요)
		
		RecordView view = new RecordViewImpl();
		
		// check~!!!
		// 의존 객체 주입
		//-- view 만든거에 setRecord() 메소드 호출하면서 rec1 넣어줄 수 있음
		//-- 의존 객체 주입 방법을 사용하게 되면
		//   느슨한 결합을 구성할 수 있게 되고
		//   한 쪽의 변동 상황이 다른 쪽에 영향을 덜 줄 수 있게 된다.
		view.setRecord(rec1);		//-- setter injection
									//   (setter를 통한 주입)
		
		//---------------------------------------------------------------
		
		view.input();
		view.output();
	}
}
