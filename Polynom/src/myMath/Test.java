package myMath;
//package myMath;

import java.util.Iterator;

public class Test {

	
	public static void test1() {
		//zero test
		Polynom_able p1 = new Polynom();
		p1.add(new Monom(0.2,0));
		p1.add(new Monom(-0.1,0));
		p1.add(new Monom(-0.1,0));
		
		System.out.println("the answer should be zero:"+p1.toString());
		
		Polynom_able p2 = new Polynom(p1.toString());
		System.out.println("the answer should be zero:"+p2.toString());
		
		System.out.println("answer should be true:"+p2.equals(p2));//zero = zero
		
		//polynom test
		p2.add(new Monom(-0.3,0));
		p2.add(new Monom(4,5));
		p2.add(new Monom(-8,1));
		p2.add(new Monom(7.3,3));
		p2.add(new Monom(7,7));
		p2.add(new Monom(-0.2,8));
		p2.add(new Monom(5.2,11));
		
		System.out.println("get this polynom \"-0.3x^0 + -8.0x^1 + 7.3x^3 + 4.0x^5 + 7.0x^7 + -0.2x^8 + 5.2x^11\":");
		System.out.println("polynom is: "+p2);
		
		//copy 1 test
		p1 = new Polynom(p2.toString());
		System.out.println("get same polynom:");
		System.out.println("polynom is: "+p1);
		
		//copy 2 test
		p1 = p2.copy();
		System.out.println("get same polynom:");
		System.out.println("polynom is: "+p1);
		
		//equals 1 test
		p2.add(new Monom(0.2,0));
		p2.add(new Monom(-0.1,0));
		p2.add(new Monom(-0.1,0));
		System.out.println("get false:"+p1.equals(p2));
		
		//equals 2 test
		System.out.println("get true:"+p1.toString().equals(p1.toString()));
		
		
		//Iterator test
		System.out.println("get \"0, 1, 3, 5, 7, 8, 11,\":");
		Iterator<Monom> iterator = p1.iteretor();
		while(iterator.hasNext()){
			System.out.print(iterator.next().get_power() + ", ");
		}
		
		System.out.println();
		
		System.out.println(p2.isZero());
		System.out.println(p2.toString());
		System.out.println(p2.derivative());
		p2.substract(p1);
		System.out.println(p2.toString());
		System.out.println(p2.root(0, 10, 1));
		System.out.println(p2.equals(p1));
		System.out.println(p2.f(10));
		Monom m1=new Monom(1,5);
		Monom m2=new Monom(3,5);
		Monom m3=new Monom(3,5);
		System.out.println(m1.toString());
		m1.add(m2);
		System.out.println(m1.toString());
		System.out.println((m1.compareTo(m3)));
		System.out.println(m1.area(1, 5, 1));
		System.out.println(m1.f(4));
		System.out.println(m1.multiplay(m2));
		System.out.println(m1.derivative());
		System.out.println(m1.substract(m2));
		System.out.println(p2.area(1, 5, 1));


	





		
	}
	
	

/**
 * This class represents a simple main class with some basic tests (not JUnit Testing - should be using Junit!!)
 * @author Boaz
 *
 */


	
	private static void test_equals() {
		System.out.printf("equal test");
		double a1 = 2.5, a2 = 3;
		int b1 = 2, b2 = 4;
		Monom m1 = new Monom(a1,b1);
		Monom m2 = new Monom(a1,b1+1);
		Monom m3 = new Monom(a1+0.001,b1);
		Monom m4 = new Monom(m1);
		System.out.println(m1+" != "+m2);
		System.out.println(m1+" != "+m3);
		System.out.println(m1+" == "+m4);
		if(m1.equals(m2)) {
			System.err.println(m1+" != "+m2);
			throw new RuntimeException("Error: Monom equals method is wrong!");		
		}
		if(m1.equals(m3)) {
			System.err.println(m1+" != "+m3);
			throw new RuntimeException("Error: Monom equals method is wrong!");		
		}
		if(!m1.equals(m4)) {
			System.err.println(m1+" == "+m4);
			throw new RuntimeException("Error: Monom equals method is wrong!");		
		}
	}
	private static void test_multiply() {
		double a1 = 2.5, a2 = 3;
		int b1 = 2, b2 = 4;
		Monom m1 = new Monom(a1,b1);
		Monom m2 = new Monom(a2,b2);
		Monom m3 = new Monom(m1);
		m3.multiplay(m2);
		
		System.out.println(m1+" * "+m2+" = "+m3);
		Monom m4 = new Monom(a1*a2, b1+b2);
		if(!m3.equals(m4)) {
			System.err.println(m3+" != "+m4);
			throw new RuntimeException("Error: Monom multiply method is wrong!");		
		}
	}
	private static void test_add() {
		double a1 = 2.5, a2 = 3;
		int b1 = 2, b2 = 4;
		Monom m1 = new Monom(a1,b1);
		Monom m3 = new Monom(m1);
		m3.add(m1);
		
		System.out.println(m1+" + "+m1+" = "+m3);
		Monom m4 = new Monom(a1+a1, b1);
		if(!m3.equals(m4)) {
			System.err.println(m3+" != "+m4);
			throw new RuntimeException("Error: Monom add method is wrong!");		
		
	
}
		}
	
	public static void main(String[] args) {
		System.out.println("Test1");
		test1();

		
	
		

	}
	}
	
		
