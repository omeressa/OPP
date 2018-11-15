package myMath;
/**
 * We took this path code from: https://github.com/eseifert/gral.git
 * @author Omar Essa & Warda Essa
 */
import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;


public class LinePlotTest extends JFrame {

	public LinePlotTest() {
		Polynom  p=new Polynom("5x^2+6x+6+5x^2-9x^3");
//		p.add(new Monom(1,3));
//		p.add(new Monom(8,0));
//		p.add(new Monom(2,0));
//		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 800);
		
		DataTable data = new DataTable(Double.class, Double.class);

		for(double x=-5.0;x<=5.0;x+=0.25){
			double y=p.f(x);
			data.add(x,y);
		}

		XYPlot plot = new XYPlot(data);
		getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines = new DefaultLineRenderer2D();

		plot.setLineRenderers(data,lines);

		Color color = new Color(0.4f, 0.8f, 0.0f);

		plot.getPointRenderers(data).get(0).setColor(color);

		plot.getLineRenderers(data).get(0).setColor(color);

	}

	public static void main(String[] args) {
		LinePlotTest frame = new LinePlotTest();
		frame.setVisible(true);
	}
}