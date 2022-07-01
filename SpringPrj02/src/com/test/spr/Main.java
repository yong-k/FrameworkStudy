/*====================================================
 	Main.java
 	- main() 메소드를 포함하고 있는 테스트 클래스 
=====================================================*/

// ctrl+f11 누르고 
package com.test.spr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// ▒ Spring Framework 환경설정 메모도 보기

public class Main
{
	public static void main(String[] args)
	{
		// [스프링이 처리하고 관리하게 될 영역] → 사실 우리 눈에 안 보임
		//---------------------------------------------------------------
		/*
		// ① RecordImpl1(Record) 객체 생성 과정
		Record rec1 = new RecordImpl1();	//-- ★업캐스팅	/ dependency (의존 객체)
		
		// ② RecordImpl2(Record) 객체 생성 과정
		Record rec2 = new RecordImpl2();	//-- ★업캐스팅	/ dependency (의존 객체)
		
		
		// RecordViewImpl(RecordView) 객체 생성 과정
		//-- Record(의존 객체 - RecordImpl1 / RecordImpl2 객체 필요)
		
		RecordView view = new RecordViewImpl();
		
		// check~!!!
		// 의존 객체 주입
		//-- 의존 객체 주입 방법을 사용하게 되면
		//   느슨한 결합을 구성할 수 있게 되고
		//   한 쪽의 변동 상황이 다른 쪽에 영향을 덜 줄 수 있게 된다.
		view.setRecord(rec1);		//-- setter injection
									//   (setter를 통한 주입)
		*/
		//---------------------------------------------------------------
		
		
		// ※ 스프링 프레임워크에서는
		//    객체 생성 과정 및 DI(의존객체 주입)에 대한 설정은
		//    스프링 환경 설정 파일로 대체된다. (→ applicationContext.xml)
		
		// 여기서는
		// 위에 우리가 작성한 코드처럼
		// 우리가 직접 객체 생성하고, 생성한 객체에 뭐 주입시켜주고 하는게 아니라,
		//
		// '야 스프링 너가 다 해줘!' 하는 거
		// 
		// 스프링 프레임워크에서 『applicationContext.xml』 파일을 읽어서
		// 생성 및 관리할 객체에 대한 정보를 얻어내야 한다.
		//
		// '야 내가 applicationContext.xml 안에 
		//  너한테 전달해야 될 내용 다 써놨어 거기 봐봐'
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//-- org.springframework.context의 ApplicationContext 선택하고,
		//   문자열로 applicationContext.xml 넣어주기
		
		/*
		우리가 현재 필요한 건 view
		우리 xml에 다 bean 으로 등록해놨다.
		springContainer 안에서는 bean Container 가 돌아간다.
		bean 공장이 생겨서 bean을 생성해낸다.
		enterprise 기반으로 생성한거라, 단일 bean에 집중해서 돌아가는게 아니라
		여러 bean들을 찍어내고, 관리하는 게 비일비재하게 일어나기 때문에
		bean 생성하는 factory들(→ bean factory)이 있는 
		공단(→ applicationContext) 자체가 돌아가게 된다.
		'bean 식별해줬으면 좋겠어'하고 id 설정해서 라벨링을 다 한 것이다.
		*/
		
		// 『getBean()』 메소드를 이용하여 사용자가 원하는 객체를 얻어낸다.
		// 메소드 인자값으로 id 속성값을 지정하게 된다.
		//RecordView view = (RecordViewImpl)context.getBean("view");
		//-- view 라는 식별자 쓰는 bean을 우리에게 넘겨준다.
		//-- getBean() 에 마우스 올려보면 Object 타입으로 넘겨주는거 확인할 수 있다.
		//   그걸 원하는 타입인 RecordViewImpl 타입으로 캐스팅해서 넘겨주면 된다.
		
		// 일단 Object로 받고 우리가 원하는 타입으로 캐스팅해야되니까
		// 스프링 3.0 이후 버전부터 사용 가능한 코드
		RecordView view = context.getBean("view", RecordViewImpl.class);
		//-- 설계도를 직접 넘겨주면 그 해당 타입에 맞게끔 반환시켜준다!!
		
		// Line 76, Line 83 중 아무거나 써도 된다.
		
		// 어제와 비교해서 엄~~청 편해진점
		// 자바코드는 아예 손 안 댄 상태에서
		// xml만 바꿔주면 (<ref bean="record1"/> 부분 바꿔주면)
		// springContainer 가 알아서 넣어준다.
		// 
		// SpringPrj01 에서는 컴파일하고, 배포 후에는 view.setRecord(rec1); 처리 불가
		// 이제는 그럴 필요 없이 자바 코드는 전혀 건드리지 않고,
		// xml에 작성한 설정 정보만 수정하면, 업무로직 바꿔서 처리 가능~!
		
		view.input();
		view.output();
		
		
		// IoC/DI 하는 게
		// 자동차 일체형으로 안 만드는 것처럼
		// 타이어, 엔진, 핸들 따로따로 만들어서 하나 고장나면 차 전체 다 안바꾸게 
		// 그것만 고칠 수 있도록
		// 의존관계 너무 강해지지 않도록 하는 거랑 같다.
		// 자바에서 설명했던 것의 연장선이라고 생각하면 된다.
	}
}
