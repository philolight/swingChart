package main.component.chart.drawer.data.envelop;

import java.awt.Polygon;
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

public class EnvelopDrawer extends DataDrawer{
	public EnvelopDrawer(Side side) { super(side); }

	private int lineWidth = 1;
	private Stroke stroke = DefaultStrokeFactory.create(lineWidth);
	
	protected IColorProvider colorProvider = IColorProvider.NULL;
	
	Polygon topPolygon = new Polygon();
	Polygon bottomPolygon = new Polygon();
	
	public void setColorConditioner(IColorProvider colorProvider) {
		this.colorProvider = colorProvider;
	}
	
	public void draw(){
		g.setStroke(stroke);		
		topPolygon.reset();
		bottomPolygon.reset();

		for(int i = plotter.getLeftIndex(); i <= plotter.getRightIndex(); i++){
			int x = plotter.getDataPositionX(i);
			int[] y = plotter.getDataPositionY(i);
			topPolygon.addPoint(x, y[1]);
			bottomPolygon.addPoint(x, y[1]);
		}
		
		for(int i = plotter.getRightIndex(); i >= plotter.getLeftIndex(); i--){
			int x = plotter.getDataPositionX(i);
			int[] y = plotter.getDataPositionY(i);
			topPolygon.addPoint(x, y[0]);
			bottomPolygon.addPoint(x, y[2]);
		}
		
		g.setPaint(colorProvider.getTransparentColor(index(0), series(0), Value.ignoredValue()));
		g.fillPolygon(topPolygon);
		
		g.setPaint(colorProvider.getTransparentColor(index(0), series(2), Value.ignoredValue()));
		g.fillPolygon(bottomPolygon);
		
		for(int i = plotter.getLeftIndex(); i <= plotter.getRightIndex(); i++){
			drawData(i);
		}
	}
		
	@Override
	protected void drawData(int index) {
		int x1 = plotter.getDataPositionX(index - 1);
		int x2 = plotter.getDataPositionX(index);
		int[] y1 = plotter.getDataPositionY(index - 1);
		y1 = y1.clone();	// TODO : 추후에 최적화 할 것
		int[] y2 = plotter.getDataPositionY(index);

		for(int series = 0; series < y1.length; series++){
			if(series == series(1))
				g.setPaint(colorProvider.getBrightColor(index, series, Value.ignoredValue()));
			else{
				g.setPaint(colorProvider.getNormalColor(index, series, Value.ignoredValue()));
			}
				
			g.setStroke(stroke);
			g.drawLine(x1, y1[series], x2, y2[series]);
		}
	}
	
	@Override
	public void drawLegend(int series, Rectangle legendArea){
		if(series == 1){
			g.setStroke(stroke);
			g.setPaint(colorProvider.getNormalColor(index(0), series, Value.ignoredValue()));		
			g.drawLine(
					(int)legendArea.getX(), 
					(int)legendArea.getY(), 
					(int)(legendArea.getX() + legendArea.getWidth()),
					(int)(legendArea.getY() + legendArea.getHeight())
					);
		}
		else{
			g.setPaint(colorProvider.getNormalColor(index(0), series, Value.ignoredValue()));
			g.fillRect((int)legendArea.getX(), (int)legendArea.getY(), (int)legendArea.getWidth(), (int)legendArea.getHeight());

			g.setStroke(stroke);
			g.setPaint(colorProvider.getBrightColor(index(0), series, Value.ignoredValue()));
			g.drawRect((int)legendArea.getX(), (int)legendArea.getY(), (int)legendArea.getWidth(), (int)legendArea.getHeight());
		}
	}
		
	@Override
	public void setTheme(Theme theme) {
		lineWidth = theme.envelopChart.lineWidth;
		stroke = theme.envelopChart.stroke;
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
