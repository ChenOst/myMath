
package myMath;
import java.util.Scanner;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and b is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * 
 * @author Chen Ostrovski 316402650 , Ester Reznikov 315674028
 */
public class Monom implements function{

	//constructors

	/**
	 * Regular Constructor : builds a new Monom   
	 * @param  a  the value of the coefficient
	 * @param  b  the value of the power
	 */

	public Monom(double a, int b) throws Exception  {
		this._coefficient=a;
		this.set_power(b);

	}

	/**
	 * Default Constructor : builds a new Monom.
	 * Sets the value of the coefficient to be 0,
	 * Sets the value of the power to be 0
	 */

	public Monom() {
		this._coefficient=0;
		this._power=0;
	}

	/**
	 * Copy Constructor : deep copy, builds a new Monom
	 * @param  other get the coefficient and power of other Monom and set them in this Monom
	 */

	public Monom(Monom other) throws Exception {
		this(other.get_coefficient(), other.get_power());
	}

	/**
	 * String Constructor : builds a new Monom.
	 * Get String as input and change it to Monom
	 * @param monom proper input a*x^b 
	 */

	public Monom(String monom) throws Exception{
		// Monom equals to null throw exception
		if (monom==null)
			throw new Exception("String is null");
		// Monom equals to invalid String throw exception
		else if (monom.matches(".*[!-'].*" )|| monom.matches(".*[,].*" ) || monom.matches(".*[/].*") ||monom.matches(".*[:-W].*")|| monom.matches(".*[Y-]].*") ||
				monom.matches(".*[_-w].*") || monom.matches(".*[y-~].*") )
			throw new Exception("String is not legal");
		// Monom is equal to a valid String 
		else {
			monom=monom.replaceAll("[(,), ,*]","");
			// Checking if the Monom of the form : x, which means that coefficient equals to 1
			if ('x'==monom.charAt(0) || 'X'==monom.charAt(0)){
				this.set_coefficient(1);
				// Checking if both coefficient and power equal to 1
				if(monom.length()==1) {
					this.set_power(1);
				}
				// If power don't equals to 1
				else
					this.set_power(Integer.parseInt(monom.substring(2,monom.length())));
			}
			// Checking if the Monom contains *
			else if (!monom.contains("x") && !monom.contains("X")) {
				this.set_coefficient(Double.parseDouble(monom));
				this.set_power(0);
			}
			// Checking if the Monom contains ^
			else if (!monom.contains("^")){
				this.set_coefficient(Double.parseDouble(monom.substring(0,monom.length()-1)));
				this.set_power(1);
			}
			// Checking if the Monom contains both * and ^
			else {
				for (int i=0; i<monom.length(); i++) {
					if (monom.charAt(i)=='x' || monom.charAt(i)=='X') {
						this.set_coefficient(Double.parseDouble(monom.substring(0,i)));
					}
					else if (monom.charAt(i)=='^')	
						this.set_power(Integer.parseInt(monom.substring(i+1)));
				}
			}
		}
	}

	//Methods

	/**
	 * This method get a real number and calculates the Monom value
	 * @param  x the value of the number
	 * @return the value of the Monom at x
	 */

	@Override
	public double f(double x) {
		return _coefficient*Math.pow(x, _power);
	}

	/**
	 * This method add other Monom to this Monom.
	 * Add only if the Monoms have the same power
	 * @param other the other Monom's coefficient and power
	 */

	public void add(Monom other) throws Exception{
		// Add only the coefficients, the power stays the same
		// Adds on condition that both Monoms have the same power
		if (this.get_power()!=other.get_power())
			throw new Exception("The power of the monoms is diffrent");

		this.set_coefficient(this.get_coefficient()+other.get_coefficient());
	}

	/**
	 * This method multiply this Monom by other Monom.
	 * Multiply the coefficients and add the power
	 * @param other the other Monom's coefficient and power
	 */

