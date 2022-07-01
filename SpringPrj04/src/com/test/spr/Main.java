/*=============================================
	Main.java
	- main() 메소드를 포함하는 테스트 클래스
==============================================*/

package com.test.spr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
	public static void main(String[] args)
	{
		// 이제 우리가 직접 객체 생성하지 않는다.
		//MemberList member = new MemberList();
		//-- 객체 생성 과정을 스프링이 담당하게 되므로
		//   인스턴스 생성 구문 삭제~!!!
		
		// 스프링컨테이너로부터 MemberList 객체 얻어옴
		// 그러려면 생성해달라고 말해놓는게 필요함
		// '너가 필요한 dao는 내가 설계해놓을테니까,
		// 필요하면 끼워서 쓰면 돼' → applicationContext.xml
		
		// 스프링 환경 설정 파일로 만든 『applicationContext.xml』 에서
		// MemberList 클래스 기반의 객체를 얻어올 수 있도록 처리
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		MemberList member = context.getBean("member", MemberList.class);
		//-- 이렇게 부르면 형변환할 필요 없다.
		
		member.print();
	}
	
}
