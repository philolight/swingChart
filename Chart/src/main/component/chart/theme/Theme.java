package main.component.chart.theme;

import java.awt.Color;

import main.component.chart.Chart;

public class Theme{	
	int barColorCount = 0;
	private Color[] barFillColors = {
		new Color(0x33, 0x33, 0x33), new Color(0x00, 0xCC, 0x00), new Color(0x00, 0x00, 0xCC), 
		new Color(0xCC, 0xCC, 0x00), new Color(0x00, 0xCC, 0xCC), new Color(0xCC, 0x00, 0xCC),
		new Color(0x80, 0x00, 0x00), new Color(0x00, 0x80, 0x00), new Color(0x00, 0x00, 0x80), 
		new Color(0x80, 0x80, 0x00), new Color(0x00, 0x80, 0x80), new Color(0x80, 0x00, 0x80)
	};
	
	private Color[] barLineColors = {
			new Color(0x99, 0x99, 0x99), new Color(0x33, 0xFF, 0x33), new Color(0x33, 0x33, 0xFF), 
			new Color(0xFF, 0xFF, 0x33), new Color(0x33, 0xFF, 0xFF), new Color(0xFF, 0x33, 0xFF),
			new Color(0x80, 0x33, 0x33), new Color(0x33, 0x80, 0x33), new Color(0x33, 0x33, 0x80), 
			new Color(0x80, 0x80, 0x33), new Color(0x33, 0x80, 0x80), new Color(0x80, 0x33, 0x80)
	};
	
	int lineColorCount = 0;
	private Color[] lineColors = {
			new Color(0xFF, 0x33, 0x33), new Color(0x33, 0xFF, 0x33), new Color(0x33, 0x33, 0xFF), 
			new Color(0xFF, 0xFF, 0x33), new Color(0x33, 0xFF, 0xFF), new Color(0xFF, 0x33, 0xFF),
			new Color(0x80, 0x33, 0x33), new Color(0x33, 0x80, 0x33), new Color(0x33, 0x33, 0x80), 
			new Color(0x80, 0x80, 0x33), new Color(0x33, 0x80, 0x80), new Color(0x80, 0x33, 0x80)
	};
	
	public Color getLineColor(){
		return lineColors[lineColorCount++];
	}
	
	public Color getBarLineColor(){
		return barLineColors[barColorCount];
	}
	
	public Color getBarFillColor(){
		return barFillColors[barColorCount++];
	}
	
	public void setBackgroundColor(Color backgroundStartColor, Color backgroundEndColor) {
		background.backgroundStartColor = backgroundStartColor;
		background.backgroundEndColor = backgroundEndColor;
		title.backgroundStartColor = backgroundStartColor;
		footer.backgroundColor = backgroundStartColor;
	}

	public void setLineWidth(int lineWidth) {
		leftAxis.lineWidth = lineWidth;
		rightAxis.lineWidth = lineWidth;
		bottomAxis.lineWidth = lineWidth;
	}
	
	public void setLineColor(Color lineColor) {
		leftAxis.lineColor = lineColor;
		rightAxis.lineColor = lineColor;
		bottomAxis.lineColor = lineColor;
	}
	
	public void setFontColor(Color fontColor) {
		title.fontColor = fontColor;
		leftAxis.fontColor = fontColor;
		rightAxis.fontColor = fontColor;
		bottomAxis.fontColor = fontColor;
		legend.fontColor = fontColor;
		footer.fontColor = fontColor;
	}
	
	public void setValueStringFormat(String valueStringFormat){
		leftAxis.valueStringFormat = valueStringFormat;
		rightAxis.valueStringFormat = valueStringFormat;
	}

	public void setMainColors(Color[] mainColors) {
		this.barFillColors = mainColors;
	}

	public void setSubColors(Color[] subColors) {
		this.barLineColors = subColors;
	}
	
	public BackgroundTheme 		background = new BackgroundTheme();
	public DataBackgroundTheme 	dataBackground = new DataBackgroundTheme();

	public TitleTheme 			title = new TitleTheme();
	public LeftAxisTheme 		leftAxis = new LeftAxisTheme();
	public RightAxisTheme 		rightAxis = new RightAxisTheme();
	public BottomAxisTheme		bottomAxis = new BottomAxisTheme();
	public LegendTheme 			legend = new LegendTheme();
	public BarChartTheme 		barChart = new BarChartTheme();
	public LineChartTheme 		lineChart = new LineChartTheme();
	public EnvelopChartTheme	envelopChart = new EnvelopChartTheme();
	public LabelChartTheme 		labelChart = new LabelChartTheme();
	public FooterTheme 			footer = new FooterTheme();
	public MarkTheme 			mark = new MarkTheme();

}
