package org.trustsoft.slastic.control.analysis;

import java.awt.BasicStroke; 
import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Font; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 
import javax.swing.BorderFactory; 
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.Timer; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.axis.DateAxis; 
import org.jfree.chart.axis.NumberAxis; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.renderer.xy.XYItemRenderer; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer; 
import org.jfree.data.time.Millisecond; 
import org.jfree.data.time.TimeSeries; 
import org.jfree.data.time.TimeSeriesCollection; 
import org.jfree.ui.RectangleInsets; 

public class SLACheckerGUI extends JPanel{
	public static TimeSeries responseTimes;
	static TimeSeries q90;
	static long quantile90;
	static long quantile95;
	static long quantile99;
	static TimeSeries q95;
	static TimeSeries q99;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SLACheckerGUI(int maxAge, long Newquantile90, long Newquantile95, long Newquantile99){
		super(new BorderLayout());
		quantile90 = Newquantile90;
		quantile95 = Newquantile95;
		quantile99 = Newquantile99;
		responseTimes = new TimeSeries("responseTimes", Millisecond.class);
		responseTimes.setMaximumItemAge(maxAge);
		 q90 = new TimeSeries("quantile90", Millisecond.class);
		 q95 = new TimeSeries("quantile95", Millisecond.class);
		 q99 = new TimeSeries("quantile90", Millisecond.class);
		
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(responseTimes);
		dataset.addSeries(q90);
		dataset.addSeries(q95);
		dataset.addSeries(q99);
//		TimeSeriesCollection datasetQ90 = new TimeSeriesCollection();
//		datasetQ90.addSeries(q90);
//		TimeSeriesCollection datasetQ95 = new TimeSeriesCollection();
//		datasetQ95.addSeries(q95);
//		TimeSeriesCollection datasetQ99 = new TimeSeriesCollection();
//		datasetQ99.addSeries(q99);
		
		
		DateAxis timeAxis = new DateAxis("Time");
		NumberAxis responseTimeAxis = new NumberAxis("responseTime");
		timeAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12)); 
		responseTimeAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12)); 
		timeAxis.setLabelFont(new Font("SansSerif", Font.PLAIN, 14)); 
		responseTimeAxis.setLabelFont(new Font("SansSerif", Font.PLAIN, 14)); 
		XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false); 
		renderer.setSeriesPaint(0, Color.red); 
		renderer.setSeriesPaint(1, Color.green); 
		renderer.setStroke(new BasicStroke(3f, BasicStroke.CAP_BUTT, 
		BasicStroke.JOIN_BEVEL)); 
		
		
		XYPlot plot = new XYPlot(dataset, timeAxis, responseTimeAxis, renderer);
		plot.setBackgroundPaint(Color.lightGray); 
		plot.setDomainGridlinePaint(Color.white); 
		plot.setRangeGridlinePaint(Color.white); 
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));

		
		timeAxis.setAutoRange(true); 
		timeAxis.setLowerMargin(0.0); 
		timeAxis.setUpperMargin(0.0); 
		timeAxis.setTickLabelsVisible(true); 
		responseTimeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		JFreeChart chart = new JFreeChart("SLAChecker", 
				new Font("SansSerif", Font.BOLD, 24), plot, true); 

		
		chart.setBackgroundPaint(Color.white); 
		
		ChartPanel chartPanel = new ChartPanel(chart); 
		chartPanel.setBorder(BorderFactory.createCompoundBorder( 
		BorderFactory.createEmptyBorder(4, 4, 4, 4), 
		BorderFactory.createLineBorder(Color.black))); 
		

		
		add(chartPanel); 
	}
	
	public static void addResponseTime(long rt){
		responseTimes.add(new Millisecond(), rt);
		q90.add(new Millisecond(), quantile90);
		q95.add(new Millisecond(),quantile95);
		q99.add(new Millisecond(), quantile99);
	}
	
	public static void paint(long quantile90, long quantile95, long quantile99){
		JFrame frame = new JFrame("SLAChecker Visualization"); 
		System.out.println(quantile90+" "+quantile95+" "+quantile99);
		SLACheckerGUI gui = new SLACheckerGUI(30000, quantile90, quantile95, quantile99);
		frame.getContentPane().add(gui, BorderLayout.CENTER);
		frame.setBounds(200, 120, 800, 400);
		
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		
		
	}
}
