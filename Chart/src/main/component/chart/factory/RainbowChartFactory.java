package main.component.chart.factory;

import main.component.chart.Chart;
import main.component.chart.Side;
import main.component.chart.color.RainbowColorProvider;
import main.component.chart.data.IMetaDataSet;
import main.component.chart.data.MetaDataSet;
import main.component.chart.drawer.data.bar.BarDrawer;
import main.component.chart.drawer.data.label.LabelDrawer;

public class RainbowChartFactory {
	public static Chart create(IMetaDataSet... metaDatas) {
		MetaDataSet metaDataSet = new MetaDataSet();
		for(IMetaDataSet each : metaDatas) metaDataSet.addMetaData(each);
		
		Chart chart = new Chart();
		chart.settingControl.setTitle("Rainbow Chart");
		
		BarDrawer barDrawer;	
		barDrawer = new BarDrawer(Side.LEFT);
		chart.drawerControl.addLeft(barDrawer, metaDataSet);
		barDrawer.setColorConditioner(new RainbowColorProvider());
		
		LabelDrawer labelDrawer;
		labelDrawer = new LabelDrawer(Side.LEFT);
		chart.drawerControl.addLeft(labelDrawer, metaDataSet);
		
		chart.settingControl.setAutoScroll(true);
		chart.settingControl.setColumns(24);
		chart.flowControl.setLeftIndex(0);
		
		return chart;
	}
}
