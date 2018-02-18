package main.component.chart;

public class GraphicProperty {
	public static double textHeightAdjustment(){ return 10.0/13.0;}	// g.getFontMetrics(font).getHeight()는 실제보다 더 크기때문에 조정이 필요함.
	public static int margin(){ return 5; }								// 일반적인 표시 마진
	public static double chartWidthOverHeight(){ return 8.0/4.0; }
}
