package main.component.chart.drawer.axis;

import main.component.chart.control.area.AreaControl;
import main.component.chart.drawer.axis.bottomAxisLabelGenerator.ColumnBottomAxisLabelGenerator;
import main.component.chart.drawer.axis.bottomAxisLabelGenerator.IBottomAxisLabelGenerator;
import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;

public class BottomAxisDrawer extends AxisDrawer{	
	public int labelInterval = 10;
	private double labelAngle = 0.0;
		
	IBottomAxisLabelGenerator bottomAxisLabelGenerator = new ColumnBottomAxisLabelGenerator();
	
	public void setLabelAngle(double labelAngle){this.labelAngle = labelAngle;}
	
	public void setBottomAxisLabelGenerator(IBottomAxisLabelGenerator bottomAxisLabelGenerator){
		this.bottomAxisLabelGenerator = bottomAxisLabelGenerator;
	}
	
	@Override
	public void draw() {	
		g.setStroke(stroke);
		g.setPaint(lineColor);
		g.drawLine(plotter.left, plotter.top, plotter.right, plotter.top);
				
		for(int index = plotter.getLeftIndex(); index <= plotter.getRightIndex(); index++){
			if(index % labelInterval == 0){
				g.setColor(lineColor);
				g.drawLine(plotter.getDataPositionX(index), plotter.top, plotter.getDataPositionX(index), plotter.top + markLineLength);
				
				drawString(index);
			}
		}
	}
	
	private void drawString(int index){		
		g.setColor(fontColor);
		g.setFont(font);
		String str = bottomAxisLabelGenerator.getLabel(index);
		int stringWidth = g.getFontMetrics(font).stringWidth(str);
		int x = plotter.getDataPositionX(index);
		
		if(labelAngle == 0.0) 	drawNormalString(str, x, stringWidth);
		else 					drawRotatedString(str, x, stringWidth);
	}
	
	private void drawNormalString(String str, int x, int stringWidth){
		g.drawString(str, x - stringWidth / 2, plotter.top + textHeight);
	}

	/** String을 회전시켜 표시하는 함수 */
	private void drawRotatedString(String str, int x, int stringWidth){
		g.rotate(labelAngle, x, plotter.centerY );
		g.drawString(str, x - stringWidth / 2, plotter.centerY);
		g.rotate(-labelAngle, x, plotter.centerY );
	}

	@Override
	public void setTheme(Theme theme) {
		lineColor = theme.bottomAxis.lineColor;
		setLineWidth(theme.bottomAxis.lineWidth);
		fontColor = theme.bottomAxis.fontColor;	
		setFont(theme.bottomAxis.font);
		labelAngle = theme.bottomAxis.labelAngle;
	}

	@Override
	public void setAreaControl(AreaControl areaControl) {
		plotter.setArea(areaControl.getBottomAxisArea());
		super.setArea(areaControl.getBottomAxisArea());
	}
	
	@Override
	public void setSettingControl(SettingControl settingControl) {
		this.settingControl = settingControl;
		plotter.setSetting(settingControl);
		
		labelInterval = settingControl.getBottomLabelInterval();
	}
}
