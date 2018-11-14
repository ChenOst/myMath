import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myMath.Monom;
import myMath.Polynom;

class testPolynom {
	//constructor
	@Test 
	public void test_constructor1() throws Exception{
		try {
			Polynom p=new Polynom("FGH");
			fail( "My method didn't throw when I expected it to" );
		} 
		catch (Exception expectedException) {
		}
	}
	@Test 
	public void test_constructor2() throws Exception{
		Polynom p1=new Polynom("2x--4");
		Polynom p2= new Polynom("2x+4");
		if (!p1.equals(p2))
			fail("fail");
	}
	@Test 
	public void test_constructor3() throws Exception{
		Polynom p1=new Polynom("5x^2+(-4x)-(2)*x^0");
		Polynom p2= new Polynom("5x^2-4x-2");
		if (!p1.equals(p2))
			fail("fail");
	}
	@Test 
	public void test_constructor4() throws Exception{
		Polynom p1=new Polynom();
		Polynom p2= new Polynom("0");
		if (!p1.equals(p2))
			fail("fail");
	}
	@Test 
	public void test_constructor5() throws Exception{
		String s=null;
		try {
			Polynom p=new Polynom(s);
			fail( "My method didn't throw when I expected it to" );
		} 
		catch (Exception expectedException) {
		}
	}
	//isZero

	@Test
	public void test_isZero1() throws Exception {
		Polynom p1=new Polynom("0");
		if (p1.isZero()!=true)
			fail("fail") ;
	}

	@Test
	public void test_isZero2() throws Exception {
		Polynom p1=new Polynom("2x^2");
		if (p1.isZero()==true)
			fail("fail");
	}

	//addMonom


	@Test
	public void test_addMonom1() throws Exception {
		Monom a= new Monom("7x^4");
		Polynom p1=new Polynom();
		p1.add(a);
		Polynom p2= new Polynom ("7x^4");
		if (!p1.equals(p2))
			fail("fail");
	}
	@Test
	public void test_addMonom2() throws Exception {
		Monom a= new Monom();
		Polynom p1=new Polynom();
		p1.add(a);
		Polynom p2= new Polynom ("0");
		if (!p1.equals(p2))
			fail("fail");
	} 
	@Test
	public void test_addMonom3() throws Exception {
		Monom a= new Monom("X^2");
		Polynom p1=new Polynom();
		p1.add(a);
		Polynom p2= new Polynom ("x^2");
		if (!p1.equals(p2))
			fail("fail");
	} 
	@Test
	public void test_addMonom4() throws Exception {
		Monom a= new Monom();
		Polynom p1=new Polynom("x^2");
		p1.add(a);
		Polynom p2= new Polynom ("x^2");
		if (!p1.equals(p2))
			fail("fail");
	} 
	
	
	//addPolynom
	@Test
	public void test_addPolynom1() throws Exception {
		Polynom p1=new Polynom("2x^2+3");
		Polynom p2=new Polynom();
		p2.add(p1);
		Polynom p3= new Polynom ("2x^2+3");
		if (!p2.equals(p3))
			fail("fail");
		
	} 
	@Test
	public void test_addPolynom2() throws Exception {
		Polynom p1=new Polynom("2x^2+3");
		Polynom p2=new Polynom("5x^5+3x^2+4");
		p2.add(p1);
		Polynom p3= new Polynom ("5x^5+5x^2+7");
		if (!p2.equals(p3))
			fail("fail");
	} 
	@Test
	public void test_addPolynom3() throws Exception {
		Polynom p1=new Polynom();
		Polynom p2=new Polynom();
		p2.add(p1);
		if(!p2.isZero())
			fail("fail");
			
	} 
	@Test
	public void test_addPolynom4() throws Exception {
		Polynom p1=new Polynom("3x^5-1");
		Polynom p2=new Polynom("-3x^5+1");
		p2.add(p1);
		if (!p2.isZero())
			fail("fail");
	} 
	@Test
	public void test_addPolynom5() throws Exception {
		Polynom p1=new Polynom("2x^2+3");
		Polynom p2=new Polynom();
		p1.add(p2);
		Polynom p3= new Polynom ("2x^2+3");
		if (!p1.equals(p3))
			fail("fail");
		
	} 
	
	//f(x)
	@Test
	public void test_f1() throws Exception {
		Polynom p1=new Polynom("7x^4+6.4x^3+5x^2+7x");
		double f=p1.f(6.5);
		assertEquals(14509.7875, f);
	} 
	@Test
	public void test_f2() throws Exception {
		Polynom p1=new Polynom("7x^4+8.4x^3");
		double f=p1.f(10);
		assertEquals(78400,f);
	} 
	
