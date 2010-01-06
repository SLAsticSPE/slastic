package org.trustsoft.slastic.control.analysis.daLena;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

/**
 * Panel that show the development of the responesTimes over the Time and the belonging SLAs
 * @author Lena Stoever
 *
 */
public class SLACheckerGUI extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 572394L;
	public TimeSeries[] responseTimes;
	TimeSeries q90;
	long quantile90;
	long quantile95;
	long quantile99;
	TimeSeries q95;
	TimeSeries q99;
	String name; 
	private JFrame frame;
	
	/**
	 * 
	 * @param name Name that is shown in the title of the panel. 
	 * @param maxAge maximum age of the data that should be shown.
	 * @param Newquantile90 SLA for the 0.9-quantile
	 * @param Newquantile95 SLA for the 0.95-quantile
	 * @param Newquantile99 SLA for the 0.99-quantile
	 */
	public SLACheckerGUI(String name,int maxAge, long Newquantile90, long Newquantile95, long Newquantile99){
		
		
		super(new BorderLayout());
		this.name = name;
		quantile90 = Newquantile90;
		quantile95 = Newquantile95;
		quantile99 = Newquantile99;
		responseTimes = new TimeSeries[3];
		for(int i = 0; i< responseTimes.length; i++){
			responseTimes[i] = new TimeSeries("responseTime"+i);
		}
		
		 q90 = new TimeSeries("SLA for 0.9 quantile");
		 q95 = new TimeSeries("SLA for 0.95 quantile");
		 q99 = new TimeSeries("SLA for 0.99 quantile");
		
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		for(int i = 0; i < responseTimes.length; i++){
			dataset.addSeries(responseTimes[i]);
		}
		
		dataset.addSeries(q90);
		dataset.addSeries(q95);
		dataset.addSeries(q99);
		
		//create  x-axis
		DateAxis timeAxis = new DateAxis("Time");
		
		//create y-axis
		NumberAxis responseTimeAxis = new NumberAxis("responseTime");
		
		//initializing both axis'
		timeAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12)); 
		responseTimeAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12)); 
		timeAxis.setLabelFont(new Font("SansSerif", Font.PLAIN, 14)); 
		responseTimeAxis.setLabelFont(new Font("SansSerif", Font.PLAIN, 14)); 
		
		//create and initialize renderer for XY-Plot
		XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false); 
		renderer.setSeriesPaint(0, Color.red); 
		renderer.setSeriesPaint(1, Color.green); 
		renderer.setBaseStroke(new BasicStroke(3f, BasicStroke.CAP_BUTT, 
		BasicStroke.JOIN_BEVEL)); 
		
		//create and initialize plot
		XYPlot plot = new XYPlot(dataset, timeAxis, responseTimeAxis, renderer);
		plot.setBackgroundPaint(Color.lightGray); 
		plot.setDomainGridlinePaint(Color.white); 
		plot.setRangeGridlinePaint(Color.white); 
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));

		//configuring x-achis
		timeAxis.setAutoRange(true); 
		timeAxis.setLowerMargin(0.0); 
		timeAxis.setUpperMargin(0.0); 
		timeAxis.setTickLabelsVisible(true); 
		
		//configuring y-achis
		responseTimeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		//creating chart with the created plot
		JFreeChart chart = new JFreeChart("SLAChecker", 
				new Font("SansSerif", Font.BOLD, 24), plot, true); 

		
		chart.setBackgroundPaint(Color.white); 
		
		//creating chartPanel
		ChartPanel chartPanel = new ChartPanel(chart); 
		chartPanel.setBorder(BorderFactory.createCompoundBorder( 
		BorderFactory.createEmptyBorder(4, 4, 4, 4), 
		BorderFactory.createLineBorder(Color.black))); 
		

		
		this.add(chartPanel); 
	}
	
	/**
	 * Method that updates the data for the plots
	 * @param rt Array of responseTimes. One value for each quantile.
	 */
	public void addResponseTime(long[] rt){
		for(int i = 0; i< rt.length; i++){
			responseTimes[i].add(new Millisecond(), rt[i]);
		}
		
		this.q90.add(new Millisecond(), quantile90);
		this.q95.add(new Millisecond(),quantile95);
		this.q99.add(new Millisecond(), quantile99);
	}
	
	/**
	 * Method that is responsible for showing the plots. 
	 */
	public void paint(){
		frame = new JFrame(this.name); 
		frame.getContentPane().add(this, BorderLayout.CENTER);
		frame.setBounds(200, 120, 800, 400);
		
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				setVisible(false);
			}
		});
		
		
		
	}
	public void terminate(){
		frame.dispose();
	}
}
