package main.component.chart.drawer.data.label;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import main.component.chart.GraphicProperty;
import main.component.chart.Side;
import main.component.chart.control.area.AreaControl;
import main.component.chart.control.minMax.MinMaxControl;
import main.component.chart.drawer.data.DataDrawer;
import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;
import main.component.common.VerticalPosition;

public class LabelDrawer extends DataDrawer {
	public LabelDrawer(Side side) { super(side); }

	protected VerticalPosition positionType = VerticalPosition.CENTER;
	protected Color fontColor = Color.BLACK;
	protected Font font = new Font("돋움", Font.PLAIN, 9);
	protected String valueStringFormat = "%.2f";	
	protected int textHeight = 9;
	double barWidthRatio;
	
	@Override public void setGraphics2D(Graphics2D g){
		super.setGraphics2D(g);
		textHeight = (int)(g.getFontMetrics(font).getHeight() * GraphicProperty.textHeightAdjustment());		
	}
	
	public void setFont(Font font){
		this.font = font;
		textHeight = (int)(g.getFontMetrics(font).getHeight() * GraphicProperty.textHeightAdjustment());
	}
				
	@Override
	protected void drawData(int index) {
		String[] stringSet = plotter.getDataString(index, valueStringFormat);
		int[] ySet = plotter.getDataPositionY(index);
		double[] values = plotter.getData(index);
		
		g.setPaint(fontColor);
		g.setFont(font);
		for(int i = 0; i < stringSet.length; i++){
			calculateLabelPointX(index, i, stringSet.length, stringSet[i]);
			calculateLabelPointY(ySet[i], values[i]);

			g.drawString(stringSet[i], labelPoint.x, labelPoint.y);
		}
	}
	
	protected Point labelPoint = new Point();
	protected void calculateLabelPointX(int index, int order, int length, String text){
		int x = plotter.getDataPositionX(index);
		int xStart = x - (int)(plotter.getPixelsBetweenData() * barWidthRatio / 2.0);
		int xWidth = (int)(plotter.getPixelsBetweenData() * barWidthRatio / length) + 2;
		
		labelPoint.x = xStart + xWidth / 2 + xWidth * order - order * 2 - g.getFontMetrics().stringWidth(text) / 2;
	}
	
	protected void calculateLabelPointY(int y, double value){		
		switch(positionType){
		case TOP:
			labelPoint.y = y;
			labelPoint.y = labelPoint.y < plotter.top + textHeight ? plotter.top + textHeight : labelPoint.y;
			labelPoint.y = labelPoint.y > plotter.bottom ? plotter.bottom : labelPoint.y;
		break;
		case CENTER:
			labelPoint.y = (y + plotter.bottom + textHeight) / 2 ;
			labelPoint.y = labelPoint.y > plotter.bottom ? plotter.bottom : labelPoint.y;
			break;
		case BOTTOM:
			labelPoint.y = plotter.bottom;
			break;
		default:
			break;
		}
	}
	
	@Override public void drawLegend(int index, Rectangle legendArea){}
	
	@Override public void drawLegendText(int index, Rectangle area){}
	
	@Override public void setTheme(Theme theme) {
		positionType = theme.labelChart.positionType;
		fontColor = theme.labelChart.fontColor;
		setFont(theme.labelChart.font);
		valueStringFormat = theme.labelChart.valueStringFormat;
		barWidthRatio = theme.barChart.barWidthRatio;
	}
	
	@Override
	public void setAreaControl(AreaControl areaControl) {
		plotter.setArea(areaControl.getDataArea());
		setArea(areaControl.getDataArea());
	}
	
	@Override
	public void setSettingControl(SettingControl settingControl) {
		this.settingControl = settingControl;
		plotter.setSetting(settingControl);
	}
}