	//derivative
	@Test
	public void test_derivative1() throws Exception {
		Polynom p1=new Polynom("7x^4+8x^3");
		Polynom p2=new Polynom("28x^3+24*x^2");
		if (!p2.equals(p1.derivative()))
			fail("fail");
	}
	@Test
	public void test_derivative2() throws Exception {
		Polynom p1=new Polynom("5");
		Polynom p2=new Polynom("0");
		if (!p2.equals(p1.derivative()))
			fail("fail");
	} 
	//subtract Monom
	@Test
	public void test_subtractMonom1() throws Exception {
		Monom a= new Monom("7x^4");
		Polynom p1=new Polynom();
		p1.subtract(a);
		Polynom p2= new Polynom ("-7x^4");
		if (!p1.equals(p2))
			fail("fail");
	}
	@Test
	public void test_subtractMonom2() throws Exception {
		Monom a= new Monom();
		Polynom p1=new Polynom();
		p1.subtract(a);
		Polynom p2= new Polynom ("0");
		if (!p1.equals(p2))
			fail("fail");
	} 

	@Test
	public void test_subtractMonom3() throws Exception {
		Monom a= new Monom();
		Polynom p1=new Polynom("x^2");
		p1.subtract(a);
		Polynom p2= new Polynom ("x^2");
		if (!p1.equals(p2))
			fail("fail");
	} 
	
	
	//addPolynom
	@Test
	public void test_subtractPolynom1() throws Exception {
		Polynom p1=new Polynom("2x^2+3");
		Polynom p2=new Polynom();
		p2.substract(p1);
		Polynom p3= new Polynom ("-2x^2-3");
		if (!p2.equals(p3))
			fail("fail");
		
	} 
	@Test
	public void test_subtractPolynom2() throws Exception {
		Polynom p1=new Polynom("2x^2+3");
		Polynom p2=new Polynom("5x^5+3x^2+4");
		p2.substract(p1);
		Polynom p3= new Polynom ("5x^5+x^2+1");
		if (!p2.equals(p3))
			fail("fail");
	} 
	@Test
	public void test_subtractPolynom3() throws Exception {
		Polynom p1=new Polynom();
		Polynom p2=new Polynom();
		p2.substract(p1);
		if(!p2.isZero())
			fail("fail");
			
	} 
	@Test
	public void test_subtractPolynom4() throws Exception {
		Polynom p1=new Polynom("3x^5-1");
		Polynom p2=new Polynom("3x^5-1");
		p1.substract(p2);
		if (!p1.isZero())
			fail("fail");
	} 
	@Test
	public void test_subtractPolynom5() throws Exception {
		Polynom p1=new Polynom("2x^2+3");
		Polynom p2=new Polynom();
		p1.add(p2);
		Polynom p3= new Polynom ("2x^2+3");
		if (!p1.equals(p3))
			fail("fail");
		
	} 
	
	//area
	@Test
	public void test_area1() throws Exception {
		Polynom p1=new Polynom("5x^7+4x^8");
		double area=Math.round(p1.area(-10, 10, 0.01)*100.0)/100.0;
		if (area!=155374.4)
			fail("fail");		
	} 
	@Test
	public void test_area2() throws Exception {
		Polynom p1=new Polynom("3.0*x^2 + 9.0*x");
		double area=Math.round(p1.area(-10, 10, 0.04)*100.0)/100.0;
		if (area!=1307.88)
			fail("fail");		
	} 
	@Test
	public void test_area3() throws Exception {
		Polynom p1=new Polynom("0.2x^4-1.5x^3+3x^2-x-5");
		double area=p1.area(-2, 6, 0.01);
		if (area!=50.647960976680025)
			fail ("fail");	
	} 
	//underarea
	@Test
	public void test_Underarea3() throws Exception {
		Polynom p1=new Polynom("0.2x^4-1.5x^3+3x^2-x-5");
		double area=p1.underArea(-2, 6, 0.01);
		if (area!=25.18363382194)
			fail ("fail");
		
	} 
	//abovearea
	@Test
	public void test_aboveArea3() throws Exception {
		Polynom p1=new Polynom("0.2x^4-1.5x^3+3x^2-x-5");
		double area=p1.aboveArea(-2, 6, 0.01);
		if (area!=25.464327154740023)
			fail ("fail");
		
	}
	
	
}
