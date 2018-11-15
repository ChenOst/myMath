import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import junit.framework.Assert;
import myMath.Monom;
import myMath.Polynom;

public class testMonom {
	/**
	 * Testing methods of Polynom class
	 */


	//constructor
	@Test 
	public void test_constructor1() throws Exception{
		try {
			Monom m=new Monom("FGH");
			fail( "My method didn't throw when I expected it to" );
		} 
		catch (Exception expectedException) {
		}
	}

	@Test 
	public void test_constructor2() throws Exception{
		try {
			Monom m=new Monom(1,-2);
			fail( "My method didn't throw when I expected it to" );
		} 
		catch (Exception expectedException) {
		}
	}
	
	@Test 
	public void test_constructor3() throws Exception{
		Monom m1=new Monom(1,2);
		Monom m2=new Monom("x^2");
		if (!m1.isEquals(m2))
		fail("fail");
	}
	@Test 
	public void test_constructor4() throws Exception{
		Monom m1=new Monom("5x^2");
		Monom m2=new Monom(5,2);
		if (!m1.isEquals(m2))
			fail("fail");
	}
	@Test 
	public void test_constructor5() throws Exception{
		Monom m1=new Monom("5* x^2");
		Monom m2=new Monom(5,2);
		if (!m1.isEquals(m2))
			fail("fail");
	}

	@Test 
	public void test_constructor6() throws Exception{
		Monom m1=new Monom("-x");
		Monom m2=new Monom(-1,1);
		if (!m1.isEquals(m2))
			fail("fail");
	}

	@Test 
	public void test_constructor7() throws Exception{
		Monom m1=new Monom("x^2");
		Monom m2=new Monom(1,2);
		if (!m1.isEquals(m2))
			fail("fail");
	}
	@Test
	public void test_constructor8() throws Exception{
		Monom m1=new Monom("5");
		Monom m2=new Monom(5,0);
		if (!m1.isEquals(m2))
			fail("fail");
	}
	@Test
	public void test_constructor9() throws Exception{
		Monom m1=new Monom();
		Monom m2=new Monom(0,0);
		if (!m1.isEquals(m2))
			fail("fail");
	}
	@Test 
	public void test_constructor10() throws Exception{
		String s=null;
		try {
			Monom m=new Monom(s);
			fail( "My method didn't throw when I expected it to" );
		} 
		catch (Exception expectedException) {
		}
	}


	//f(x)

	@Test
	public void test_f1() throws Exception {
		Monom a=new Monom (1.5,4);
		double f=a.f(3.5);
		if (f!= 225.09375) {
			fail("fail");		
		}		
	}
	@Test
	public void test_f2() throws Exception {
		Monom a=new Monom (-2.7,1);
		double f=Math.round(a.f(5.2)*100.0)/100.0;
		if (f!=-14.04) {
			fail("fail");		
		}		
	}
	@Test
	public void test_f3() throws Exception {
		Monom a=new Monom (1.5,4);
		double f=Math.round(a.f(10)*100.0)/100.0;
		if (f!=15000) {
			fail("fail");		
		}		
	}

	//add
	@Test
	public void test_add1() throws Exception {
		Monom a=new Monom (3.7,4);
		Monom b=new Monom(-3.7,4);
		Monom expected= new Monom ("0");
		b.add(a);
		if (!b.isEquals(expected)) {
			fail ("fail");
		}
	}	
	@Test
	public void test_add2() throws Exception {
		Monom a=new Monom (-2.7,1);
		Monom b=new Monom(-2.3,1);
		Monom expected= new Monom ("-5x");
		b.add(a);
		if (!b.isEquals(expected)) {
			fail ("fail");
		}
	}	
	@Test
	public void test_add3() throws Exception {
		Monom a=new Monom (1.5,4);
		Monom b=new Monom(3.7,4);
		Monom expected= new Monom ("5.2x^4");
		b.add(a);
		if (!b.isEquals(expected)) {
			fail ("fail");
		}
	}
	@Test
	public void test_add4() throws Exception {
		Monom a=new Monom (2,3);
		Monom b=new Monom(-2,3);
		Monom expected= new Monom ("0");
		b.add(a);
		if (!b.isEquals(expected)) {
			fail ("fail");
		}
	}
	@Test
	public void test_add5() throws Exception {
		Monom a=new Monom (2,2);
		Monom b=new Monom(0,0);
		Monom expected= new Monom (2,2);
		b.add(a);
		if (!b.isEquals(expected)) {
			fail ("fail");
		}
	}
	@Test
	public void test_add6() throws Exception {
		Monom a=new Monom (0,0);
		Monom b=new Monom(0,0);
		Monom expected= new Monom (0,0);
		b.add(a);
		if (!b.isEquals(expected)) {
			fail ("fail");
		}
	}
	@Test
	public void test_add7() throws Exception {
		Monom a=new Monom (0,0);
		Monom b=new Monom(2,2);
		Monom expected= new Monom (2,2);
		b.add(a);
		if (!b.isEquals(expected)) {
			fail ("fail");
		}
	}
	@Test 
	public void test_add8() throws Exception {
		Monom a=new Monom (1,0);
		Monom b=new Monom(2,2);
		try {
			b.add(a);
			fail( "My method didn't throw when I expected it to" );
		}
		catch(Exception expectedException) {	
		}
	}

