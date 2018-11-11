package myMath;
//
import org.omg.Messaging.SyncScopeHelper;
//
public class Test {
	public static void main(String[] args) throws Exception{
/*	System.out.println("Monom test:");
		Test_Monom();
		System.out.println("Polynom test:");
		Test_Polynom();
		*/
		Polynom a=new Polynom("4X");
		Polynom b=new Polynom("4*x^1");
		Monom c=new Monom(4,1);
		Polynom p3=new Polynom();
		p3.add(c);
		System.out.println(a.equals(b));
		System.out.println(b.equals(p3));
		System.out.println(p3.equals(a));
		p3.area(1, 2, 5);

	}

	/**
	 * This method checks the class Monom
	 * Checks all the methods in Monom
	 */

	public static void Test_Monom() throws Exception {
		//Constructors, getters:
		System.out.println("Constructors and getters:\n");
		Monom a=new Monom();
		Monom b=new Monom(1.5,4);
		Monom c=new Monom(-2.7,1);
		Monom d=new Monom(b);
		Monom e=new Monom("2.5*x^3");
		System.out.println("Monom a- need to get:0 ");		
		System.out.println(a.toString());
		System.out.println();
		System.out.println("Monom b- need to get: 1.5*x^4" );		
		System.out.println(b.toString());
		System.out.println();
		System.out.println("Monom c- need to get: (-2.7)*x");		
		System.out.println(c.toString());
		System.out.println();
		System.out.println("Monom d- need to get: 1.5*x^4");		
		System.out.println(d.toString());
		System.out.println();
		System.out.println("Monom e- need to get: 2.5*x^3");		
		System.out.println(e.toString());
		System.out.println();

		System.out.println("e.coefficient- you need to get: 2.5");
		System.out.println(e.get_coefficient());
		System.out.println("e.power- you need to get: 3");
		System.out.println(e.get_power());
		System.out.println();




		//f(x):
		System.out.println("f(x):\n");
		System.out.println("b.f(3.5)): need to get: 225.09375");
		System.out.println(b.f(3.5));
		System.out.println();
		System.out.println("c.f(5.2)): need to get: -14.04");
		System.out.println(c.f(5.2));
		System.out.println();
		System.out.println("d.f(10)): need to get: 15000");
		System.out.println(d.f(10));
		System.out.println();


		Monom h=new Monom(-2.3,1);
		Monom i=new Monom(3.7,4);
		Monom j=new Monom (-3.7,4);
		Monom k=new Monom(1,0);


		//add:
		System.out.println("add:\n");
		System.out.println("j+i: need to get: 0 ");
		j.add(i);
		System.out.println(j);
		System.out.println();
		System.out.println("c+h: need to get: (-5)*x ");
		c.add(h);
		System.out.println(c.toString());
		System.out.println();
		System.out.println("d+i: need to get: 5.2*x^4 ");
		d.add(i);
		System.out.println(d.toString());
		System.out.println();
		System.out.println("a+a: need to get: 0 ");
		a.add(a);
		System.out.println(a.toString());
		System.out.println();
		System.out.println("a+k: need to get: 1 ");
		a.add(k);
		System.out.println(a.toString());
		System.out.println();

		//multiply:
		System.out.println("multiply:\n");
		System.out.println("e*h: need to get: (-5.75)*x^4 ");
		e.multiply(h);
		System.out.println(e.toString());
		System.out.println();
		System.out.println("b*i: need to get: 5.55*x^8 ");
		b.multiply(i);
		System.out.println(b.toString());
		System.out.println();
		Monom l=new Monom(0,0);
		i.multiply(l);
		System.out.println("i*l: need to get:0");
		System.out.println(i.toString());
		System.out.println();

		//subtract:
		System.out.println("subtract:\n");
		System.out.println("d-e: need to get: 10.95*x^4 ");
		d.subtract(e);
		System.out.println(d);
		System.out.println("c-c: need to get: 0 ");
		c.subtract(c);
		System.out.println(c);
		System.out.println("c-h: need to get: (2.3)*x ");
		c.subtract(h);
		System.out.println(c);
		System.out.println();

		//derivative:
		System.out.println("derivative:\n");
		System.out.println("(d)': need to get: 43.8*x^3");
		d.derivative();
		System.out.println(d.toString());
		System.out.println("(c)': need to get: 2.3");
		c.derivative();
		System.out.println(c.toString());
		System.out.println("(j)': need to get: 0");
		j.derivative();
		System.out.println(j.toString());
		



	}
	/**
	 * This method checks the class Polynom
	 * Checks all the methods in Polynom
	 */

