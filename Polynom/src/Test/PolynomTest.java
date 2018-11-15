package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import myMath.Polynom;
import myMath.Polynom_able;
import myMath.Monom;

public class PolynomTest {

	private static final boolean False = false;
	private static final boolean True = false;

	@Test
	public void testPolynom() {
		Polynom p = new Polynom();
		if(p.isZero()==False)
			fail("Eror ,Polynom is not empty");
	}

	@Test
	public void testPolynomPolynom() {
		Polynom p1=new Polynom();		
		p1.add(new Monom(3,8));
		p1.add(new Monom(5,3));
		Polynom p2=(p1);
		if(!p2.equals(p1))
		fail("Error, there is a problem in constructing a copy polynom!");
	}

	@Test
	public void testF() {
		Monom m1 = new Monom(5 , 3);//320
		int x=4 ;
		Monom m2 = new Monom (3 , 2);//48
		Polynom p = new Polynom();
		p.add(m1);
		p.add(m2);
		if(p.f(x) != 320+48)
		fail("Error, F function is giving a wrong answer!");
	}

	@Test
	public void testAddPolynom_able() {
		Polynom p1=new Polynom();
		p1.add(new Monom(2, 3));
		p1.add(new Monom(6, 2));
		p1.add(new Monom(7, 1));
		p1.add(new Monom(10, 0));
		Polynom p2=new Polynom();
		p1.add(p2);
		if(p1.equals(p1)==False)
		fail("Error, there is a problem in adding all the monoms !");
	}

	@Test
	public void testAddMonom() {
		Polynom p1=new Polynom();
		Polynom p2=new Polynom();
		p1.add(new Monom(2,2));
		p1.add(new Monom(2,1));
		p1.add(new Monom(2,0));
		Monom m1=new Monom(2,2);
		Monom m2=new Monom(2,1);
		Monom m3=new Monom(2,0);

		p2.add(m1);
		p2.add(m2);
		p2.add(m3);
		if(p2.equals(p1)==False)
		fail("Error, there is a problem in adding the monoms");
	}

	@Test
	public void testSubstract() {
		Polynom p1=new Polynom();
		p1.add(new Monom(2, 3));
		p1.add(new Monom(6, 2));
		p1.add(new Monom(6, 1));
		p1.add(new Monom(11, 0));
		Polynom p2=new Polynom();
		p2.add(new Monom(4, 2));
		p2.add(new Monom(2, 1));
		p2.add(new Monom(6, 0));
		p1.substract(p2);
		Polynom p3= new Polynom();
		p3.add(new Monom(2, 3));
		p3.add(new Monom(2, 2));
		p3.add(new Monom(4, 1));
		p3.add(new Monom(5, 0));

		if(!p3.equals(p1))
		fail("Error, there is a problem in sub the poylenoms!");
	}

	@Test
	public void testMultiply() {
		
		Polynom p1 = new Polynom();
		Polynom p2 = new Polynom();
		p1.add(new Monom(2, 2));
		p1.add(new Monom(2, 3));
		p2.add(new Monom(4, 5));
		p2.add(new Monom(5, 5));
		Polynom p12 = new Polynom(p1);
		p12.multiply(p2);
		Polynom p21 = new Polynom(p2);
		p21.multiply(p1);

		if (!p12.equals(p12))
		fail("Error, there is a problem in multiplying this polynom!");
	}

	@Test
	public void testIsZero() {
		Polynom p = new Polynom() ;
		if(!p.isZero())
		fail("Error, the polynom is not empty!");
	}

	@Test
	public void testRoot() {
		Polynom p1 = new Polynom();
		p1.add(new Monom(1, 3));
		if (p1.f(p1.root(-10, 100.1, 0.001)) > 0.001)
		  fail("Error,the root is bigger than the eps!");
		
	}

	@Test
	public void testCopy() {
		Polynom p1 = new Polynom();
		Polynom_able p2 = new Polynom();
        p1.add(new Monom(3,2));
        p2=p1.copy();
        if(!p1.equals(p2))
		fail("Error, there is a problem in the copy!");
	}

	@Test
	public void testDerivative() {
		Polynom p1=new Polynom();
		Polynom p2=new Polynom();

		p1.add(new Monom(2,2));
		p1.add(new Monom(2,1));
		p2.add(new Monom(4,1));
		p2.add(new Monom(2,0));
		p1.derivative();
		if(!p1.equals(p2))
		fail("Error, the derivative is wrong!");
	}

	@Test
	public void testArea() {/*
		Polynom p1=new Polynom();
		p1.add(new Monom(1,2));
		p1.add(new Monom(6,1));
		p1.add(new Monom(5,0));
		double expected = -32/3;
		if(expected!=(p1.area(-5, -1, 0.00)))
		fail("Error, the area you culclated is wrong!");*/
		Polynom p1 = new Polynom();
		p1.add(new Monom(4, 2));
		Polynom p2 = new Polynom();
		p2.add(new Monom(4, 2));
		Polynom p3 = new Polynom();
		p3.add(new Monom(10, 2));
		if (p1.area(0, 1, 0.001) != (p1.area(0, 1, 0.001)))
			fail("area is inconsistent");
		if (p1.area(0, 1, 0.001) != (p2.area(0, 1, 0.001)))
			fail("area is object-specific");
		if (p1.area(0, 1, 0.001) == (p3.area(0, 1, 0.001)))
			fail("area is same for non-equal objects");
	}

	@Test
	public void testPolynomString() {

		Polynom p1 = new Polynom("4x^6 + 4x^3 - 4");

		Polynom check_ans = new Polynom();
		check_ans.add(new Monom("4x^6"));
		check_ans.add(new Monom("4x^3"));
		check_ans.add(new Monom("-4"));
		if (!p1.toString().equals(check_ans.toString()))
			fail("Not yet implemented");
	}


}
