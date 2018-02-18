package main.component.chart.factory;

import main.component.chart.Chart;
import main.component.chart.Side;
import main.component.chart.color.RainbowColorProvider;
import main.component.chart.data.IMetaDataSet;
import main.component.chart.data.MetaDataSet;
import main.component.chart.drawer.data.line.LineDrawer;

public class LineScrollChartFactory {
	public static Chart create(IMetaDataSet... metaDatas) {
		MetaDataSet metaDataSet = new MetaDataSet();
		for(IMetaDataSet each : metaDatas) metaDataSet.addMetaData(each);
		
		Chart chart = new Chart();
		chart.settingControl.setTitle("Line Scroll Chart");
		
		LineDrawer lineDrawer;		
		lineDrawer = new LineDrawer(Side.LEFT);
		chart.drawerControl.addLeft(lineDrawer, metaDataSet);
		lineDrawer.setColorConditioner(new RainbowColorProvider());
		chart.settingControl.setColumns(500);		
			
		chart.settingControl.setAutoScroll(true);
		chart.settingControl.setLeftLegend(false);
		chart.settingControl.setBottomLegend(true);
		chart.drawerControl.setSettingControl(chart.settingControl);
		chart.flowControl.setLeftIndex(0);
		
		return chart;
	}
}
