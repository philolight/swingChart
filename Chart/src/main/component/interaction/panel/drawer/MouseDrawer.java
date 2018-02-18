package main.component.interaction.panel.drawer;

import java.awt.Color;

import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;
import main.component.interaction.control.MouseControl;
import main.component.interaction.control.MouseState;

public class MouseDrawer extends InteractionDrawer{

	MouseControl mouseControl = null;
	
	public void setMouseControl(MouseControl mouseControl){
		this.mouseControl = mouseControl;
	}
	
	@Override
	public void draw() {
	
		MouseState mouseState = mouseControl.getState();
		
		mouseState.draw(g);
	}

	@Override
	public void setTheme(Theme theme) {
		// TODO Auto-generated method stub
		
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
