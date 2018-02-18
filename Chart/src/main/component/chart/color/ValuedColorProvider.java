package main.component.chart.color;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

public class ValuedColorProvider extends ColorAdjustment{
	List<Double> valueIndex = Collections.emptyList();
	
	public ValuedColorProvider(){
		adjustmentAll();
	}
	
	public void setValueIndex(List<Double> valueIndex){
		this.valueIndex = valueIndex;
	}
	
	private int getIndexByValue(double value){
		for(int i = 0; i < valueIndex.size(); i++){
			if(value < valueIndex.get(i)) return i;
		}
		
		return valueIndex.size() - 1;
	}
	
	@Override public Color getNormalColor(int index, int series, double value) { return normalColors[getIndexByValue(value)]; }
	@Override public Color getBrightColor(int index, int series, double value) { return brightColors[getIndexByValue(value)]; }
	@Override public Color getTransparentColor(int index, int series, double value) { return transparentColors[getIndexByValue(value)]; }
}
