package myMath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Predicate;
import myMath.Monom;
import myMath.Monom_Comperator;

/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Chen Ostrovski 316402650, Ester Reznikov 315674028
 */
public class Polynom implements Polynom_able{
	private ArrayList<Monom> Polynom_able;	

	//Constructors
	/** 
	 * Zero Constructor : builds a new empty Polynom
	 */	

	public Polynom() {
		Polynom_able= new ArrayList<>();
	}

	/**
	 *   Copy Constructor : deep copy, builds a new Polynom
	 *   @param other get the Monoms from other Polynom and them in this Polynom
	 */

	public Polynom(Polynom other) {
		Polynom_able= new ArrayList<>();
		// The iterator help us to go through the Polynom
		Iterator<Monom> p_iter = other.iterator();
		// While the other Polynom has Monoms, add the Monoms to this Polynom
		while (p_iter.hasNext()) {
			Monom current= new Monom(p_iter.next());
			this.add(current);
		}
	}

	/** 
	 *  String Constructor : builds a new Polynom,
	 *  Get String as input and change it to Polynom
	 *  @param p proper input f(x) = a_1X^b_1 + a_2*X^b_2 ... a_n*Xb_n
	 */

	public Polynom(String p) {
		Polynom PolynomNew=new Polynom();
		// Split helps us to separate the various Monoms
		String[]s=p.split(" \\+ ");
		for (int i=0; i<s.length; i++) {
			// Saves the Monoms values and adds to the this Polynom
			Monom newM=new Monom(s[i]);
			PolynomNew.add(newM);
		}
		this.Polynom_able=PolynomNew.Polynom_able;
	}

	//Methods
	/**
	 * This method add other Polynom to this Polynom.
	 * Does not contain two Monoms with the same power,
	 * If the Monom equal to 0 don't add it
	 * @param p1 all the Monoms in other Polynom
	 */
	@Override
	public void add(Polynom_able p1) {
		// The iterator help us to go through the Polynom
		Iterator<Monom> p_iter=p1.iterator();
		//While the other Polynom has Monoms add them to this Polynom
		while (p_iter.hasNext() ) {
			Monom current=p_iter.next();
			this.add(current);
		}
	}

	/**
	 * This method add Monom to this Polynom.
	 * Add two Monoms with the same power in order to avoid duplication,
	 * If the Monom equal to 0 don't add it
	 * @param m1 the Monom's coefficient and power
	 */
	@Override
	public void add(Monom m1) {
		// Checking if the Monom is not equal to 0
		if (m1.get_coefficient()!=0) {
			boolean flag= true;
			// The iterator help us to go through the Polynom
			Iterator<Monom> p_iter=this.iterator();
			// Go over the Polynom and see if there is a Monom with the same power as m1
			while (p_iter.hasNext() && flag ) {
				Monom current=p_iter.next();
				// Checking if the Monoms has the same power
				if (m1.get_power()== current.get_power()) {
					current.add(m1);
					// If it found two Monons with the same power don't go through the while again
					flag =false;
				}
			}
			// If it didn't found two Monons with the same power add the Monom in the end of the Polynom
			if (!p_iter.hasNext() && flag) {
				Polynom_able.add(Polynom_able.size(), m1);
			}
			// If there are Monoms that equal to 0 remove them and sort the Polynom
			Remove_Zero();
		}
	}

	/**
	 * This method subtract other Polynom from this Polynom
	 * @param p1 all the Monoms in other Polynom
	 */
	@Override
	public void subtract(Polynom_able p1) {
		// The iterator help us to go through the Polynom
		Iterator<Monom> p_iter=p1.iterator();
		while (p_iter.hasNext() ) {
			Monom current=p_iter.next();
			// Subtract from this Polynom the Monoms from other Polynom
			this.subtract(current);
		}
	}

