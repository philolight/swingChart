package main.component.chart.control.flow;

import main.component.chart.data.IMetaDataSet;
import main.component.chart.setting.SettingControl;

public class FlowControl{
	private int leftIndex = 0;
	private int rightIndex = 0;	
	public IMetaDataSet metaDataSet = IMetaDataSet.NULL;
	
	SettingControl settingControl;
	
	public int getLeftIndex() { return leftIndex; }
	public int getRightIndex() { return rightIndex; }
	public void setSettingControl(SettingControl settingControl) { this.settingControl = settingControl; }	
	public void setMetaData(IMetaDataSet metaDataSet){ this.metaDataSet = metaDataSet; }

	public void setLeftIndex(int leftIndex) {
		if(metaDataSet.size() <= leftIndex) return;
		
		this.leftIndex = leftIndex; 
		calculateRightIndex();
	}
				
	private void calculateLeftIndex(){
		if(!settingControl.isAutoScroll()) return;
		leftIndex = metaDataSet.size() > settingControl.getColumns() ? metaDataSet.size() - settingControl.getColumns() : 0 ;
	}
	
	private void calculateRightIndex(){
		rightIndex = (leftIndex + settingControl.getColumns() - 1);
		rightIndex = rightIndex >= metaDataSet.size() ? metaDataSet.size() - 1 : rightIndex;
	}
	
	public void reset(){
		leftIndex = rightIndex = 0;
	}
	
	public void onUpdateData(){
		calculateLeftIndex();
		calculateRightIndex();
	}
}
