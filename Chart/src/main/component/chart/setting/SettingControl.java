package main.component.chart.setting;

import main.component.chart.Side;

public class SettingControl{
	protected boolean isLeftLegend = true;
	protected boolean isRightLegend = false;
	protected boolean isBottomLegend = false;
	
	protected String title = "";
	protected int columns = 20;
	protected boolean isAutoScroll = false;		// 데이터 증가 시 자동 스크롤
	protected int bottomLabelInterval = 10;		// bottom Label의 간격

	protected boolean[] isStaticMin = new boolean[Side.size()];
	protected boolean[] isStaticMax = new boolean[Side.size()];
	protected double[] staticMinValue = new double[Side.size()];
	protected double[] staticMaxValue = new double[Side.size()];
	
	public int lineChartLineWidth = 1;
	public boolean lineChartIsDotOnTheLine = true; // line 위에 점을 그림
	
	public void enableStaticMin(Side side, double value){
		isStaticMin[side.index()] = true;
		staticMinValue[side.index()] = value;
	}
	
	public void disableStaticMin(Side side){
		isStaticMin[side.index()] = false;
	}
	
	public void enableStaticMax(Side side, double value){
		isStaticMax[side.index()] = true;
		staticMaxValue[side.index()] = value;
	}
	
	public void disableStaticMax(Side side){
		isStaticMax[side.index()] = false;
	}
	
	public boolean isStaticMin(Side side){ return isStaticMin[side.index()];}
	public boolean isStaticMax(Side side){ return isStaticMax[side.index()];}
	public double getStaticMinValue(Side side){ return staticMinValue[side.index()];}
	public double getStaticMaxValue(Side side){ return staticMaxValue[side.index()];}
	
	protected boolean isFixedSize = false; // 데이터 개수 고정
	
	public void setTitle(String name) { this.title = name; }

	public int getColumns() { return columns; }
	public void setColumns(int columns) { this.columns = columns; }
	
	public boolean isAutoScroll() { return isAutoScroll; }
	public void setAutoScroll(boolean isAutoScroll) { this.isAutoScroll = isAutoScroll; }	

	public boolean isLeftLegend() { return isLeftLegend; }
	public void setLeftLegend(boolean isLeftAxis) { this.isLeftLegend = isLeftAxis; }
	
	public boolean isRightLegend() { return isRightLegend; }
	public void setRightLegend(boolean isRightLegend) { this.isRightLegend = isRightLegend;}
	
	public boolean isBottomLegend() { return isBottomLegend; }
	public void setBottomLegend(boolean isBottomLegend) { this.isBottomLegend = isBottomLegend; }
	
	public boolean isFixedSize() { return isFixedSize; }
	public void setFixedSize(boolean isFixedSize) { this.isFixedSize = isFixedSize; }
	
	public String getTitle(){ return title; }
	
	public int getBottomLabelInterval() { return bottomLabelInterval; }
	public void setBottomLabelInterval(int bottomLabelInterval) { this.bottomLabelInterval = bottomLabelInterval; }
	
	public int getLineChartLineWidth() { return lineChartLineWidth; }
	public void setLineChartLineWidth(int lineChartLineWidth) { this.lineChartLineWidth = lineChartLineWidth; }
	
	public boolean getLineChartIsDotOnTheLine() { return lineChartIsDotOnTheLine; }
	public void setLineChartIsDotOnTheLine(boolean lineChartIsDotOnTheLine) { this.lineChartIsDotOnTheLine = lineChartIsDotOnTheLine; }
}
