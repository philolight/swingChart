package main.component.chart.drawer.legend;

import main.component.chart.control.area.AreaControl;

public class LeftLegendDrawer extends LegendDrawer{
	
	@Override
	public void setAreaControl(AreaControl areaControl) {
		plotter.setArea(areaControl.getLeftLegendArea());
		this.setArea(areaControl.getLeftLegendArea());
	}
}
