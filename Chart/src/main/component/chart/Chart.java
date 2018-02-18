package main.component.chart;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import main.component.chart.control.area.AreaControl;
import main.component.chart.control.drawer.DrawerControl;
import main.component.chart.control.flow.FlowControl;
import main.component.chart.setting.ChartSetting;
import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;

public class Chart extends JPanel{	
	public DrawerControl drawerControl = null;
	public SettingControl settingControl = null;
	public FlowControl flowControl = null;
	public AreaControl areaControl = null;
	protected Theme theme;
	
	public void setDrawerControl(DrawerControl drawerControl) { this.drawerControl = drawerControl; }
	public void setSettingControl(SettingControl settingControl) { this.settingControl = settingControl; }
	public void setFlowControl(FlowControl flowControl) { this.flowControl = flowControl; }
	public void setAreaControl(AreaControl areaControl) { this.areaControl = areaControl; }
		
	public void setTheme(Theme theme) {
		this.theme = theme;
		drawerControl.setTheme(theme);
	}

	public DrawerControl getDrawerControl() { return drawerControl; }

	public Chart(){
		init();
	}
	
	protected  void init(){
		settingControl = new SettingControl();
		flowControl = new FlowControl();
		areaControl = new AreaControl();
		drawerControl = new DrawerControl(flowControl);
		setDependencies();
	}
	
	public void reset(){
		flowControl.reset();
		drawerControl.reset();
	}
	
	protected void setDependencies(){
		areaControl.setDrawerControl(drawerControl);
		
		flowControl.setSettingControl(settingControl);

		drawerControl.setSettingControl(settingControl);
		drawerControl.setAreaControl(areaControl);
		drawerControl.setFlowControl(flowControl);
	}
	
	@Override
	public void setSize(int width, int height) {
		// width에 height의 비율을 맞춘다. 8:5
		Rectangle rectangle = new Rectangle();
		rectangle.setBounds(0, 0, width, height);
		areaControl.setArea(rectangle);
		drawerControl.setAreaControl(areaControl);
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	@Override
	public void paint(Graphics g) {
		flowControl.onUpdateData();
		drawerControl.onUpdateData();
		drawerControl.drawAll(g);
	}
}