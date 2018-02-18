package main.component.chart.drawer.data.bar;

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

public class BarDrawer extends DataDrawer{
	public BarDrawer(Side side) { super(side);	}

	int barWidth = 0;
	double barWidthRatio = 0.6;
	protected int barLineWidth = 1;
	protected Stroke stroke = DefaultStrokeFactory.create(barLineWidth);
	
	protected int barOverlapWidth = 2;
	
	protected IColorProvider colorProvider = IColorProvider.NULL;
	
	public void setColorConditioner(IColorProvider colorProvider) {
		this.colorProvider = colorProvider;
	}
	
	protected void calculateBarWidth(){
		barWidth = plotter.getPixelsBetweenData() < 3 ? 1 : (int)(plotter.getPixelsBetweenData() * barWidthRatio);
	}
	
	@Override
	protected void drawData(int index) {
		int x = plotter.getDataPositionX(index);
		double[] values = plotter.getData(index);
		int[] ySet = plotter.getDataPositionY(index);
		
		int xStart = x - barWidth / 2;
		int xWidth = barWidth / values.length + barOverlapWidth;
		for(int series = 0; series < values.length; series++){ // 계열별로 반복
			g.setPaint(colorProvider.getNormalColor(index, series, values[series]));
			g.fillRect(xStart + (xWidth - barOverlapWidth) * series, ySet[series], xWidth, plotter.height - ySet[series] + plotter.top);
			
			g.setPaint(colorProvider.getBrightColor(index, series, values[series]));
			g.setStroke(stroke);
			g.drawRect(xStart + (xWidth - barOverlapWidth) * series, ySet[series], xWidth, plotter.height - ySet[series] + plotter.top);
		}
	}
		
	@Override
	public void drawLegend(int series, Rectangle legendArea){
		g.setPaint(colorProvider.getNormalColor(index(0), series, Value.ignoredValue()));
		g.fillRect((int)legendArea.getX(), (int)legendArea.getY(), (int)legendArea.getWidth(), (int)legendArea.getHeight());
		
		g.setPaint(colorProvider.getBrightColor(index(0), series, Value.ignoredValue()));
		g.setStroke(stroke);
		g.drawRect((int)legendArea.getX(), (int)legendArea.getY(), (int)legendArea.getWidth(), (int)legendArea.getHeight());
	}
	
	@Override
	public void setTheme(Theme theme) {
		barLineWidth = theme.barChart.barLineWidth;
		stroke = DefaultStrokeFactory.create(barLineWidth);
		barWidthRatio = theme.barChart.barWidthRatio;
		calculateBarWidth();
	}

	@Override
	public void setAreaControl(AreaControl areaControl) {
		plotter.setArea(areaControl.getDataArea());
		setArea(areaControl.getDataArea());
		calculateBarWidth();
	}
	
	@Override
	public void setSettingControl(SettingControl settingControl) {
		this.settingControl = settingControl;
		plotter.setSetting(settingControl);
	}
}
