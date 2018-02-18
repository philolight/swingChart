package main.component.chart.factory;

import main.component.chart.Chart;
import main.component.chart.Side;
import main.component.chart.ZeroCenteredChart;
import main.component.chart.color.PlusMinusBarColorProvider;
import main.component.chart.data.IMetaDataSet;
import main.component.chart.data.MetaDataSet;
import main.component.chart.drawer.data.bar.BarDrawer;
import main.component.chart.drawer.data.bar.ZeroCenteredBarDrawer;

public class ZeroCenteredCompareChartFactory {
	public static Chart create(IMetaDataSet... metaDatas) {
		MetaDataSet metaDataSet = new MetaDataSet();
		for(IMetaDataSet each : metaDatas) metaDataSet.addMetaData(each);
		
		Chart chart = new ZeroCenteredChart();
		chart.settingControl.setTitle("Zero Centered Compare Chart");
		
		BarDrawer barDrawer;
		barDrawer = new ZeroCenteredBarDrawer(Side.LEFT);
		barDrawer.setColorConditioner(new PlusMinusBarColorProvider());
		chart.drawerControl.addLeft(barDrawer, metaDataSet);
		
		chart.settingControl.setAutoScroll(true);
		chart.flowControl.setLeftIndex(0);
		
		return chart;
	}
}
