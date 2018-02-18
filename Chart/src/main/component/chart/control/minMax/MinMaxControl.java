package main.component.chart.control.minMax;

import main.component.chart.Side;
import main.component.chart.control.flow.FlowControl;
import main.component.chart.data.IMetaDataSet;
import main.component.chart.setting.SettingControl;

public class MinMaxControl{
	public double min = Double.POSITIVE_INFINITY;
	public double max = Double.NEGATIVE_INFINITY;
	public int markCount = 6;
	public double []marks = new double[markCount];
	
	SettingControl settingControl;
	FlowControl flowControl;
	
	Side side = Side.CENTER;
	
	public IMetaDataSet metaDataSet = IMetaDataSet.NULL;
 	
	public MinMaxControl(Side side){ this.side = side; }
		
	public void onUpdateData(){
		updateMinMax();
	}
	
	protected void updateMinMax(){
		double minBefore = min;
		min = updateMin();
		
		double maxBefore = max;
		max = updateMax();
		
		if(minBefore != min || maxBefore != max) calculateMarks();
	}
	
	protected double updateMin(){
		double result = min;
		if(settingControl.isStaticMin(side)) return settingControl.getStaticMinValue(side);
		
		if(settingControl.isAutoScroll())
			result = metaDataSet.getGlobalMin();
		else
			result = metaDataSet.getMinBetween(flowControl.getLeftIndex(), flowControl.getRightIndex());

		return result;
	}
	
	protected double updateMax(){
		double result = max;
		if(settingControl.isStaticMax(side)) return settingControl.getStaticMaxValue(side);

		if(settingControl.isAutoScroll() == true)
			result = metaDataSet.getGlobalMax();
		else
			result = metaDataSet.getMaxBetween(flowControl.getLeftIndex(), flowControl.getRightIndex());
		return result;
	}

	public double[] getMarks() {
		return marks;
	}
	
	protected void calculateMarks() {
		double difference = max - min;
		marks[0] = min;
		marks[1] = min + (double)1/5 * difference;
		marks[2] = min + (double)2/5 * difference;
		marks[3] = min + (double)3/5 * difference;
		marks[4] = min + (double)4/5 * difference;
		marks[5] = max;
	}
	
	public void reset() {
		min = Double.POSITIVE_INFINITY;
		max = Double.NEGATIVE_INFINITY;
		for(int i = 0; i < marks.length; i++) marks[i] = 0.0;
	}
	
	public void setFlowControl(FlowControl flowControl) { this.flowControl = flowControl; }
	public void setSettingControl(SettingControl settingControl) { this.settingControl = settingControl; }
	public void setMetaData(IMetaDataSet metaDataSet){ this.metaDataSet = metaDataSet; }
}
