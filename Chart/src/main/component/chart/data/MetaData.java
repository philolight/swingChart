package main.component.chart.data;

import java.util.Collections;
import java.util.List;

import main.component.chart.data.calculator.ICalculator;

public class MetaData<N extends Number> implements IMetaDataSet{
	private String name = "";	
	public List<N> data = Collections.emptyList();
	public ICalculator<N> calculator;
	
	private int beforeDataSize = 0;
	private double globalMin = Double.POSITIVE_INFINITY;
	private double globalMax = Double.NEGATIVE_INFINITY;
	
	public void setCalculator(ICalculator<N> calculator){ this.calculator = calculator; }
	public void setData(List<N> data){
		this.data = data;
		reset();
	}
	
	public MetaData(){}	
	public MetaData(String name, List<N> data, ICalculator<N> calculator){
		this();
		this.name = name;
		setData(data);
		setCalculator(calculator);
	}
	
	public void reset(){
		beforeDataSize = 0;
		globalMin = Double.POSITIVE_INFINITY;
		globalMax = Double.NEGATIVE_INFINITY;
	}
	
	double []valueSet = new double[1];
	
	@Override
	public double[] get(int index){
		valueSet[0] = calculator.doubleValue(data.get(index));
		return valueSet;
	}
		
	@Override
	public void onUpdateData(){
		calculateGlobalMin();
		calculateGlobalMax();
		beforeDataSize = data.size();
	}
	
	@Override
	public double getMinBetween(int leftIndex, int rightIndex){
		double min = Double.POSITIVE_INFINITY;

		for(int i = leftIndex; i <= rightIndex; i++){
			double value = calculator.doubleValue(data.get(i));
			if(min > value) min = value;
		}
		return min;
	}
	
	@Override
	public double getMaxBetween(int leftIndex, int rightIndex){
		double max = Double.NEGATIVE_INFINITY;
		for(int i = leftIndex; i <= rightIndex; i++){
			double value = calculator.doubleValue(data.get(i));
			if(max < value) max = value;
		}
		return max;
	}
	
	private void calculateGlobalMin(){
		for(int i = beforeDataSize; i < data.size(); i++){
			double value = calculator.doubleValue(data.get(i));
			if(globalMin > value) globalMin = value;
		}
	}
		
	private void calculateGlobalMax(){
		for(int i = beforeDataSize; i < data.size(); i++){
			double value = calculator.doubleValue(data.get(i));
			if(globalMax < value) globalMax = value;
		}
	}
	
	@Override public int size() { return data.size(); }
	@Override public double getGlobalMin() { return globalMin; }
	@Override public double getGlobalMax() { return globalMax; }
	@Override public int getSetSize() { return 1; }
	@Override public void setName(int i, String name) { this.name = name; }
	@Override public String getName(int index) { return name; }
}