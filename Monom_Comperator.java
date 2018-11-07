package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {
	
	/**
	 * This method compare between two Monoms
	 * @param m1 includes the coefficient and power of Monom m1
	 * @param m2 includes the coefficient and power of Monom m2
	 * @return -1: if m1 has bigger power, 1: if m2 has bigger power, 0: if they have equal power
	 */
	@Override
	public int compare(Monom m1, Monom m2) {
		if (m1.get_power() > m2.get_power())
			return -1;
		else if (m1.get_power()< m2.get_power()) 
			return 1;
		else
			return 0;
	}







}
