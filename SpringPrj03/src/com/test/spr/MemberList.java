/*==================================================
	MemberList.java
	- DAO 클래스를 참조하는 클래스
	- 의존관계 설정 (DAO 클래스를 의존객체로 설정)
	- print() 메소드 구현
===================================================*/

package com.test.spr;

public class MemberList
{
	// 처음 연결하는 클래스 정보
	/*
	private OracleDAO dao;
	
	// setter 말고 생성자로 처리해보겠음
	public MemberList()
	{
		// MemberList 입장에서 의존 객체 OracleDAO
		dao = new OracleDAO();
	}
	*/
	
	// 두 번째 연결하는 클래스 정보
	private MssqlDAO dao;
	
	public MemberList()
	{
		// MemberList 입장에서 의존 객체 MssqlDAO
		dao = new MssqlDAO();
	}
	
	// print() 메소드 구현
	public void print()
	{
		try
		{
			for (MemberDTO dto : dao.list())
			{
				System.out.printf("%14s %4s %15s %15s\n"
						, dto.getId(), dto.getName()
						, dto.getTel(), dto.getEmail());
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
