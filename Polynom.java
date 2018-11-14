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
	 *  Copy Constructor : deep copy, builds a new Polynom
	 *  @param other get the Monoms from other Polynom and them in this Polynom
	 *  @throws Exception if one of the Monoms in the Polynom have power the lower than zero
	 */

	public Polynom(Polynom other)  throws Exception{
		Polynom_able= new ArrayList<>();
		// The iterator help us to go through the Polynom
		Iterator<Monom> p_iter = other.iteretor();
		// While the other Polynom has Monoms, add the Monoms to this Polynom
		while (p_iter.hasNext()) {
			Monom current= new Monom(p_iter.next());
			this.add(current);
		}
	}

	/** 
	 *  String Constructor : builds a new Polynom,
	 *  Get String as input and change it to Polynom
	 *  @param polynom proper input f(x) = a_1x^b_1 + a_2*x^b_2 ... a_n*xb_n
	 *  @throws Exception if the Polynom equals to null
	 *  @throws Exception if the Polynom contains invalid signs
	 */

	public Polynom(String polynom) throws Exception{
		// throws exception if the Polynom equal to null
		if (polynom==null)
			throw new Exception("String is null");
		else {
			Polynom PolynomNew=new Polynom();
			// Replaces the string marks so that the string matches the Monom constructor
			polynom=polynom.replaceAll("[(,), ]","");
			polynom=polynom.replaceAll("[-]"+"[-]", "+");
			polynom=polynom.replaceAll("[-]", "+-");
			polynom=polynom.replaceAll("[+]"+"[+]", "+");
			if (polynom.charAt(0)=='+') {
				polynom=polynom.replaceFirst("[+]", "");
			}
			// Split makes separation between Monoms
			String[]s=polynom.split("\\+");
			for (int i=0; i<s.length; i++) {
				// Saves the Monoms values and adds to the this Polynom
				Monom newM=new Monom(s[i]);
				PolynomNew.add(newM);
			}
			this.Polynom_able=PolynomNew.Polynom_able;
		}
	}

	//Methods

	/**
	 * This method add other Polynom to this Polynom.
	 * Does not contain two Monoms with the same power,
	 * If the Monom equal to 0 don't add it
	 * @param p1 all the Monoms in other Polynom
	 * @throws Exception if one of the Monoms in the Polynom have power the lower than zero
	 */
	@Override
	public void add(Polynom_able p1)  throws Exception {
		if (p1!=null) {
			// The iterator help us to go through the Polynom
			Iterator<Monom> p_iter=p1.iteretor();
			//While the other Polynom has Monoms add them to this Polynom
			while (p_iter.hasNext() ) {
				Monom current=p_iter.next();
				this.add(current);
			}
		}
	}

	/**
	 * This method add Monom to this Polynom.
	 * Add two Monoms with the same power in order to avoid duplication,
	 * If the Monom equal to 0 don't add it
	 * @param m1 the Monom's coefficient and power
	 * @throws Exception if the Monom have power that lower than zero
	 */
	@Override
	public void add(Monom m1) throws Exception {
		// Checking if the Monom is not equal to 0
		if (m1.get_coefficient()!=0) {
			boolean flag= true;
			// The iterator help us to go through the Polynom
			Iterator<Monom> p_iter=this.iteretor();
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
	 * @throws Exception if one of the Monoms in the Polynom have power that lower than zero
	 */
	@Override
	public void substract(Polynom_able p1) throws Exception {
		// The iterator help us to go through the Polynom
		Iterator<Monom> p_iter=p1.iteretor();
		while (p_iter.hasNext() ) {
			Monom current=p_iter.next();
			// Subtract from this Polynom the Monoms from other Polynom
			this.subtract(current);
		}
	}

	/**
	 * This method subtract Monom from this Polynom
	 * @param m1 the Monom's coefficient and power
	 * @throws Exception if the Monom have power that lower than zero
	 */

	public void subtract(Monom m1) throws Exception{
		boolean flag= true;
		// The iterator help us to go through the Polynom
		Iterator<Monom> p_iter=this.iteretor();
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
	 * @throws Exception if one of the Monoms in the Polynom have power that lower than zero
	 */

	@Override
	public void multiply(Polynom_able p1) throws Exception {
		// Create two iterators one to go through this Polynom and one to go through other Polynom
		Iterator<Monom> p_iter; 
		Iterator<Monom> this_iter=this.iteretor(); // The iterator of this Polynom

		Polynom copy_polynom=  new Polynom(); // Place to put the values after multiplying them
		ArrayList<Monom> copy_polynom2=  new ArrayList<Monom>();
		Monom temp=new Monom();
		//Passes all the Monoms in this Polynom
		while (this_iter.hasNext()) {
			// thiscurrent represent the current Monom from this Polynom
			Monom thiscurrent=this_iter.next();
			p_iter=p1.iteretor(); // The iterator of other Polynom
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
		Iterator<Monom> copy= copy_polynom.iteretor();
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
	 * @throws Exception if one of the Monoms in the Polynom have power that lower than zero
	 */

	@Override
	public boolean equals (Polynom_able p1) throws Exception{
		// Create two iterators one to go through this Polynom and one to go through other Polynom
		Iterator<Monom> p_iter=p1.iteretor();
		Iterator<Monom> this_iter=this.iteretor();
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
	 * (i) x0 bigger or equal to x1 bigger or equal to x2 and (ii) f(x2) smaller than eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @throws Exception if epsilon is negative number
	 * @throws Exception if the range is invalid
	 * @return mid
	 */
	@Override
	public double root(double x0, double x1, double eps) throws Exception {
		double mid=0;
		// epsilon have to be bigger than zero
		if (eps<=0)
			throw new Exception("eps need to be bigger than zero");
		// x0 can't be bigger than x1
		else if (x0>x1)
			throw new Exception("x1 should be bigger than x0");
		// All the values are legal
		else { 
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
		}
		return mid;
	}

	/**
	 * This method create a deep copy of this Polynum
	 * @return newp deep copy of this Polynum
	 * @throws Exception if one of the Monoms have power the lower than zero
	 */
	@Override
	public Polynom_able copy()  throws Exception {
		Polynom_able newp= new Polynom() ;
		newp.add(this);
		return newp;
	}

	/**
	 * This method derivative this Polynom,
	 * Change this Polynom to the derivative of this Polynom
	 * @return newp derivative version of this Polynom
	 * @throws Exception if one of the Monoms have power the lower than zero
	 */
	@Override
	public Polynom_able derivative() throws Exception{
		// Create a new Polynom in order to contain the derivative Monoms
		Polynom_able newp= new Polynom();
		// The iterator help us to go through the Polynom
		Iterator<Monom> p_iter=this.iteretor();
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
	 * @throws Exception if epsilon is negative number
	 * @throws Exception if the range is invalid
	 * @throws Exception if there is no cutting point with the X-axis
	 */
	@Override
	public double area(double x0,double x1, double eps) throws Exception {
		return underArea(x0, x1,  eps)+aboveArea(x0,x1,  eps);
	}


	/**
	 * Auxiliary function the calculates the area if its under the X-axis
	 * @param f the negative value of the area
	 * @return the approximated area under the x-axis under this Polynom and between the [x0,x1] range.
	 * @throws Exception if there is no cutting point with the X-axis
	 */


	public double underArea(double x0,double x1, double eps) throws Exception {
		// Throw exception if the Polynom don't cuts the X-axis
		if (xaxis(x0,x1,eps))
			throw new Exception("There is no cutting point with the X-axis");
		// Epsilon can't be a negative number or equal to 0 
		else if (eps<=0)
			throw new Exception("eps need to be bigger than zero");
		// x0 can't be bigger than x1
		else if (x0>x1)
			throw new Exception("x1 should be bigger than x0");
		else {

			int n=(int)((x1-x0)/eps);
			double sum=0;
			for (int i=1; i<=n; i++) {
				double xi=x0+eps*(i-1);
				// Calculate the value of x in the i place
				double f=this.f(xi); 
				if (f<0)
				sum=sum+f;
			}
			return Math.abs(sum*eps);
		}
	}
	public double aboveArea(double x0,double x1, double eps) throws Exception {
		// Throw exception if the Polynom don't cuts the X-axis
		if (xaxis(x0,x1,eps))
			throw new Exception("There is no cutting point with the X-axis");
		// Epsilon can't be a negative number or equal to 0 
		else if (eps<=0)
			throw new Exception("eps need to be bigger than zero");
		// x0 can't be bigger than x1
		else if (x0>x1)
			throw new Exception("x1 should be bigger than x0");
		else {

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
	}

	public boolean xaxis(double x0,double x1,double eps) throws Exception {
		boolean flag=false;
		double x;
		// Checks if the Polynom cuts the X-axis
		for (double i=x0; i<=x1; i+=0.001) {
			if (this.f(i)==0) {
				flag=true;
			}
		}
		return flag;
	}
	/**
	 * This method help us to go through the Polynom
	 * @return Iterator
	 */
	@Override
	public Iterator<Monom> iteretor(){
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
			Iterator<Monom> print_iter = this.iteretor();
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
	 * This method is used for build a new plot.
	 * Given a Polynom and range the method drew a new plot
	 * @param x1 the starting point of the range
	 * @param x2 the ending point of the range
	 * @throws Exception if the user build a invalid Polynom
	 * @throws Exception if x1 is bigger than x2 (invalid range) 
	 */
	public void toPlot(double x1, double x2) throws Exception{
		Polynom toplot=new Polynom(this.toString());
		Plot plot=new Plot(toplot,x1,x2);
		plot.setVisible(true);
	}
	/** 
	 * This method get a real number and calculates the Polynom value
	 * @param x the value of the number
	 * @return the value of the Polynom at x
	 * @throws Exception if the powers value is lower than zero
	 * @throws Exception if the Monoms equals to null
	 */

	@Override
	public double f(double x) throws Exception{
		// The iterator help us to go through the Polynom
		Iterator<Monom> iter = this.iteretor();
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
