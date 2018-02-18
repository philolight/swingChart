package main.component.chart.drawer.background;

import java.awt.Color;
import java.awt.LinearGradientPaint;

import main.component.chart.control.area.AreaControl;
import main.component.chart.drawer.Drawer;
import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;
import main.component.chart.theme.gradient.TwoToneGradientFactory;

public class BackgroundDrawer extends Drawer{
	public Color backgroundStartColor = Color.WHITE;
	public Color backgroundEndColor = Color.BLACK;
	LinearGradientPaint gradient; 
	
	@Override
	public void draw() {
		g.setPaint(gradient);
		g.fillRect(plotter.left, plotter.top, plotter.width, plotter.height);
	}

	@Override
	public void setTheme(Theme theme) {
		backgroundStartColor = theme.background.backgroundStartColor;
		backgroundEndColor = theme.background.backgroundEndColor;
		gradient = TwoToneGradientFactory.create(backgroundStartColor, 
				backgroundEndColor, plotter.top, plotter.height);
	}

	@Override
	public void setAreaControl(AreaControl areaControl) {
		plotter.setArea(areaControl.getBackgroundArea());
		setArea(areaControl.getBackgroundArea());
		gradient = TwoToneGradientFactory.create(backgroundStartColor, 
				backgroundEndColor, plotter.top, plotter.height);
	}
	
	@Override
	public void setSettingControl(SettingControl settingControl) {
		this.settingControl = settingControl;
		plotter.setSetting(settingControl);
	}
}
