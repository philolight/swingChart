package main.component.chart.control.drawer;

import main.component.chart.Side;
import main.component.chart.control.flow.FlowControl;
import main.component.chart.control.minMax.ZeroCenteredMinMaxControl;

public class ZeroCenteredDrawerControl extends DrawerControl{
	
	public ZeroCenteredDrawerControl(FlowControl flowControl) {
		super(flowControl);
	}
	
	@Override
	protected void newMinMaxControl(){
		totalMinMaxControl = new ZeroCenteredMinMaxControl(Side.CENTER);
		leftMinMaxControl = new ZeroCenteredMinMaxControl(Side.LEFT);
		rightMinMaxControl = new ZeroCenteredMinMaxControl(Side.RIGHT);
	}
}
