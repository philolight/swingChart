package main.component.chart.drawer.data.line;

import java.awt.Rectangle;
import java.awt.Stroke;

import main.component.chart.Side;
import main.component.chart.Value;
import main.component.chart.color.IColorProvider;
import main.component.chart.control.area.AreaControl;
import main.component.chart.drawer.data.DataDrawer;
import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;
import main.component.chart.theme.stroke.DefaultStrokeFactory;

public class LineDrawer extends DataDrawer{
	public LineDrawer(Side side) { super(side); }

	private int lineWidth = 2;
	private Stroke stroke = DefaultStrokeFactory.create(lineWidth);
	private boolean isDotOnTheLine = false; // line 위에 점을 그림
	
	protected IColorProvider colorProvider = IColorProvider.NULL;
	
	public void setColorConditioner(IColorProvider colorProvider) {
		this.colorProvider = colorProvider;
	}
	
	public void setLineWidth(int lineWidth){
		this.lineWidth = lineWidth;
		stroke = DefaultStrokeFactory.create(lineWidth);
	}
	
	@Override
	public void draw(){
		for(int i = plotter.getLeftIndex(); i <= plotter.getRightIndex(); i++){
			drawData(i);
		}
		
		if(isDotOnTheLine == false) return;
		
		for(int i = plotter.getLeftIndex(); i <= plotter.getRightIndex(); i++){
			drawDot(i);
		}
	}
			
	@Override
	protected void drawData(int index) {
		int x1 = plotter.getDataPositionX(index - 1);
		int x2 = plotter.getDataPositionX(index);
		int[] y1 = plotter.getDataPositionY(index - 1);
		y1 = y1.clone();	// TODO : 추후에 최적화 할 것
		int[] y2 = plotter.getDataPositionY(index);

		g.setStroke(stroke);
		
		for(int series = 0; series < y1.length; series++){
			g.setPaint(colorProvider.getNormalColor(index, series, Value.ignoredValue()));
			g.drawLine(x1, y1[series], x2, y2[series]);
		}
	}
	
	private void drawDot(int index) {
		int x = plotter.getDataPositionX(index);
		int[] y = plotter.getDataPositionY(index);

		int dotSize = lineWidth + 2;
		
		g.setStroke(stroke);
		for(int i = 0; i < y.length; i++){			
			g.setPaint(colorProvider.getBrightColor(index, i, Value.ignoredValue()));
			g.fillArc(x-lineWidth, y[i]-lineWidth, dotSize, dotSize, 0, 360);
		}
	}
	
	@Override
	public void drawLegend(int series, Rectangle legendArea){
		g.setStroke(stroke);
		g.setPaint(colorProvider.getNormalColor(index(0), series, Value.ignoredValue()));
		
		g.drawLine(
				(int)legendArea.getX(), 
				(int)legendArea.getY(), 
				(int)(legendArea.getX() + legendArea.getWidth()),
				(int)(legendArea.getY() + legendArea.getHeight())
				);
		
		if(isDotOnTheLine){
			g.fillArc(legendArea.x + legendArea.width/2 - 2, legendArea.y + legendArea.height/2 - 2, 5, 5, 0, 360);
		}
	}
	
	@Override
	public void setTheme(Theme theme) {
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
		
		setLineWidth(settingControl.getLineChartLineWidth());
		isDotOnTheLine = settingControl.getLineChartIsDotOnTheLine();
	}
}
