package main.component.chart.drawer.background;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Rectangle;

import main.component.chart.control.area.AreaControl;
import main.component.chart.drawer.Drawer;
import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;
import main.component.chart.theme.gradient.TwoToneGradientFactory;

public class DataBackgroundDrawer extends Drawer{
	Graphics2D g;
	public void setGraphics2D(Graphics2D g2d){ g = g2d; }
	
	Color backgroundColor = Color.BLACK;
	Color stripColor = new Color(255-223, 255-223, 255-223);
	Color gradationColor = new Color(255-128, 255-128, 255-64);
	LinearGradientPaint gradient;
	
	Color grad[];
	
	public void draw(){
		int stripInterval = settingControl.getColumns()/8;
		g.setPaint(backgroundColor);
		g.fillRect(plotter.left, plotter.top, plotter.width+1, plotter.height);
		
		g.setPaint(stripColor);		
		for(int i = plotter.getLeftIndex(); i < plotter.getRightIndex(); i++){
			int x = (int)(plotter.getDataPositionX(i));

			if( (i / stripInterval) % 2 == 0){
				g.fillRect(x, plotter.top, (int)plotter.getPixelsBetweenData() + 1, plotter.height);
			}
		}
		
		g.setPaint(gradient);
		g.fillRect(plotter.left, plotter.top, plotter.width, plotter.height);
	}

	@Override
	public void setTheme(Theme theme) {
		backgroundColor = theme.dataBackground.backgroundColor;
		stripColor = theme.dataBackground.stripColor;
		gradationColor = theme.dataBackground.gradationColor;
		gradient = newGradient();
	}
	
	@Override
	public void setAreaControl(AreaControl areaControl) {
		plotter.setArea(areaControl.getDataArea());
		setArea(areaControl.getDataArea());
		gradient = newGradient();
	}
	
	public LinearGradientPaint newGradient(){
		return TwoToneGradientFactory.create(
				new Color(gradationColor.getRed(), gradationColor.getGreen(), gradationColor.getBlue(), 0), 
				new Color(gradationColor.getRed(), gradationColor.getGreen(), gradationColor.getBlue(), 128), 
				plotter.top, 
				plotter.height);
	}
	
	@Override
	public void setSettingControl(SettingControl settingControl) {
		this.settingControl = settingControl;
		plotter.setSetting(settingControl);
	}
}
