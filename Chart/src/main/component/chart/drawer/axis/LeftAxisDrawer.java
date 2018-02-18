package main.component.chart.drawer.axis;

import java.util.Map;

import main.component.chart.control.area.AreaControl;
import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;

public class LeftAxisDrawer extends AxisDrawer{
	public String valueStringFormat = "%.2f";
	
	@Override
	public void draw() {
		drawVerticalLine();
		drawHorizontalMark();
	}
		
	private void drawVerticalLine(){
		g.setStroke(stroke);
		g.setPaint(lineColor);
		g.drawLine(plotter.right, plotter.top, plotter.right, plotter.top + plotter.height);		
	}
	
	private void drawHorizontalMark(){
		Map<Integer, Double> marks = plotter.getMarks();
		
		for(Integer key : marks.keySet()){
			g.setStroke(stroke);
			g.setPaint(lineColor);
			
			g.drawLine(plotter.right, key, plotter.right - markLineLength, key);
			
			g.setStroke(stroke);
			g.setPaint(fontColor);
			g.setFont(font);
			String str = String.format(valueStringFormat, marks.get(key));
			g.drawString(str, plotter.right - markLineLength - 2 - g.getFontMetrics().stringWidth(str), key);
		}
	}

	@Override
	public void setTheme(Theme theme) {
		lineColor = theme.leftAxis.lineColor;
		setLineWidth(theme.leftAxis.lineWidth);
		fontColor = theme.leftAxis.fontColor;
		setFont(theme.leftAxis.font);
		valueStringFormat = theme.leftAxis.valueStringFormat;	
	}

	@Override
	public void setAreaControl(AreaControl areaControl) {
		plotter.setArea(areaControl.getLeftAxisArea());
		super.setArea(areaControl.getLeftAxisArea());
	}
	
	@Override
	public void setSettingControl(SettingControl settingControl) {
		this.settingControl = settingControl;
		plotter.setSetting(settingControl);
	}
}
