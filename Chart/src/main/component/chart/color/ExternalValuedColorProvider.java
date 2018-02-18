package main.component.chart.color;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

/** index 번째 데이터를 indexCondition 값을 인덱스로 하는 색상으로 표시하게 만들어 주는 Conditioner */
public class ExternalValuedColorProvider extends ColorAdjustment{
	List<Integer> indexCondition = Collections.emptyList();
		
	public void setIndexCondition(List<Integer> indexCondition){
		this.indexCondition = indexCondition;
	}
	
	private int getValueOfIndex(int index){
		if(indexCondition.size() <= index) return 0;
		return indexCondition.get(index);
	}
	
	@Override public Color getNormalColor(int index, int series, double value) { return normalColors[getValueOfIndex(index)]; }
	@Override public Color getBrightColor(int index, int series, double value) { return brightColors[getValueOfIndex(index)]; }
	@Override public Color getTransparentColor(int index, int series, double value) { return transparentColors[getValueOfIndex(index)]; }
}
