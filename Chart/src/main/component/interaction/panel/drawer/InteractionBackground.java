package main.component.interaction.panel.drawer;

import java.awt.Color;
import java.awt.LinearGradientPaint;

import main.component.chart.control.area.AreaControl;
import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;
import main.component.chart.theme.gradient.TwoToneGradientFactory;

public class InteractionBackground extends InteractionDrawer{
	public Color backgroundStartColor = Color.WHITE;
	public Color backgroundEndColor = Color.BLACK;
	LinearGradientPaint gradient; 
	
	@Override
	public void draw() {
		g.setPaint(gradient);
		g.fillRect(area.x, area.y, area.width, area.height);
	}

	@Override
	public void setTheme(Theme theme) {
		backgroundStartColor = theme.background.backgroundStartColor;
		backgroundEndColor = theme.background.backgroundEndColor;
		gradient = TwoToneGradientFactory.create(backgroundStartColor, 
				backgroundEndColor, area.x, area.height);
	}

	@Override
	public void setSettingControl(SettingControl settingControl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdateData() {
		// TODO Auto-generated method stub
		
	}
}
