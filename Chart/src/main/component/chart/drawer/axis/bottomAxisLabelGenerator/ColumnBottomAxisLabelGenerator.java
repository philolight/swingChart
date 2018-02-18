package main.component.chart.drawer.axis.bottomAxisLabelGenerator;

public class ColumnBottomAxisLabelGenerator implements IBottomAxisLabelGenerator{

	@Override
	public String getLabel(int index) {
		String result = "" + index;
		return result;
	} 
}
