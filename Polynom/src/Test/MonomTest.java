package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import myMath.Monom;

public class MonomTest {

	private static final boolean True = false;

	@Test
	public void testMonomDoubleInt() {
		double a_forTesting = 3.5 ;
		int pow_forTesting = 5 ;
		Monom m = new Monom(a_forTesting , pow_forTesting);
		if(m.get_coefficient() != 3.5 || m.get_power() != 5)
		fail("Error, there is a problem in defining the cofficient or the power(problem in constructing the monom from numbers)!");
	}

	@Test
	public void testMonomMonom() {
		Monom m1 = new Monom(3 , 2);
		Monom m2=(m1);
        if(m1.get_coefficient()!=m2.get_coefficient()||m1.get_power()!=m2.get_power())
		fail("Error, there is a problem in constracting the monom from another monom!");
	}

	@Test
	public void testF() {
		//_coefficient * Math.pow(x, _power)
		double _a = 5 ;
		int _pow = 3 ;
		Monom m = new Monom(_a ,_pow);
		int _x = 4 ;
		double correct_answer = _a * Math.pow(_x, _pow);
		if((m.f(_x)) != correct_answer )
		fail("Error, the expected answer of the function F is uncorrect!");
	}

	@Test
	public void testDerivative() {
		double _a = 5 ;
		int _pow = 3 ;
		Monom m = new Monom(_a , _pow);
		m.derivative();
		if(m.get_coefficient() != 15 || m.get_power() != 2)
		fail("Error, the derivative of the monom is uncorrect! ");
	}

	@Test
	public void testAdd() {
		double a1 = 5 ;
		double a2 = 10 ;
		int _pow = 3 ;
		Monom m1 = new Monom(a1 , _pow);
		Monom m2 = new Monom(a2 , _pow);
		m1.add(m2);
		if(m1.get_coefficient() != 15 || m1.get_power() != 3)
		fail("Error, there is a problem in adding another monom to this one!");
	}

	@Test
	public void testMultiplay() {
		double a1 = 5 ;
		double a2 = 10 ;
		int _pow1 = 3 ;
		int _pow2 = 4 ;
		Monom m1 = new Monom(a1 , _pow1);
		Monom m2 = new Monom(a2 , _pow2);
		m1.multiplay(m2);
		if(m1.get_coefficient() != 50 || m1.get_power() != 7 )
		fail("Error, there is a problem in multiplaying this monom by the other monom!");
	}

	@Test
	public void testSubstract() {
		Monom m1 = new Monom(30,1);
		Monom m2 = new Monom(12,1);
		m1.substract(m2);
		if (m1.get_coefficient() != 18)
		fail("Error, uncorrect answer of the sub!");
	}

	@Test
	public void testMonomString() {
		String _str = "" + 5 + "x^" + 3 ;
		Monom m = new Monom(_str);
		if(m.get_coefficient() != 5 || m.get_power() != 3)
		fail("Error,there is a problem in constructing a monom from a string!");
	}

	@Test
	public void testToString() {
		double a=5;
		int _pow=10;
		Monom m=new Monom(a,_pow);
		
			if(m.toString().equals(""+5+"x^"+10)!=True)
				fail("Error,the written string is uncorrect!");
	
	}

}