	/**
	 * This method subtract Monom from this Polynom
	 * @param m1 the Monom's coefficient and power
	 */
	public void subtract(Monom m1) {
		boolean flag= true;
		// The iterator help us to go through the Polynom
		Iterator<Monom> p_iter=this.iterator();
		// Go over the Polynom and see if there is a Monom with the same power as m1
		while (p_iter.hasNext() && flag ) {
			Monom current=p_iter.next();
			// If the current Monoms in the Polynom has the same power as m1. subtract from current m1.
			if (m1.get_power()== current.get_power()) {
				current.subtract(m1);
				// If it found two Monons with the same power don't go through the while again
				flag =false;
			}
		}
		// If it didn't found two Monons with the same power subtract the Monom from the end of the Polynom
		if (!p_iter.hasNext() && flag) {
			// Create a new Monom with coefficient equals to 0 and the same power as m1, subtract from it m1.
			Monom m2=new Monom(0,m1.get_power());
			m2.subtract(m1);
			// Add to the Polynom m2. m2 equals to the negative m1
			Polynom_able.add(Polynom_able.size(), m2);
		}
		// If there are Monoms that equal to 0 remove them and sort the Polynom
		Remove_Zero();
	}

	/**
	 * This method multiply this Polynom by other Polynom,
	 * Multiply all the Monoms is this Polynom by the Monoms in the other Polynom
	 * @param p1 all the Monoms in other Polynom
	 */
	/* (non-Javadoc)
	 * @see myMath.Polynom_able#multiply(myMath.Polynom_able)
	 */
	@Override
	public void multiply(Polynom_able p1) {
		// Create two iterators one to go through this Polynom and one to go through other Polynom
		Iterator<Monom> p_iter; 
		Iterator<Monom> this_iter=this.iterator(); // The iterator of this Polynom

		Polynom copy_polynom=  new Polynom(); // Place to put the values after multiplying them
		ArrayList<Monom> copy_polynom2=  new ArrayList<Monom>();
		Monom temp=new Monom();
		//Passes all the Monoms in this Polynom
		while (this_iter.hasNext()) {
			// thiscurrent represent the current Monom from this Polynom
			Monom thiscurrent=this_iter.next();
			p_iter=p1.iterator(); // The iterator of other Polynom
			// Passes all the Monoms in other Polynom
			while (p_iter.hasNext()) {
				// Put the Monom from this Polynom in temporary variable
				temp=new Monom(thiscurrent);
				// p1current represent the current Monom from other Polynom
				Monom p1current=p_iter.next();
				// Multiply the Monom from this Polynom by the Monom from other Polynom
				temp.multiply(p1current);
				// Add to the new Polynom
				copy_polynom.add(temp);
			}
		}
		// The iterator help us to go through the copy_polynom
		Iterator<Monom> copy= copy_polynom.iterator();
		// transfers the Monoms form the Polynom to the ArrayList
		while(copy.hasNext())
			copy_polynom2.add(copy.next());
		Polynom_able=copy_polynom2;
		// If there are Monoms that equal to 0 remove them and sort the Polynom
		Remove_Zero();

	}

	/**
	 * This method check if this Polynom is logically equals to other Polynom
	 * @param p1 all the Monoms in other Polynom
	 * @return true : if this Polynom represents the same function or false : otherwise
	 */
	@Override
	public boolean equals (Polynom_able p1) {
		// Create two iterators one to go through this Polynom and one to go through other Polynom
		Iterator<Monom> p_iter=p1.iterator();
		Iterator<Monom> this_iter=this.iterator();
		//While the both Polynom still have Monoms
		while (this_iter.hasNext() && p_iter.hasNext()) {
			Monom thiscurrent= new Monom (this_iter.next());
			Monom p1current= new Monom (p_iter.next());
			// Compare between the Monoms
			// If they don't have the same coefficient or power return : False
			if (thiscurrent.get_coefficient()!=p1current.get_coefficient()) 
				return false;

			if(thiscurrent.get_power()!=p1current.get_power()) 
				return false;

		}
		// If both have no more Monoms return : True
		if (!this_iter.hasNext() && !p_iter.hasNext())
			return true;
		return false;
	}

	/**
	 * This method check if it's the Zero Polynom 
	 * @return true : if the Polynom is null or false : otherwise
	 */
	@Override
	public boolean isZero() {
		return (Polynom_able.isEmpty());
	}

