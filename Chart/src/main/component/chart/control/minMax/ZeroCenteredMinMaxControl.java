package main.component.chart.control.minMax;

import main.component.chart.Side;

public class ZeroCenteredMinMaxControl extends MinMaxControl{
	public ZeroCenteredMinMaxControl(Side side) {
		super(side);
		markCount = 7;
		marks = new double[markCount];
	}

	@Override
	protected void updateMinMax(){
		double minBefore = min;	
		min = updateMin();
		
		double maxBefore = max;
		max = updateMax();
		
		makeMinMaxZeroCentered();
		
		if(minBefore != min || maxBefore != max) calculateMarks();
	}
	
	private void makeMinMaxZeroCentered(){
		double biggestAbsolute = Math.abs(min) > Math.abs(max)? Math.abs(min) : Math.abs(max);
		
		min = -biggestAbsolute;
		max = biggestAbsolute;
	}
	
	@Override
	protected void calculateMarks() {
		marks = new double [7];
		marks[0] = min;
		marks[1] = min + (double)1/3 * max;
		marks[2] = min + (double)2/3 * max;
		marks[3] = 0.0;
		marks[4] = (double)1/3 * max;
		marks[5] = (double)2/3 * max;
		marks[6] = max;
	}
}
