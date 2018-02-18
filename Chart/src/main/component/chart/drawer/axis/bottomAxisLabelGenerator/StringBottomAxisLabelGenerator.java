package main.component.chart.drawer.axis.bottomAxisLabelGenerator;

import java.util.Collections;
import java.util.List;

public class StringBottomAxisLabelGenerator implements IBottomAxisLabelGenerator{
	List<String> labels = Collections.emptyList();
	
	public void setLabels(List<String> labels){ this.labels = labels; }
	
	@Override
	public String getLabel(int index) {
		if(index >= labels.size()) return "";
		return labels.get(index);
	}
}