	public static void Test_Polynom() throws Exception{

		//Constructors:
		System.out.println("Constructors:\n");
		Polynom p1=new Polynom();
		System.out.println("p1: need to get:0 ");
		System.out.println(p1.toString());
		System.out.println();
		Polynom p2=new Polynom("5*x^2 + (-2)*x^3 + 7*x");
		System.out.println("p2: need to get: (-2)*x^3 + 5*x^2 + 7*x ");
		System.out.println(p2.toString());
		System.out.println();
		Polynom p3=new Polynom(p2);
		System.out.println("p3: need to get: (-2)*x^3 + 5*x^2 + 7*x ");
		System.out.println(p3.toString());
		System.out.println();

		//isZero():
		System.out.println("isZero:\n");
		System.out.println("p1.isZero(): need to get: true");		
		System.out.println(p1.isZero());
		System.out.println();
		System.out.println("p2.isZero(): need to get: false");		
		System.out.println(p2.isZero());
		System.out.println();

		//add: Polynom,monom:
		System.out.println("add:\n");
		Monom a=new Monom (7,4);
		Monom b= new Monom(8.4, 3);
		p1.add(a);
		p1.add(b);
		System.out.println("p1: need to get: 7*x^4 + 8.4*x^3");
		System.out.println(p1.toString());
		System.out.println();
		p2.add(p1);
		System.out.println("p2: need to get: 7*x^4 + 6.4*x^3 + 5*x^2 + 7*x");
		System.out.println(p2.toString());
		System.out.println();


		//f(x):
		System.out.println("f(x):\n");
		System.out.println("p1.f(10)): need to get: 78400");
		System.out.println(p1.f(10));
		System.out.println();
		System.out.println("p2.f(6.5)): need to get: 14509.7875");
		System.out.println(p2.f(6.5));
		System.out.println();

		//derivative:
		System.out.println("derivative:\n");
		System.out.println("p2:"+p2.toString());
		System.out.println();
		System.out.println("p2': need to get: 28*x^3 + 19.2*x^2 + 10*x + 7");
		System.out.println(p2.derivative());
		System.out.println();
		System.out.println("p2'': need to get: 84*x^2 + 38.4*x + 10");
		System.out.println(p2.derivative().derivative());
		System.out.println();
		Polynom p6= new Polynom ("5*x + 4");
		System.out.println("p6:"+p6.toString());
		System.out.println();
		System.out.println("p6': need to get: 5");
		System.out.println(p6.derivative());
		System.out.println();
		System.out.println("p6'': need to get: 0");
		System.out.println(p6.derivative().derivative());
		System.out.println();


		//subtract:
		System.out.println("subtract:\n");
		p1.subtract(a);
		System.out.println("p1-a: need to get: 8.4*x^3");
		System.out.println(p1.toString());
		System.out.println();
		p2.substract(p1);
		System.out.println("p2-p1: need to get: -2*x^3 + 5*x^2 + 7*x");
		System.out.println(p2.toString());
		System.out.println();
		Polynom p4=new Polynom(p2);
		Polynom p7=new Polynom(p2);
		Polynom p5=new Polynom("-2*x^3 + 5*x^2 + 7*x");
		p2.substract(p5);
		System.out.println("p2-p5: need to get:0 ");
		System.out.println(p2.toString());
		p4.substract(p7);
		System.out.println("p4-p7: need to get:0 ");
		System.out.println(p4.toString());
		System.out.println();

		//multyply:
		System.out.println("multyply:\n");
		Polynom p8=new Polynom();
		Polynom p9=new Polynom("3*x^2 + 7*x^3");
		Polynom p10=new Polynom("2*x^1 + 3");
		Polynom p11=new Polynom("x^2 + x");
		p10.multiply(p10);;
		System.out.println("p10*p10: need to get:4*x^2 + 12*x + 9");
		System.out.println(p10.toString());
		System.out.println();
		p9.multiply(p11);
		System.out.println("p9*p11: need to get:7*x^5+ 3*x^4 + 10*x^3");
		System.out.println(p9.toString());
		System.out.println();
		p9.multiply(p8);
		System.out.println("p9*p8:need to get:0");
		System.out.println(p9.toString());
		System.out.println();

		//equals:
		System.out.println("equals:\n");
		Polynom p12=new Polynom("6*x^7 + 4*x^2");
		Polynom p13=new Polynom("6*x^7 + 4*x^2");
		Polynom p14=new Polynom("6*x^7");
		System.out.println("p12.equals(p13): need to get: true");
		System.out.println(p12.equals(p13));
		System.out.println();
		System.out.println("p14.equals(p13): need to get: false");
		System.out.println(p14.equals(p13));
		System.out.println();

		//f(x):
		System.out.println("f(x):\n");
		Polynom p15=new Polynom("(-5)*x^2 + -4*x^2");
		System.out.println("f(p15): need to get- (-144)");
		System.out.println(p15.f(4));
		System.out.println();
		Polynom p16=new Polynom("-5*x^7 + -4*x^8");
		System.out.println("f(p16): need to get- (-187.9453125)");
		System.out.println(p16.f(1.5));
		System.out.println();
		Polynom p17=new Polynom("3*x^2 + 9*x^1");
		System.out.println("f(p17): need to get- (336.72 ");
		System.out.println(p17.f(9.2));
		System.out.println();
		Polynom p18=new Polynom("3*x^1 + 2*x^0");
		System.out.println("f(p18): need to get- 304.25");
		System.out.println(p18.f(100.75));
		System.out.println();

		//root:
		System.out.println("root:\n");
		System.out.println("p16.root(0,7,0.000001):need to get 7");
		System.out.println(p16.root(0,7,0.000001));
		System.out.println();
		System.out.println("p12.root(-2,7,0.0001):need to get -0.92169189453125");
		System.out.println(p12.root(-2,7,0.001));
		System.out.println();
		System.out.println("p17.root(-2,8,0.034):need to get 0.01171875");
		System.out.println(p17.root(-2,8,0.034));
		System.out.println();


		//area:
		System.out.println("area: \n");
		Polynom p19=new Polynom("5*x^7 + 4*x^8");
		System.out.println("p19.area(2,4,0.01):need to get 155374.399800205.");
		System.out.println(p19.area(2, 4, 0.01));
		System.out.println();
		System.out.println("p17.area(4,10,0.04):need to get 1307.88476399054");
		System.out.println(p17.area(4,10,0.04));
		System.out.println();

	}
}
