package main.component.chart.factory;

import java.util.ArrayList;
import java.util.List;

import main.component.chart.Chart;
import main.component.chart.Side;
import main.component.chart.color.RainbowColorProvider;
import main.component.chart.data.IMetaDataSet;
import main.component.chart.data.MetaDataSet;
import main.component.chart.drawer.axis.bottomAxisLabelGenerator.StringBottomAxisLabelGenerator;
import main.component.chart.drawer.data.bar.BarDrawer;

public class TextLabelChart {
	public static Chart create(IMetaDataSet... metaDatas) {
		MetaDataSet metaDataSet = new MetaDataSet();		
		for(IMetaDataSet each : metaDatas) metaDataSet.addMetaData(each);
		
		Chart chart = new Chart();
		chart.settingControl.setTitle("Text Label Chart");
		
		BarDrawer barDrawer;
		barDrawer = new BarDrawer(Side.LEFT);
		barDrawer.setColorConditioner(new RainbowColorProvider());
		chart.drawerControl.addLeft(barDrawer, metaDataSet);

		List<String> calendarLabels = new ArrayList<String>();
		calendarLabels.add("January"); 
		calendarLabels.add("February"); 
		calendarLabels.add("March");
		calendarLabels.add("April");
		calendarLabels.add("May");
		calendarLabels.add("June");
		calendarLabels.add("July");
		calendarLabels.add("August");
		calendarLabels.add("September");
		calendarLabels.add("October");
		calendarLabels.add("November");
		calendarLabels.add("December");
		
		StringBottomAxisLabelGenerator labelGenerator = new StringBottomAxisLabelGenerator();
		labelGenerator.setLabels(calendarLabels);
		chart.drawerControl.getBottomAxisDrawer().setBottomAxisLabelGenerator(labelGenerator);
		
		chart.settingControl.setColumns(12);
		chart.settingControl.setAutoScroll(false);
		chart.flowControl.setLeftIndex(0);
		
		return chart;
	}
}
