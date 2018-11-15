package myMath;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;

import fr.julien.graphique.Graphique;
import fr.julien.graphique.ZoneGraphique;
import fr.julien.graphique.axes.AxeX;
import fr.julien.graphique.axes.AxeY;
import fr.julien.graphique.axes.OptionAxe;
import fr.julien.graphique.element.fonction.Fonction;
import fr.julien.graphique.element.forme.Polygone;
import fr.julien.graphique.element.quadrillage.Quadrillage;


/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	// ********** add your code below ***********

	/**
	 * List of Monoms
	 */
	 ArrayList<Monom> polynom;
	/**
	 * this is an empty Constructor,gives us zero constructor is we refere to in isZero Function 
	 */
	public Polynom() {
		polynom = new ArrayList<Monom>();
	}
    /**
     * this is a  Constructor , adding all the monoms into a polynom in order
     * @param monoms is the list of monoms we are adding to polynom
     */
	public Polynom(List<Monom> monoms) {
		polynom = new ArrayList<Monom>();
		for(Monom monom:monoms)
			polynom.add(new Monom(monom));
		Collections.sort(polynom,new Monom_Comperator());
	}
	/**
	 * this here is a Copy Constructor,
	 * @param PolyMonom the monom list we want to copy
	 */
	public Polynom(Polynom PolyMonom) {
		polynom = new ArrayList<Monom>();
		for(Monom monom:PolyMonom.polynom)
			polynom.add(new Monom(monom));
		Collections.sort(polynom,new Monom_Comperator());
	}
	/**
	 * this function help in solving area
	 */
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		double ans = 0;
		for(Monom monom:polynom)
			ans += monom.f(x);
		return ans;
	}
	/**
	 * indxOfRank function
	 * @param m the monom
	 * @return the rank of the index
	 */
	private int indxOfRank(Monom m) {
		return indxOfRank(m,0,polynom.size());
	}
	
	private int indxOfRank(Monom m, int high, int low) {
		int mid = (high + low)/2;
		if(m.rankDiffrence(polynom.get(mid)) == 0)
			return mid;
		if(m.rankDiffrence(polynom.get(mid - 1)) < 0 && m.rankDiffrence(polynom.get(mid)) > 0) {
			return mid;
		}
		if(m.rankDiffrence(polynom.get(mid)) > 0)
			return indxOfRank(m,high,mid);
		return indxOfRank(m, mid, low);
	}
    /**
     * adding to polynom_able
     */
	@Override
	public void add(Polynom_able p1) {
		// TODO Auto-generated method stub

		Iterator<Monom> iter = p1.iteretor();
		while(iter.hasNext()) {
			Monom m = iter.next();
			this.add(m);

		}
		}

    /**
     * add anither monom
     */
	@Override
	public void add(Monom m1) {
		// TODO Auto-generated method stub
		boolean found_power = false;
		Iterator<Monom> iter = this.iteretor();
		while(!found_power && iter.hasNext()) {
			Monom m = iter.next();
			if(m.get_power()==m1.get_power()) { // same power
				m.add(m1);
				found_power = true;
				if(m.get_coefficient()==0) { // 	if(m.get_coefficient()==0)
					iter.remove();
				}
			}
		}
		if(!found_power) {
			polynom.add(m1);
			this.polynom.sort(COMP);
		}
	}
    /**
     * sub p1 from polynom
     */
	@Override
	public void substract(Polynom_able p1) {
		// TODO Auto-generated method stub
        /*this code i took from github , it is an easy code that deal with sub in the perfect way possible*/
		Iterator<Monom> it = p1.iteretor();
		while (it.hasNext()){
			Monom m = it.next();
			Monom m2 = getPow(m.get_power());
			if (m2 == null){
				add(new Monom(-m.get_coefficient(), m.get_power()));
			} 
			else{
				m2.substract(m);
			}
		}
	}
	/**
	 * helping function
	 * @param pow the pow we want to check 
	 * @return pow if it is in the polynom
	 */
	
	public Monom getPow(int pow)
	{
		Iterator<Monom> it = iteretor();
		while (it.hasNext())
		{
			Monom m = it.next();
			if (m.get_power() == pow)
			{
				return m;
			}
		}
		return null;
	}


    /**
     * multiply this polynom by p1
     */
	@Override
	public void multiply(Polynom_able p1) {
		// TODO Auto-generated method stub
		Iterator<Monom> it = p1.iteretor();
		Polynom original = new Polynom(this);
		Polynom_able p = copy();
		if (it.hasNext()){
			Monom m = it.next();
			multiply(this, m);
		}
		while (it.hasNext()){
			Monom m = it.next();
			multiply(p, m);
			add(p);
			p = original.copy();
		}
		simplify();
	}
	/**
	 * helping Function
	 */
	public void simplify(){
		this.polynom.sort(new Monom_Comperator());
		Iterator<Monom> it = iteretor();
		if (!it.hasNext())
			return;
		Monom m1 = it.next();
		while (it.hasNext()){
			Monom m2 = it.next();
			if (m1.get_power() == m2.get_power()){
				m1.add(m2);
				it.remove();
			}
			m1 = m2;
		}
	}


