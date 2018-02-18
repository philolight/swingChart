package main.component.chart;

import main.component.chart.control.area.AreaControl;
import main.component.chart.control.drawer.ZeroCenteredDrawerControl;
import main.component.chart.control.flow.FlowControl;
import main.component.chart.setting.SettingControl;

public class ZeroCenteredChart extends Chart{
	@Override
	protected void init(){
		settingControl = new SettingControl();
		flowControl = new FlowControl();
		areaControl = new AreaControl();
		drawerControl = new ZeroCenteredDrawerControl(flowControl);
		setDependencies();
	}
}