	public void multiply(Monom other) throws Exception {
		this.set_coefficient(this.get_coefficient()*other.get_coefficient());
		this.set_power(this.get_power()+other.get_power());
	}

	/**
	 * This method derivative this Monom.
	 * Change this Monom to the derivative of this Monom
	 * Multiply the coefficient by power and subtract the power by one
	 */

	public void derivative() throws Exception {
		// Checking if power bigger then 0 or equal to 0
		// Power equal to 0. return 0.0 
		if (this.get_power()==0) 
			this.set_coefficient(0);
		// Power bigger then 0. return a*x^b 
		else {
			double a=(this.get_power()*this.get_coefficient());
			int b=(this.get_power()-1);
			this.set_coefficient(a);
			this.set_power(b);
		}
	} 

	/**
	 * This method subtract other Monom from this Monom.
	 * Subtract only if the power of this Monom equals to other Monom
	 * @param other the other Monom's coefficient and power
	 */

	public void subtract(Monom other) throws Exception{
		if (this.get_power()!= other.get_power())
			throw new Exception("The power of the monoms is diffrent");
		// Calculates the new value of the coefficient
		double new_ceofficient=this.get_coefficient()-other.get_coefficient();
		// Sets the new value of coefficient in this Monom
		this.set_coefficient(new_ceofficient);
	}


	/**
	 * This method prints the coefficient and the power the Monom
	 * @return the String of this Monom
	 */

	public String toString() {

		// Checking if coefficient equal to 0. return: 0.0
		if (this.get_coefficient()==0)
			return "0.0";
		// Checking if coefficient equal to 1 and power is not equal to 0
		else if (this.get_coefficient()==1 && this.get_power()!=0) {
			// Checking if power equal to 1. return : x .instead of : 1*x^1
			if (this.get_power()==1)
				return "x";
			// Checking if power equal to any other number. return : x^b .instead of : 1*x^b
			else
				return "x^"+this.get_power();
		}

		// Checking if coefficient is negative number its added in brackets : (-a)*x^b
		if (this.get_coefficient()<0)
			// Checking if power equals to 0. return : (-a)
			if (this.get_power()==0)
				return "("+this.get_coefficient()+")";
		// Checking if power equals to 1. return : (-a)*x
			else if (this.get_power()==1)
				return "("+this.get_coefficient()+")"+"*x";
		// Checking if power equals to any other number. return : (-a)*x^b
			else
				return "("+this.get_coefficient()+")"+"*x^" +this.get_power();

		// Checking if power equal to 0. return : a .instead of : a*x^0
		if (this.get_power()==0)
			return this.get_coefficient()+"";
		// Checking if power equal to 1. return : a*x .instead of : a*x^1
		else if (this.get_power()==1)
			return this.get_coefficient()+"*x";

		// Checking if both coefficient and power are bigger then 1 return : a*x^b
		return this.get_coefficient()+"*x^" +this.get_power();

	}

	//Getters

	/**
	 * This method get the coefficint of the Monom
	 * @return coefficient
	 */

	public double get_coefficient() {
		return _coefficient;
	}
	public boolean isEquals(Monom other) {
		if (this.get_coefficient()==other.get_coefficient() && this.get_power()==other.get_power())
			return true;
		return false;

	}

	/**
	 * This method get the power of the Monom
	 * @return power
	 */

	public int get_power() {
		return _power;
	}

	//****************** Private Methods and Data *****************

	//Setters

	/**
	 * This method set the coefficient of the Monom
	 * @param a the value of the coefficient
	 */

	private void set_coefficient(double a){
		this._coefficient = a;
	}

	/**
	 * This method set the power of the Monom.
	 * Set the power if its bigger or equal to 0
	 * @param b the value of the power
	 */

	private void set_power(int b) throws Exception {
		if(b>= 0) 
			this._power = b;
		else 
			throw new Exception("The power should be greater than or equal to 0");
	}


	private double _coefficient; //Represent a : a*x^b
	private int _power; //Represent b : a*x^b

}
