package main.component.chart.factory;

import main.component.chart.Chart;
import main.component.chart.Side;
import main.component.chart.data.IMetaDataSet;
import main.component.chart.data.MetaDataSet;
import main.component.chart.drawer.data.bar.BarDrawer;
import main.component.chart.drawer.data.label.LabelDrawer;
import main.component.chart.drawer.data.line.LineDrawer;

public class ScrollChartFactory {
	public static Chart create(IMetaDataSet... metaDatas) {
		MetaDataSet metaDataSet = new MetaDataSet();		
		for(IMetaDataSet each : metaDatas) metaDataSet.addMetaData(each);
		
		Chart chart = new Chart();
		chart.settingControl.setTitle("Scroll Chart");
		
		BarDrawer barDrawer;
		barDrawer = new BarDrawer(Side.LEFT);
		chart.drawerControl.addLeft(barDrawer, metaDataSet);
		
		LineDrawer lineDrawer;
		lineDrawer = new LineDrawer(Side.LEFT);
		chart.drawerControl.addLeft(lineDrawer, metaDataSet);
		
		LabelDrawer labelDrawer;
		labelDrawer = new LabelDrawer(Side.LEFT);
		chart.drawerControl.addLeft(labelDrawer, metaDataSet);	
		
		chart.settingControl.setAutoScroll(true);
		chart.flowControl.setLeftIndex(0);
		
		return chart;
	}
}
