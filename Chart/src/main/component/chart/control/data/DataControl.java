package main.component.chart.control.data;

import main.component.chart.data.IMetaDataSet;
import main.component.chart.data.MetaDataSet;

public class DataControl {
	MetaDataSet totalMetaData = new MetaDataSet();
	MetaDataSet leftMetaData = new MetaDataSet();
	MetaDataSet rightMetaData = new MetaDataSet();
	
	public MetaDataSet getTotalMetaData() { return totalMetaData; }
	public MetaDataSet getLeftMetaData() { return leftMetaData; }
	public MetaDataSet getRightMetaData() { return rightMetaData; }
	
	public void addLeft(IMetaDataSet metaDataSet){ 
		totalMetaData.addMetaData(metaDataSet);
		leftMetaData.addMetaData(metaDataSet);
	}
	
	public void addRight(IMetaDataSet metaDataSet){
		totalMetaData.addMetaData(metaDataSet);
		rightMetaData.addMetaData(metaDataSet);
	}
	
	public void onUpdateData(){
		leftMetaData.onUpdateData();
		rightMetaData.onUpdateData();
		totalMetaData.onUpdateData();
	}
	public void reset() {
		leftMetaData.reset();
		rightMetaData.reset();
		totalMetaData.reset();
	}
}
