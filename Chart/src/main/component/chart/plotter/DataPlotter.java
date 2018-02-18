package main.component.chart.plotter;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import main.component.chart.control.area.AreaInfo;
import main.component.chart.control.flow.FlowControl;
import main.component.chart.control.minMax.MinMaxControl;
import main.component.chart.data.IMetaDataSet;
import main.component.chart.setting.SettingControl;

public class DataPlotter extends AreaInfo{
	protected SettingControl setting;
	protected MinMaxControl minMax;
	protected FlowControl flow;
	protected IMetaDataSet metaDataSet;
	protected double pixelsBetweenData;

	public void setSetting(SettingControl setting) {
		this.setting = setting;
		minMax.setSettingControl(setting);
	}
	
	public void setMinMaxControl(MinMaxControl minMaxControl) {
		this.minMax = minMaxControl;
		if(flow != null) minMax.setFlowControl(flow);
	}
	public void setFlowControl(FlowControl flow) { 
		this.flow = flow;
		if(minMax != null) minMax.setFlowControl(flow);
	}
	
	public void setMetaData(IMetaDataSet metaDataSet){
		ySet = new int[metaDataSet.getSetSize()];
		dataStringSet = new String[metaDataSet.getSetSize()];
		
		minMax.setMetaData(metaDataSet);
		flow.setMetaData(metaDataSet);
		this.metaDataSet = metaDataSet;
	}
	
	public void setColumns(int columns) {
		calculatePixelsBetweenData();
	}
	
	public void setArea(Rectangle area){
		super.setArea(area);
		calculatePixelsBetweenData();
	}
	
	public double getPixelsBetweenData() { return pixelsBetweenData; }	
	public int getLeftIndex(){ return flow.getLeftIndex(); }
	public int getRightIndex(){ return flow.getRightIndex(); }
			
	public int getDataPositionX(int i) {
		if(i < flow.getLeftIndex()) i = flow.getLeftIndex();
		return (int)(left + pixelsBetweenData / 2.0 + pixelsBetweenData * (i - flow.getLeftIndex()));
	}

	int[] ySet = {};
	public int[] getDataPositionY(int i) {
		if(i < flow.getLeftIndex()){
			i = flow.getLeftIndex();
		}
		
		double[] valueSet = metaDataSet.get(i);
		int index = 0;
		for(double value : valueSet){ 
			ySet[index++] = getDataY(value);
		}
		return ySet;
	}
	
	String[] dataStringSet = {};
	public String[] getDataString(int index, String valueStringFormat){
		double[] valueSet = metaDataSet.get(index);
		for(int i = 0; i < valueSet.length; i++){
			dataStringSet[i] = String.format(valueStringFormat, valueSet[i]);
		}
		return dataStringSet;
	}
	
	public Map<Integer, Double> getMarks() {
		
		double []values = minMax.getMarks();
		
		Map<Integer, Double> marks = new HashMap<Integer, Double>();
		
		for(double value : values){
			int key = getDataY(value);
			marks.put(key, value);
		}
		
		return marks;
	}
	
	public void reset(){
		metaDataSet.reset();
		minMax.reset();
	}
	
	public void onUpdateData(){
		metaDataSet.onUpdateData();
		minMax.onUpdateData();
	}
	
	protected void calculatePixelsBetweenData(){
		pixelsBetweenData = (double)(right - left) / (double)setting.getColumns();
	}
	
	protected int getDataY(double value){
		double rate = 1.0 - (value - minMax.min) / (minMax.max - minMax.min);
		return limitToHeightBoundary( (int)(top + rate * height) );
	}
	
	public double[] getData(int i){
		return metaDataSet.get(i);
	}
	
	protected int limitToHeightBoundary(int y){
		if(y < top) return top;
		if(y > top + height) return top + height;
		
		return y;
	}
	public String getName(int index) {
		return metaDataSet.getName(index);
	}

	public int getSetSize() {
		return metaDataSet.getSetSize();
	}
}

