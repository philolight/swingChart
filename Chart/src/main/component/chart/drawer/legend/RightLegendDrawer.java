package main.component.chart.drawer.legend;

import main.component.chart.control.area.AreaControl;

public class RightLegendDrawer extends LegendDrawer{

	@Override
	public void setAreaControl(AreaControl areaControl) {
		plotter.setArea(areaControl.getRightLegendArea());
		this.setArea(areaControl.getRightLegendArea());
	}
}
