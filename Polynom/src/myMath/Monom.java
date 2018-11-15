package myMath;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	
	/**
	 * this is a Starting Constructor of the Monom
	 * @param a the coefficient of Monom
	 * @param d the power of Monom
	 */
	public Monom(double a, double d){
		this.set_coefficient(a);
		this.set_power((int)d);
		if(d<0)
			this.set_power(0);
	}
	/**
	 * this is a Copy Constructor, Copying the current monom into another
	 * @param ot the new monom that got in it everything current monom have (same coeff and power)
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	// ***************** add your code below **********************
	/**
	 * this function helps us with area len solving
	 */
	////////////
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		return _coefficient * Math.pow(x, _power);
	}
	/**
	 * this function compares our monom with another one
	 * @param arg1 the other monom that we are comparing
	 * @return returns if the two monoms are equal or not
	 */
	public double compareTo(Monom arg1) {
		// TODO Auto-generated method stub
		return (_power == arg1._power ? _coefficient - arg1._coefficient : _power - arg1._coefficient);
	}
	/**
	 * this function gives us the derivative of the monom
	 * @return derivative of the monom
	 */
	public Monom derivative() {
		//double a = this.get_coefficient()*this.get_power();
		//int b = this.get_power() - 1;
		//return new Monom(a,b);
		this.set_coefficient(this.get_coefficient()*this.get_power());
		this.set_power(this.get_power()-1);
	
	return this;}
	/**
	 * an adding function
	 * @param other the other monom we want to add it to our current monom
	 * @return the current monom with the addition of the other monom
	 */
	public Monom add(Monom other) {
		if(rankDiffrence(other)==0 || _coefficient == 0)
			this.set_coefficient(other.get_coefficient() + this.get_coefficient());
		if(other._coefficient == 0)
			return this;
		return null;
	}
	/**
	 * Multipy function
	 * @param other the other monom we multiplying by
	 * @return new monom with multiplying coeff and power
	 */
	public Monom  multiplay(Monom other) {
		this.set_coefficient(this.get_coefficient() * other.get_coefficient());
		this.set_power(this.get_power() + other.get_power());
		return this;
		}
	/**
	 * substracting function
	 * @param other the other monom we want to subtract our current monom from
	 * @return new monom with subtracted data
	 */
	public Monom substract(Monom other) {
		this.set_coefficient(this.get_coefficient() - other.get_coefficient());

		//if(other._coefficient == 0)
		//	return this;
		return this;
	}
	/**
	 * helping function
	 * @param other another monom 
	 * @return the difference in power between this monom and other monom
	 */
	public int rankDiffrence(Monom other) {
		return _power-other._power;
	}
	/**
	 * area function
	 * @param x0 the starting point 
	 * @param x1 the finishing point 
	 * @param eps the jump between
	 * @return the area length 
	 */
	public double area(double x0, double x1, double eps) {
		double ans = 0;
		for(double x = x0; x < x1; x+=eps)
			ans += f(x);
		return ans;
	}

	//****************** Private Methods and Data *****************
	/**
	 * get power
	 * @return current power
	 */
	public int get_power() {
		// TODO Auto-generated method stub
		return _power;
	}
	/**
	 * get coeff
	 * @return current coeff
	 */
	public double get_coefficient() {
		// TODO Auto-generated method stub
		return _coefficient;
	}
	/**
	 * set coeff
	 * @param a new coeff we are setting
	 */
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	/**
	 * set power
	 * @param p new power we are setting
	 */
	private void set_power(int p) {
		this._power = p;
	}
	
	/////////////////////////////
	/**
	 * constructor that input a string
	 * @param s monom written in string
	 */
	public Monom(String s) {
		Monom th = init_from_string(s);
		this.set_coefficient(th.get_coefficient());
		this.set_power(th.get_power());
	}
	/**
	 * translate string monom to numbers monom
	 * @param s string monom
	 * @return monom
	 */
	private static Monom init_from_string(String s) {
		if(s==null) {throw new RuntimeException("Wrong parameter for the Monom Constractor - should not be NULL!!!");}
		double  coef = 1;
		int pow = 0;
		Monom ans = null;
		if(s.contains("x")) {
			int ind = s.indexOf("x");
			String co = s.substring(0, ind);
			if(co.length()>0) {
				double c = Double.parseDouble(co);
				// Double.parseDouble("#$%");
			}
			try{
				double c = Double.parseDouble(co);
				coef = c;
			}
			catch(Exception e) {coef = 1;}
			if(s.length()>ind+2) {
				String po = s.substring(ind+2);
					int p = Integer.parseInt(po);
					//  Integer.parseInt("1.1");
					//  Integer.parseInt("dr4$");
					pow = p;
			}
		}
		else {  // just number ==> power = 0;
			coef = Double.parseDouble(s);
		}
		
		ans = new Monom(coef, pow);	
		return ans;
	}
	/**
	 * gives back the string massege
	 */
	public String toString() {
		return ""+this.get_coefficient()+"x^"+this.get_power();
	}
	//////////////////////////////////////

	private double _coefficient;  
	private int _power;
	
	
}
