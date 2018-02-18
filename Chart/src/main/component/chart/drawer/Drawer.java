package main.component.chart.drawer;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.component.chart.control.area.AreaControl;
import main.component.chart.control.flow.FlowControl;
import main.component.chart.control.minMax.MinMaxControl;
import main.component.chart.data.IMetaDataSet;
import main.component.chart.plotter.DataPlotter;
import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;

public abstract class Drawer implements IDrawer{
	protected Graphics2D g;
	public SettingControl settingControl;
	protected DataPlotter plotter = new DataPlotter();

	@Override public void setGraphics2D(Graphics2D g){ this.g = g; }
	
	public void setMinMaxControl(MinMaxControl minMaxControl) { plotter.setMinMaxControl(minMaxControl); }
	public void setFlowControl(FlowControl flow) { plotter.setFlowControl(flow); }
	public void setMetaData(IMetaDataSet metaDataSet){ plotter.setMetaData(metaDataSet); }
	abstract public void setAreaControl(AreaControl areaControl);
		
	@Override abstract public void draw();
	@Override abstract public void setTheme(Theme theme);
	@Override public void setArea(Rectangle area){ plotter.setArea(area); }
	@Override abstract public void setSettingControl(SettingControl settingControl);
	
	@Override public void reset(){
		plotter.reset();
	}
	
	@Override public void onUpdateData() {
		plotter.onUpdateData();
	}
}