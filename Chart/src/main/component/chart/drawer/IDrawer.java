package main.component.chart.drawer;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;

public interface IDrawer {
	public void setGraphics2D(Graphics2D g);
	public void draw();
	public void setTheme(Theme theme);
	public void setArea(Rectangle area);
	public void setSettingControl(SettingControl settingControl);	
	public void reset();	
	public void onUpdateData();
}
