package main.frame;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import main.component.chart.Chart;
import main.component.chart.GraphicProperty;
import main.component.chart.data.MetaData;
import main.component.chart.data.calculator.Calculators;
import main.component.chart.factory.CompareScrollChartFactory;
import main.component.chart.factory.ComplexChartFactory;
import main.component.chart.factory.EnvelopScrollChartFactory;
import main.component.chart.factory.ExternalValuedColorChartFactory;
import main.component.chart.factory.FixedSizeChartFactory;
import main.component.chart.factory.LineScrollChartFactory;
import main.component.chart.factory.RainbowChartFactory;
import main.component.chart.factory.ScrollChartFactory;
import main.component.chart.factory.StaticMinMaxChartFactory;
import main.component.chart.factory.TextLabelChart;
import main.component.chart.factory.ValuedColorChartFactory;
import main.component.chart.factory.ZeroCenteredChartFactory;
import main.component.chart.factory.ZeroCenteredCompareChartFactory;
import main.component.chart.theme.Theme;
import main.component.chart.theme.themes.BlackTheme;
import main.component.chart.theme.themes.BlueTheme;
import main.component.chart.theme.themes.GoldTheme;
import main.component.chart.theme.themes.GreenTheme;
import main.component.chart.theme.themes.RedTheme;
import main.component.chart.theme.themes.WhiteTheme;
import main.component.chart.theme.themes.YellowTheme;

public class MainFrame extends JFrame{
	public static final int BOARDER_SIZE = 10;
	List<Chart> charts = new ArrayList<Chart>();
	DataContainer dataContainer = new DataContainer();
	
	GridBagLayout layout = new GridBagLayout();
	GridBagConstraints constraints = new GridBagConstraints();
	JScrollPane scrollPane = new JScrollPane();
	JPanel panel = new JPanel();
	
	private void initGridBagConstraint(){
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.gridwidth = GridBagConstraints.RELATIVE;
		constraints.ipady = (int)(getWidth() / 2.0 / GraphicProperty.chartWidthOverHeight()) - BOARDER_SIZE;
	}
	
	private void rearrange(){
		initGridBagConstraint();
		panel.removeAll();
		
		for(int i = 0; i < charts.size(); i++){
			layout(panel, charts.get(i), i%2, i/2, 1, 1);
		}
	}
	
