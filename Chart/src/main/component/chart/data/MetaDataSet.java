package main.component.chart.data;

import java.util.ArrayList;
import java.util.List;

public class MetaDataSet implements IMetaDataSet{
	public String getName(int index) { return list.get(index).getName(0); }
	public void setName(int index, String name) { list.get(index).setName(0, name);}

	List<IMetaDataSet> list = new ArrayList<IMetaDataSet>();
	double []valueSet = {};
	
	private double globalMin = Double.POSITIVE_INFINITY;
	private double globalMax = Double.NEGATIVE_INFINITY;
	
	public void addMetaData(IMetaDataSet metaDataSet){
		list.add(metaDataSet);
		valueSet = new double[list.size()];
		reset();
	}
	
	public void reset(){
		globalMin = Double.POSITIVE_INFINITY;
		globalMax = Double.NEGATIVE_INFINITY;
	}
	
	@Override
	public double[] get(int index){
		for(int i = 0; i < list.size(); i++){
			IMetaDataSet metaDataSet = list.get(i);
			valueSet[i] = metaDataSet.get(index)[0];
		}
		return valueSet;
	}
	
	@Override
	public void onUpdateData(){
		for(int i = 0; i < list.size(); i++) list.get(i).onUpdateData();
		calculateGlobalMin();
		calculateGlobalMax();
	}
		
	public double getMinBetween(int leftIndex, int rightIndex){
		double min = Double.POSITIVE_INFINITY;
		for(int i = 0; i < list.size(); i++){
			min = Math.min(min, list.get(i).getMinBetween(leftIndex, rightIndex));
		}
		return min;
	}
	
	public double getMaxBetween(int leftIndex, int rightIndex){
		double max = Double.NEGATIVE_INFINITY;
		for(int i = 0; i < list.size(); i++){
			max = Math.max(max, list.get(i).getMaxBetween(leftIndex, rightIndex));
		}
		return max;
	}
	
	private void calculateGlobalMin(){
		for(int i = 0; i < list.size(); i++){
			double value = list.get(i).getGlobalMin();
			if(globalMin > value) globalMin = value;
		}
	}
	
	private void calculateGlobalMax(){
		for(int i = 0; i < list.size(); i++){
			double value = list.get(i).getGlobalMax();
			if(globalMax < value) globalMax = value;
		}
	}

	@Override
	public int size() {
		int minSize = 0;
		for(int i = 0; i < list.size(); i++){
			if(i == 0 || minSize > list.get(i).size()) minSize = list.get(i).size();
		}
		return minSize;
	}

	@Override
	public double getGlobalMin() {
		return globalMin;
	}

	@Override
	public double getGlobalMax() {
		return globalMax;
	}
	
	@Override public int getSetSize() {
		int result = 0;
		for(int i = 0; i < list.size(); i++){
			result += list.get(i).getSetSize();
		}
		
		return result;
	}
}
