package main.component.chart.drawer.axis;

import java.util.Map;

import main.component.chart.control.area.AreaControl;
import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;

public class RightAxisDrawer extends AxisDrawer{
	public String valueStringFormat = "%.2f";
	
	@Override
	public void draw() {
		drawVerticalLine();
		drawHorizontalMark();
	}
		
	private void drawVerticalLine(){
		g.setStroke(stroke);
		g.setPaint(lineColor);
		g.drawLine(plotter.left, plotter.top, plotter.left, plotter.top + plotter.height);		
	}
	
	private void drawHorizontalMark(){
		Map<Integer, Double> marks = plotter.getMarks();
		
		for(Integer key : marks.keySet()){
			g.setStroke(stroke);
			g.setPaint(lineColor);
			
			g.drawLine(plotter.left, key, plotter.left + markLineLength, key);
			
			g.setStroke(stroke);
			g.setPaint(fontColor);
			g.setFont(font);
			String str = String.format(valueStringFormat, marks.get(key));
			g.drawString(str, plotter.left + 5, key);
		}
	}

	@Override
	public void setTheme(Theme theme) {
		lineColor = theme.rightAxis.lineColor;
		setLineWidth(theme.rightAxis.lineWidth);
		fontColor = theme.rightAxis.fontColor;
		font = theme.rightAxis.font;
		setFont(theme.rightAxis.font);
		valueStringFormat = theme.rightAxis.valueStringFormat;
	}
	
	@Override
	public void setAreaControl(AreaControl areaControl) {
		plotter.setArea(areaControl.getRightAxisArea());
		super.setArea(areaControl.getRightAxisArea());
	}
	
	@Override
	public void setSettingControl(SettingControl settingControl) {
		this.settingControl = settingControl;
		plotter.setSetting(settingControl);
	}
}