	public void initScrollPane(){
		scrollPane.getViewport().add(panel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(30);
		add(scrollPane);
	}
	
	public void initMainFrame(){
		setSize(1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public MainFrame(){
		initMainFrame();
		initScrollPane();
		initGridBagConstraint();
		
		panel.setLayout(layout);

		Chart chart;
		
		chart = ComplexChartFactory.create(
				new MetaData<Integer>("Top Boundary", dataContainer.envelopList1, Calculators.INTEGER),
				new MetaData<Integer>("BaseLine", dataContainer.envelopList2, Calculators.INTEGER),
				new MetaData<Integer>("Bottom Boundary", dataContainer.envelopList3, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer1", dataContainer.compareBarList1, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer2", dataContainer.compareBarList2, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer3", dataContainer.compareBarList3, Calculators.INTEGER),
				new MetaData<Integer>("Line Number1", dataContainer.scrollList1, Calculators.INTEGER),
				new MetaData<Integer>("Line Number2", dataContainer.scrollList2, Calculators.INTEGER),
				new MetaData<Integer>("Line Number3", dataContainer.scrollList3, Calculators.INTEGER)
				);
		chart.setTheme(new BlueTheme());
		charts.add(chart);
		
		
		chart = ValuedColorChartFactory.create( new MetaData<Integer>("Valued Color Drawer", dataContainer.valuedColorList, Calculators.INTEGER) );
		chart.setTheme(new BlueTheme());
		charts.add(chart);
		
		chart = StaticMinMaxChartFactory.create(
				new MetaData<Integer>("Zero Centered Data", dataContainer.zeroCenteredList1, Calculators.INTEGER)
				);
		chart.setTheme(new BlueTheme());
		charts.add(chart);
		
		chart = CompareScrollChartFactory.create(
				new MetaData<Integer>("Compare Drawer1", dataContainer.compareBarList1, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer2", dataContainer.compareBarList2, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer3", dataContainer.compareBarList3, Calculators.INTEGER));
		chart.setTheme(new BlueTheme());
		charts.add(chart);
		
		chart = ComplexChartFactory.create(
				new MetaData<Integer>("Top Boundary", dataContainer.envelopList1, Calculators.INTEGER),
				new MetaData<Integer>("BaseLine", dataContainer.envelopList2, Calculators.INTEGER),
				new MetaData<Integer>("Bottom Boundary", dataContainer.envelopList3, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer1", dataContainer.compareBarList1, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer2", dataContainer.compareBarList2, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer3", dataContainer.compareBarList3, Calculators.INTEGER),
				new MetaData<Integer>("Line Number1", dataContainer.scrollList1, Calculators.INTEGER),
				new MetaData<Integer>("Line Number2", dataContainer.scrollList2, Calculators.INTEGER),
				new MetaData<Integer>("Line Number3", dataContainer.scrollList3, Calculators.INTEGER)
				);
		chart.setTheme(new BlackTheme());
		charts.add(chart);
		
		
		chart = ValuedColorChartFactory.create( new MetaData<Integer>("Valued Color Drawer", dataContainer.valuedColorList, Calculators.INTEGER) );
		chart.setTheme(new BlackTheme());
		charts.add(chart);
		
		chart = StaticMinMaxChartFactory.create(
				new MetaData<Integer>("Zero Centered Data", dataContainer.zeroCenteredList1, Calculators.INTEGER)
				);
		chart.setTheme(new BlackTheme());
		charts.add(chart);
		
		chart = CompareScrollChartFactory.create(
				new MetaData<Integer>("Compare Drawer1", dataContainer.compareBarList1, Calculators.INTEGER),
//				new MetaData<Integer>("Compare Drawer2", dataContainer.compareBarList2, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer3", dataContainer.compareBarList3, Calculators.INTEGER));
		chart.setTheme(new BlackTheme());
		charts.add(chart);
		
		chart = ComplexChartFactory.create(
				new MetaData<Integer>("Top Boundary", dataContainer.envelopList1, Calculators.INTEGER),
				new MetaData<Integer>("BaseLine", dataContainer.envelopList2, Calculators.INTEGER),
				new MetaData<Integer>("Bottom Boundary", dataContainer.envelopList3, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer1", dataContainer.compareBarList1, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer2", dataContainer.compareBarList2, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer3", dataContainer.compareBarList3, Calculators.INTEGER),
				new MetaData<Integer>("Line Number1", dataContainer.scrollList1, Calculators.INTEGER),
				new MetaData<Integer>("Line Number2", dataContainer.scrollList2, Calculators.INTEGER),
				new MetaData<Integer>("Line Number3", dataContainer.scrollList3, Calculators.INTEGER)
				);
		chart.setTheme(new RedTheme());
		charts.add(chart);
		
		
		chart = ValuedColorChartFactory.create( new MetaData<Integer>("Valued Color Drawer", dataContainer.valuedColorList, Calculators.INTEGER) );
		chart.setTheme(new RedTheme());
		charts.add(chart);
		
		chart = StaticMinMaxChartFactory.create(
				new MetaData<Integer>("Zero Centered Data", dataContainer.zeroCenteredList1, Calculators.INTEGER)
				);
		chart.setTheme(new RedTheme());
		charts.add(chart);
		
		chart = CompareScrollChartFactory.create(
				new MetaData<Integer>("Compare Drawer1", dataContainer.compareBarList1, Calculators.INTEGER),
//				new MetaData<Integer>("Compare Drawer2", dataContainer.compareBarList2, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer3", dataContainer.compareBarList3, Calculators.INTEGER));
		chart.setTheme(new RedTheme());
		charts.add(chart);
		
		chart = ComplexChartFactory.create(
				new MetaData<Integer>("Top Boundary", dataContainer.envelopList1, Calculators.INTEGER),
				new MetaData<Integer>("BaseLine", dataContainer.envelopList2, Calculators.INTEGER),
				new MetaData<Integer>("Bottom Boundary", dataContainer.envelopList3, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer1", dataContainer.compareBarList1, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer2", dataContainer.compareBarList2, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer3", dataContainer.compareBarList3, Calculators.INTEGER),
				new MetaData<Integer>("Line Number1", dataContainer.scrollList1, Calculators.INTEGER),
				new MetaData<Integer>("Line Number2", dataContainer.scrollList2, Calculators.INTEGER),
				new MetaData<Integer>("Line Number3", dataContainer.scrollList3, Calculators.INTEGER)
				);
		chart.setTheme(new GoldTheme());
		charts.add(chart);
		
		
		chart = ValuedColorChartFactory.create( new MetaData<Integer>("Valued Color Drawer", dataContainer.valuedColorList, Calculators.INTEGER) );
		chart.setTheme(new GoldTheme());
		charts.add(chart);
		
		chart = StaticMinMaxChartFactory.create(
				new MetaData<Integer>("Zero Centered Data", dataContainer.zeroCenteredList1, Calculators.INTEGER)
				);
		chart.setTheme(new GoldTheme());
		charts.add(chart);
		
		chart = CompareScrollChartFactory.create(
				new MetaData<Integer>("Compare Drawer1", dataContainer.compareBarList1, Calculators.INTEGER),
//				new MetaData<Integer>("Compare Drawer2", dataContainer.compareBarList2, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer3", dataContainer.compareBarList3, Calculators.INTEGER));
		chart.setTheme(new GoldTheme());
		charts.add(chart);
		
		
		chart = ComplexChartFactory.create(
				new MetaData<Integer>("Top Boundary", dataContainer.envelopList1, Calculators.INTEGER),
				new MetaData<Integer>("BaseLine", dataContainer.envelopList2, Calculators.INTEGER),
				new MetaData<Integer>("Bottom Boundary", dataContainer.envelopList3, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer1", dataContainer.compareBarList1, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer2", dataContainer.compareBarList2, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer3", dataContainer.compareBarList3, Calculators.INTEGER),
				new MetaData<Integer>("Line Number1", dataContainer.scrollList1, Calculators.INTEGER),
				new MetaData<Integer>("Line Number2", dataContainer.scrollList2, Calculators.INTEGER),
				new MetaData<Integer>("Line Number3", dataContainer.scrollList3, Calculators.INTEGER)
				);
		chart.setTheme(new GreenTheme());
		charts.add(chart);
		
		
		chart = ValuedColorChartFactory.create( new MetaData<Integer>("Valued Color Drawer", dataContainer.valuedColorList, Calculators.INTEGER) );
		chart.setTheme(new GreenTheme());
		charts.add(chart);
		
		chart = StaticMinMaxChartFactory.create(
				new MetaData<Integer>("Zero Centered Data", dataContainer.zeroCenteredList1, Calculators.INTEGER)
				);
		chart.setTheme(new GreenTheme());
		charts.add(chart);
		
		chart = CompareScrollChartFactory.create(
				new MetaData<Integer>("Compare Drawer1", dataContainer.compareBarList1, Calculators.INTEGER),
//				new MetaData<Integer>("Compare Drawer2", dataContainer.compareBarList2, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer3", dataContainer.compareBarList3, Calculators.INTEGER));
		chart.setTheme(new GreenTheme());
		charts.add(chart);
		

		chart = CompareScrollChartFactory.create(
				new MetaData<Integer>("Compare Drawer1", dataContainer.compareBarList1, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer2", dataContainer.compareBarList2, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer3", dataContainer.compareBarList3, Calculators.INTEGER));
		chart.setTheme(new WhiteTheme());
		charts.add(chart);

		chart = ZeroCenteredCompareChartFactory.create(
				new MetaData<Integer>("Compare Drawer1", dataContainer.compareZeroCenteredBarList1, Calculators.INTEGER),
				new MetaData<Integer>("Compare Drawer2", dataContainer.compareZeroCenteredBarList2, Calculators.INTEGER));
		chart.setTheme(new WhiteTheme());
		charts.add(chart);
		
		chart = TextLabelChart.create( new MetaData<Integer>("Text Label Drawer", dataContainer.textLabelList, Calculators.INTEGER) );
		Theme theme = new WhiteTheme();
		chart.setTheme(theme);
		chart.settingControl.setBottomLabelInterval(1);
		charts.add(chart);

		chart = ScrollChartFactory.create( new MetaData<Integer>("Scroll Drawer", dataContainer.scrollList1, Calculators.INTEGER) );
		chart.setTheme(new WhiteTheme());
		charts.add(chart);
		
		chart = ScrollChartFactory.create(new MetaData<Integer>("Scroll Drawer", dataContainer.scrollList2, Calculators.INTEGER));
		chart.setTheme(new YellowTheme());
		charts.add(chart);
		
		chart = ScrollChartFactory.create(new MetaData<Integer>("Scroll Drawer", dataContainer.scrollList1, Calculators.INTEGER));
		chart.setTheme(new YellowTheme());
		charts.add(chart);

		chart = RainbowChartFactory.create(
				new MetaData<Integer>("Rainbow Drawer1", dataContainer.rainbowList, Calculators.INTEGER)
				);
		chart.setTheme(new YellowTheme());
		charts.add(chart);

		chart = RainbowChartFactory.create(
				new MetaData<Integer>("Rainbow Drawer1", dataContainer.rainbowList, Calculators.INTEGER)
				);
		chart.setTheme(new YellowTheme());
		charts.add(chart);

		chart = EnvelopScrollChartFactory.create(
				new MetaData<Integer>("Top Boundary", dataContainer.envelopList1, Calculators.INTEGER),
				new MetaData<Integer>("BaseLine", dataContainer.envelopList2, Calculators.INTEGER),
				new MetaData<Integer>("Bottom Boundary", dataContainer.envelopList3, Calculators.INTEGER)
				);
		chart.setTheme(new BlackTheme());
		charts.add(chart);

		chart = EnvelopScrollChartFactory.create(
				new MetaData<Integer>("Top Boundary", dataContainer.envelopList1, Calculators.INTEGER),
				new MetaData<Integer>("BaseLine", dataContainer.envelopList2, Calculators.INTEGER),
				new MetaData<Integer>("Bottom Boundary", dataContainer.envelopList3, Calculators.INTEGER)
				);
		chart.setTheme(new BlackTheme());
		charts.add(chart);

		chart = LineScrollChartFactory.create(
				new MetaData<Integer>("Line Number1", dataContainer.scrollList1, Calculators.INTEGER),
				new MetaData<Integer>("Line Number2", dataContainer.scrollList2, Calculators.INTEGER),
				new MetaData<Integer>("Line Number3", dataContainer.scrollList3, Calculators.INTEGER)
				);
		chart.setTheme(new BlackTheme());
		charts.add(chart);

		chart = ZeroCenteredChartFactory.create(
				new MetaData<Integer>("Zero Centered Data", dataContainer.zeroCenteredList1, Calculators.INTEGER)
				);
		chart.setTheme(new WhiteTheme());
		charts.add(chart);
				
		chart = ZeroCenteredChartFactory.create(
				new MetaData<Integer>("Zero Centered Data2", dataContainer.zeroCenteredList2, Calculators.INTEGER)
				);
		chart.setTheme(new BlackTheme());
		charts.add(chart);
		
		chart = ZeroCenteredChartFactory.create(
				new MetaData<Integer>("Zero Centered Data2", dataContainer.zeroCenteredList2, Calculators.INTEGER)
				);
		chart.setTheme(new YellowTheme());
		charts.add(chart);
				
		chart = FixedSizeChartFactory.create(
				new MetaData<Integer>("Fixed Size Data", dataContainer.fixedSizeList1, Calculators.INTEGER)
				);
		chart.setTheme(new WhiteTheme());
		charts.add(chart);
				
		chart = FixedSizeChartFactory.create(
				new MetaData<Integer>("Fixed Size Data", dataContainer.fixedSizeList2, Calculators.INTEGER)
				);
		chart.setTheme(new WhiteTheme());
		charts.add(chart);
				
		chart = FixedSizeChartFactory.create(
				new MetaData<Integer>("Fixed Size Data", dataContainer.fixedSizeList1, Calculators.INTEGER)
				);
		chart.setTheme(new WhiteTheme());
		charts.add(chart);
				
		chart = StaticMinMaxChartFactory.create(
				new MetaData<Integer>("Zero Centered Data", dataContainer.zeroCenteredList2, Calculators.INTEGER)
				);
		chart.setTheme(new WhiteTheme());
		charts.add(chart);
		
		chart = StaticMinMaxChartFactory.create(
				new MetaData<Integer>("Zero Centered Data", dataContainer.zeroCenteredList2, Calculators.INTEGER)
				);
		chart.setTheme(new YellowTheme());
		charts.add(chart);
		
		chart = ExternalValuedColorChartFactory.create(
				dataContainer.indexConditionList,
				new MetaData<Integer>("Fixed Size Data", dataContainer.fixedSizeList1, Calculators.INTEGER)
				);
		chart.setTheme(new BlackTheme());
		charts.add(chart);
		
		rearrange();

		ChartThread thread = new ChartThread(charts, dataContainer);
		thread.start();

		setVisible(true);
		
		addComponentListener(new ComponentListener() {
			@Override public void componentResized(ComponentEvent e) {
				setSize(getWidth(), getHeight());
			}
			@Override public void componentHidden(ComponentEvent arg0) {}
			@Override public void componentMoved(ComponentEvent arg0) {}
			@Override public void componentShown(ComponentEvent arg0) {}
		});
	}

	public void layout(JPanel panel, Component obj, int x, int y,int width, int height)
	{
		constraints.gridx=x; // 시작위치 x
		constraints.gridy=y; // 시작위치 y
		constraints.gridwidth=width; // 컨테이너 너비
		constraints.gridheight=height;  // 컨테이너 높이
		panel.add(obj, constraints);
	}
	
	@Override
	public void setSize(int width, int height) {				
		rearrange();
		panel.setSize(width, height);
		charts.forEach(item -> item.setSize(width / 2, (int)(width / (2 * GraphicProperty.chartWidthOverHeight())))); 
		super.setSize(width, height);
	}

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
	}
}

class ChartThread extends Thread{
	List<Chart> charts;
	DataContainer dataContainer;

	public ChartThread(List<Chart> charts, DataContainer dataContainer){
		this.charts = charts;
		this.dataContainer = dataContainer;
	}

	@Override
	public void run() {
		while(true){
			dataContainer.onUpdateData();
			charts.forEach(chart -> chart.repaint());
			try {
				sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}