/**
 * helping function
 * @param p polynom
 * @param m monom
 */
	public void multiply(Polynom_able p, Monom m)
	{
		Iterator<Monom> it = p.iteretor();
		while (it.hasNext())
		{
			Monom m2 = it.next();
			m2.multiplay(m);
		}
	}

    /**
     * a boolean function to to find if one polynom if equal to the other
     */
	@Override
	public boolean equals(Polynom_able p1) {
		// TODO Auto-generated method stub
		Polynom tmp = (Polynom) p1;
		int i = 0;
		for(Monom monom:polynom) {
			if(tmp.polynom.get(i++).compareTo(monom)!=0)
				return false;
		}
		return true;
	}
    /**
     * a function to see if the polynom is zero or not
     */
	@Override
	public boolean isZero() {
		// TODO Auto-generated method stub
		if(this.polynom.size()==0)return true;
		return false;
	}
    /**
     * function gives us the root
     */
	@Override
	public double root(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		double y0 = this.f(x0);
		double y1 = this.f(x1);
		
		if(y0*y1>0) {
			throw new RuntimeException("Error: f(x0) and f(x1) should be on oposite sides of the X asix");
		}
		
		double delta_x = Math.abs(x0-x1);
		double delta_y = Math.abs(y0-y1);
		if(true) {
			System.out.println("f("+x0+") = "+y0+"    f("+x1+") = "+y1+"   dx = "+delta_x);
		}
		if (delta_x>eps || delta_y>eps) {
			double x_mid = (x0+x1)/2;
			double y_mid = this.f(x_mid);
			double dir = y0*y_mid;
			if(dir<0) {
				return root(x0,x_mid, eps);
			}
			else {
				return root(x_mid, x1, eps);
			}
		}
		return x0;
	
	}
    /**
     * sets a copy of polynom_able and return it
     */
	@Override
	public Polynom_able copy() {
		// TODO Auto-generated method stub
		Polynom_able ans = new Polynom(this);
		return ans;
	}
    /**
     * gets the derivative for each monom in the polynom
     */
	@Override
	
	public Polynom_able derivative() {
		// TODO Auto-generated method stub
		Polynom temp = new Polynom(this);
		Polynom_able ans = new Polynom(temp);
		Iterator<Monom> iter = this.iteretor();
		while(iter.hasNext()) {
			Monom m = iter.next();
			m.derivative();
			temp.add(m);
		}
		return ans;
	}
    /**
     * function gives us the area length
     */
	@Override
	public double area(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		double ans = 0;
		for(double x = x0; x < x1; x+=eps)
			for(Monom m:polynom)
				ans += m.area(x0, x1, eps);
		return ans;
	}
    /**
     * iterator
     */
	@Override
	public Iterator<Monom> iteretor() {
		// TODO Auto-generated method stub
		return polynom.iterator();
	}
	/**
	 * constructor with string input
	 * @param s string monom
	 */
	public Polynom(String s) {
/*
		polynom = new ArrayList<Monom>() ;
		Polynom th =init_from_string(s);
		Iterator<Monom> iter = th.iteretor();
		while(iter.hasNext()) {
			this.add(iter.next());
		}*/
		polynom = new ArrayList<Monom>();
		s=s.toLowerCase();
		s=s.replaceAll(" ", "");
		s=s.replaceAll("-", "+-");
		String[] new_string = s.split("\\+");
		if (new_string.length>1 && new_string[0].isEmpty())
			for (int i = 1; i < new_string.length; i++)
				add(new Monom(new_string[i]));
		else
		{
			for (int i = 0; i < new_string.length; i++)
				add(new Monom(new_string[i]));
		}
		Monom_Comperator ans = new Monom_Comperator();
		this.polynom.sort(ans);
	
	}
	/**
	 * translate string monom to numbers monom
	 * @param s string monom
	 * @return monom in numbers
	 */
	private static Polynom init_from_string(String s) {
		if(s==null) {throw new RuntimeException("Wrong parameter for the Monom Constractor - should not be NULL!!!");}
		String[] params = s.split(" ");
		Polynom ans = new Polynom();
		for(int i=0;i<params.length;i++) {
			String t = params[i];
			if(!t.contains("+") || t.length()>1) {
				Monom c = new Monom(t);
				ans.add(c);
			}
		}
		return ans;
	}
	/**
	 * gives back the string massege
	 */
	public String toString() {
		String ans = "";
		if(this.isZero()) {
			return "0";
		}
		Iterator<Monom> iter = this.iteretor();
		while(iter.hasNext()) {
			Monom m0 = iter.next();
			ans += m0 +" + ";
		}
		return ans;
	}
	
	private static final Monom_Comperator COMP = new Monom_Comperator();

	
	
	/****
	 * **************************************************************************
	 */



	@Override
	public void PrintMinMax(String s, double x0, double x1) {

		double start = x0;
		double end = x1;


		Polynom der = (Polynom) this.derivative();
		double eps = 0.0001;
		for (;start < end ; start=start+eps) {
			if(der.f(start) < 0 && der.f(start+eps) > 0) {
				polynom.add(new Monom(start,this.f(start)));
			}
			if(der.f(start) > 0 && der.f(start+eps) < 0) {
				polynom.add(new Monom(start,this.f(start)));
			}

		}
		Draw_MinMax(s,polynom,x0,x1);

	}

	private static void Draw_MinMax(String s, List<Point> polynom, double x0, double x1) {

		JFrame f = new JFrame();		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		OptionAxe optionsAxes = new OptionAxe(Color.BLACK, true, -1, 1, true, true);
		Graphique.getInstance().initGraphique(new AxeX(x0, 10, optionsAxes), new AxeY(-10, x1, optionsAxes));
		char a = 'A';
		for (int i = 0 ; i < polynom.size(); i++, a++) {
			Graphique.getInstance().ajouterElement(new Point(a, polynom.get(i).getAbscisse(),polynom.get(i).getOrdonnee()));
		}
		Graphique.getInstance().ajouterElement(new Fonction((s)));
        ArrayList<Monom> Monoms = new ArrayList<Monom>();
		Graphique.getInstance().ajouterElement(new Polygone(Monom));
		Graphique.getInstance().ajouterElement(new Quadrillage(0.5, 0.5));
		f.add(new ZoneGraphique());
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);

	}
*/	
}
