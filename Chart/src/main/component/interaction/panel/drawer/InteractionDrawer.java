package main.component.interaction.panel.drawer;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.component.chart.drawer.IDrawer;
import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;

abstract public class InteractionDrawer implements IDrawer{
	protected Graphics2D g;
	protected Rectangle area;
	
	@Override public void setGraphics2D(Graphics2D g){ this.g = g; }
	@Override public void setArea(Rectangle area){ this.area = area;}

	@Override abstract public void draw();
	@Override abstract public void setTheme(Theme theme);
	@Override abstract public void setSettingControl(SettingControl settingControl);	
	@Override abstract public void reset();
	@Override abstract public void onUpdateData();
}
