package main.component.chart.factory;

import main.component.chart.Chart;
import main.component.chart.Side;
import main.component.chart.color.RainbowColorProvider;
import main.component.chart.data.IMetaDataSet;
import main.component.chart.data.MetaDataSet;
import main.component.chart.drawer.data.bar.BarDrawer;
import main.component.chart.drawer.data.label.LabelDrawer;

public class CompareScrollChartFactory {
	public static Chart create(IMetaDataSet... metaDatas) {
		MetaDataSet metaDataSet = new MetaDataSet();		
		for(IMetaDataSet each : metaDatas) metaDataSet.addMetaData(each);
		
		Chart chart = new Chart();
		chart.settingControl.setTitle("Compare Scroll Chart");
		
		BarDrawer barDrawer;
		barDrawer = new BarDrawer(Side.LEFT);
		barDrawer.setColorConditioner(new RainbowColorProvider());
		chart.drawerControl.addLeft(barDrawer, metaDataSet);
							
		LabelDrawer labelDrawer;
		labelDrawer = new LabelDrawer(Side.LEFT);
		chart.drawerControl.addLeft(labelDrawer, metaDataSet);
		
		chart.settingControl.setColumns(10);
		chart.settingControl.setAutoScroll(true);
		chart.flowControl.setLeftIndex(0);
		
		return chart;
	}
}
