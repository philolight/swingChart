package main.component.chart.drawer.data.line;

import main.component.chart.Side;
import main.component.chart.control.minMax.ZeroCenteredMinMaxControl;

public class ZeroCenteredLineDrawer extends LineDrawer{
	public ZeroCenteredLineDrawer(Side side) { super(side); }

	@Override
	protected void setMinMaxControl(Side side){
		plotter.setMinMaxControl(new ZeroCenteredMinMaxControl(side));
	}
}