	//multiply
	@Test
	public void test_multyply1() throws Exception {
		Monom a=new Monom (2.5,5);
		Monom b=new Monom(-2.3,1);
		Monom expected= new Monom (-5.75,6);
		b.multiply(a);
		if (!b.isEquals(expected)) {
			fail ("fail");
		}
	}

	@Test
	public void test_multyply2() throws Exception {
		Monom a=new Monom (2.5,5);
		Monom b=new Monom(-2.3,1);
		Monom expected= new Monom (-5.75,6);
		b.multiply(a);
		if (!b.isEquals(expected)) {
			fail ("fail");
		}
	}

	@Test
	public void test_multyply3() throws Exception {
		Monom a=new Monom (0,4);
		Monom b=new Monom(0,0);
		Monom expected= new Monom ("0");
		b.multiply(a);
		if (!b.isEquals(expected)) {
			fail ("fail");
		}
	}

	//subtract:
	@Test
	public void test_subtract1() throws Exception {
		Monom a= new Monom (5.5,2);
		Monom b=new Monom (-4.5,2);
		Monom expected= new Monom (10,2);
		a.subtract(b);
		if (!a.isEquals(expected))
			fail("fail");
	}
	@Test
	public void test_subtract2() throws Exception {
		Monom a= new Monom (2,3);
		Monom expected= new Monom ("0");
		a.subtract(a);
		if (!a.isEquals(expected))
			fail("fail");
	}
	@Test
	public void test_subtract3() throws Exception {
		Monom a= new Monom (0,0);
		Monom b=new Monom(1,3);
		Monom expected= new Monom (1,3);
		b.subtract(a);
		if (!b.isEquals(expected))
			fail("fail");
	}
	@Test
	public void test_subtract4() throws Exception {
		Monom a= new Monom (0,0);
		Monom b=new Monom(1,3);
		Monom expected= new Monom (-1,3);
		a.subtract(b);
		if (!a.isEquals(expected))
			fail("fail");
	}

	@Test (expected = Exception.class)
	public void test_subtract5() throws Exception {
		Monom a=new Monom (1,0);
		Monom b=new Monom(2,2);
		b.subtract(a);
		try {
			b.subtract(a);
			fail( "My method didn't throw when I expected it to" );
		}
		catch(IndexOutOfBoundsException e) {	
		}
	}


	//derivative:
	@Test
	public void test_derivative1() throws Exception {
		Monom a= new Monom (10,4);
		Monom expected= new Monom (40,3);
		a.derivative();
		if (!a.isEquals(expected))
			fail("fail");
	}

	@Test
	public void test_derivative2() throws Exception {
		Monom a= new Monom (5,1);
		Monom expected= new Monom (5,0);
		a.derivative();
		if (!a.isEquals(expected))
			fail("fail");
	}

	@Test
	public void test_derivative3() throws Exception {
		Monom a= new Monom (2,0);
		Monom expected= new Monom (0,0);
		a.derivative();
		if (!a.isEquals(expected))
			fail("fail");
	}

	@Test
	public void test_derivative4() throws Exception {
		Monom a= new Monom (0,0);
		Monom expected= new Monom (0,0);
		a.derivative();
		if (!a.isEquals(expected))
			fail("fail");
	}
	
}