	/**
	 * Compute a value of x (x bigger or equal to x0 and x smaller or equal to x1,
	 * for f(x) smaller than eps),
	 * assuming that(f(x0)*f(x1)) smaller or equal to 0, returns f(x2) such that:
	 * (i) x0 bigger or equal to x1 bigger or equal to x2 and (ii) f(x2) smaller then eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @return mid
	 */
	@Override
	public double root(double x0, double x1, double eps) {

		double mid=0;

		while ( (x1-x0) > eps )
		{
			mid=((x1+x0)/2);          
			// Calculate the value of the Polynom at mid
			double f_mid=this.f(mid);
			// Calculate the value of the Polynom at x0
			double f_x0=this.f(x0);
			// f(x0) and f(mid) have different signs: move b
			if ( (f_mid > 0 && f_x0 < 0) || (f_mid < 0 && f_x0 > 0) ){
				x1 = mid;
			}
			// f(x0) and f(mid) have same signs: move a
			else{  
				x0 = mid;
			}
		}
		return mid;
	}

	/**
	 * This method create a deep copy of this Polynum
	 * @return newp deep copy of this Polynum
	 */
	@Override
	public Polynom_able copy() {
		Polynom_able newp= new Polynom() ;
		newp.add(this);
		return newp;
	}

	/**
	 * This method derivative this Polynom,
	 * Change this Polynom to the derivative of this Polynom
	 * @return newp derivative version of this Polynom
	 */
	@Override
	public Polynom_able derivative() {
		// Create a new Polynom in order to contain the derivative Monoms
		Polynom_able newp= new Polynom();
		// The iterator help us to go through the Polynom
		Iterator<Monom> p_iter=this.iterator();
		//Passes all the Monoms in this Polynom
		while (p_iter.hasNext() ) {
			Monom current=p_iter.next();
			// Chekin if the Monom is not a free number
			if (current.get_power()!=0) {
				Monom derivativeMonom=new Monom(current);
				derivativeMonom.derivative();
				// Add to the new Polynom the derivative Monoms
				newp.add(derivativeMonom);
			}
		}
		return newp;
	}
	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * see: https://en.wikipedia.org/wiki/Riemann_integral
	 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
	 */
	@Override
	public double area(double x0,double x1, double eps) {
		// Epsilon can't be a negative number or equal to 0 
		while (eps<=0) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Wrong input. enter eps that > 0");
			eps=scan.nextInt();
		}
		// n is the number of rectangles
		int n=(int)((x1-x0)/eps);
		double sum=0;
		for (int i=1; i<=n; i++) {
			double xi=x0+eps*(i-1);
			// Calculate the value of x in the i place
			double f=this.f(xi);
			if (f>0)
				sum=sum+f;
		}
		return sum*eps;
	}
	/**
	 * This method help us to go through the Polynom
	 * @return Iterator
	 */
	@Override
	public Iterator<Monom> iterator(){
		Iterator<Monom> iter= Polynom_able.iterator();
		return iter;
	}
	/**
	 * This method prints the Polynom,
	 * Prints " + " between the Monoms
	 */

	public String toString() {		
		if (this.isZero())
			return "0";
		else {
			String polynom=new String("");
			// The iterator help us to go through the Polynom
			Iterator<Monom> print_iter = this.iterator();
			while (print_iter.hasNext()) {
				// Prints the Monoms
				polynom=polynom+(print_iter.next()).toString();
				// Prints " + " between the Monoms
				if (print_iter.hasNext()) {
					polynom=polynom+" + ";
				}
			}
			return polynom;
		}
	}

	/** 
	 * This method get a real number and calculates the Polynom value
	 * @param x the value of the number
	 * @return the value of the Polynom at x
	 */

	@Override
	public double f(double x) {
		// The iterator help us to go through the Polynom
		Iterator<Monom> iter = this.iterator();
		// Count the value of all the Monoms together
		double sum=0;
		while(iter.hasNext()) {
			Monom current=new Monom(iter.next());
			// Add the Monom value to the sum
			sum=sum+current.f(x);
		}
		return sum;
	}
	/**
	 * This method remove zero Monom from the Polynom,
	 * remove from the Polynom Monoms that their coefficient is equal to zero
	 */
	public void Remove_Zero() {
		// The for loop help us to go through the Polynom
		for (int i=0; i<this.Polynom_able.size(); i++) {
			if (this.Polynom_able.get(i).get_coefficient()==0)
				Polynom_able.remove(this.Polynom_able.get(i));
		}
		// Sort the Polynom by power (from the biggest to the smallest)
		Collections.sort(this.Polynom_able,new Monom_Comperator()); 
	}

}



