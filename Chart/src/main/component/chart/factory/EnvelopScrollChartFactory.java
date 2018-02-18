package main.component.chart.factory;

import main.component.chart.Chart;
import main.component.chart.Side;
import main.component.chart.color.RainbowColorProvider;
import main.component.chart.data.IMetaDataSet;
import main.component.chart.data.MetaDataSet;
import main.component.chart.drawer.data.envelop.EnvelopDrawer;

public class EnvelopScrollChartFactory {
	public static Chart create(IMetaDataSet... metaDatas) {
		MetaDataSet metaDataSet = new MetaDataSet();
		for(IMetaDataSet each : metaDatas) metaDataSet.addMetaData(each);
		
		Chart chart = new Chart();
		chart.settingControl.setTitle("Envelop Scroll Chart");
		
		EnvelopDrawer envelopDrawer;		
		envelopDrawer = new EnvelopDrawer(Side.LEFT);
		chart.drawerControl.addLeft(envelopDrawer, metaDataSet);
		envelopDrawer.setColorConditioner(new RainbowColorProvider());
		chart.settingControl.setColumns(100);
		
		chart.settingControl.setAutoScroll(true);
		chart.flowControl.setLeftIndex(0);
		
		return chart;
	}
}
