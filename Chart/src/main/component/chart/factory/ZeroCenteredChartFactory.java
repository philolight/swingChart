package main.component.chart.factory;

import main.component.chart.Chart;
import main.component.chart.Side;
import main.component.chart.ZeroCenteredChart;
import main.component.chart.color.PlusMinusBarColorProvider;
import main.component.chart.data.IMetaDataSet;
import main.component.chart.data.MetaDataSet;
import main.component.chart.drawer.data.bar.BarDrawer;
import main.component.chart.drawer.data.bar.ZeroCenteredBarDrawer;
import main.component.chart.drawer.data.label.LabelDrawer;
import main.component.chart.drawer.data.label.ZeroCenteredLabelDrawer;
import main.component.chart.drawer.data.line.LineDrawer;
import main.component.chart.drawer.data.line.ZeroCenteredLineDrawer;

public class ZeroCenteredChartFactory {
	public static Chart create(IMetaDataSet... metaDatas) {
		MetaDataSet metaDataSet = new MetaDataSet();
		for(IMetaDataSet each : metaDatas) metaDataSet.addMetaData(each);
		
		Chart chart = new ZeroCenteredChart();
		chart.settingControl.setTitle("Zero Centered Chart");
		
		BarDrawer barDrawer;
		barDrawer = new ZeroCenteredBarDrawer(Side.LEFT);
		barDrawer.setColorConditioner(new PlusMinusBarColorProvider());
		chart.drawerControl.addLeft(barDrawer, metaDataSet);

		LineDrawer lineDrawer;
		lineDrawer = new ZeroCenteredLineDrawer(Side.LEFT);
		chart.drawerControl.addLeft(lineDrawer, metaDataSet);

		LabelDrawer labelDrawer;
		labelDrawer = new ZeroCenteredLabelDrawer(Side.LEFT);
		chart.drawerControl.addLeft(labelDrawer, metaDataSet);
		
		chart.settingControl.setAutoScroll(true);
		chart.flowControl.setLeftIndex(0);
		
		return chart;
	}
}
