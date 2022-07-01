/*===============================================
 	Record.java
 	- 인터페이스 
 	  : 모든 메소드가 선언만 되어있어야 한다.
 	    정의 구문 없이
================================================*/

package com.test.spr;

public interface Record
{
	public void setKor(int kor);
	public int getKor();
	
	public void setEng(int eng);
	public int getEng();
	
	public void setMat(int mat);
	public int getMat();
	
	public int getTotal();
	
	public double getAvg();
}
