package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	// ******** add your code below *********
	/**
	 * a comparing function, helps us deciding if two monoms are equals or not
	 */
	@Override
	public int compare(Monom arg0, Monom arg1) {
		// TODO Auto-generated method stub
		if(arg0.compareTo(arg1) > 0)
			return 1;
		else if( arg0.compareTo(arg1) == 0)
			return 0;
		return -1;
	}
}
