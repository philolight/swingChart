package main.component.chart.drawer.axis;

import main.component.chart.control.area.AreaControl;
import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;

public class NullAxisDrawer extends AxisDrawer{
	@Override public void setTheme(Theme theme) {}
	@Override public void setAreaControl(AreaControl areaControl) {}
	@Override public void setSettingControl(SettingControl settingControl) {}
	@Override public void draw() {}
}
