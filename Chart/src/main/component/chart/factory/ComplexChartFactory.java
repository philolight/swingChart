package main.component.chart.factory;

import main.component.chart.Chart;
import main.component.chart.Side;
import main.component.chart.color.RainbowColorProvider;
import main.component.chart.control.area.HorizontalPartition;
import main.component.chart.data.IMetaDataSet;
import main.component.chart.data.MetaDataSet;
import main.component.chart.drawer.data.bar.BarDrawer;
import main.component.chart.drawer.data.envelop.EnvelopDrawer;
import main.component.chart.drawer.data.label.LabelDrawer;
import main.component.chart.drawer.data.line.LineDrawer;

public class ComplexChartFactory {
	public static Chart create(IMetaDataSet... metaDatas) {
				
		Chart chart = new Chart();
		chart.settingControl.setTitle("Compare Scroll Chart");
		
		MetaDataSet envelopMetaDataSet = new MetaDataSet();
		for(int i = 0; i < 3; i++) envelopMetaDataSet.addMetaData(metaDatas[i]);
		
		EnvelopDrawer envelopDrawer;		
		envelopDrawer = new EnvelopDrawer(Side.LEFT);
		chart.drawerControl.addLeft(envelopDrawer, envelopMetaDataSet);
		envelopDrawer.setColorConditioner(new RainbowColorProvider(3));
		
		MetaDataSet compareBarMetaDataSet = new MetaDataSet();
		for(int i = 3; i < 6; i++) compareBarMetaDataSet.addMetaData(metaDatas[i]);
		
		BarDrawer barDrawer;
		barDrawer = new BarDrawer(Side.LEFT);
		barDrawer.setColorConditioner(new RainbowColorProvider());
		chart.drawerControl.addLeft(barDrawer, compareBarMetaDataSet);
		
		LabelDrawer labelDrawer;
		labelDrawer = new LabelDrawer(Side.LEFT);
		chart.drawerControl.addLeft(labelDrawer, compareBarMetaDataSet);
		
		MetaDataSet lineMetaDataSet = new MetaDataSet();
		for(int i = 6; i < 9; i++) lineMetaDataSet.addMetaData(metaDatas[i]);
		
		LineDrawer lineDrawer;
		lineDrawer = new LineDrawer(Side.RIGHT);
		chart.drawerControl.addRight(lineDrawer, lineMetaDataSet);
		lineDrawer.setColorConditioner(new RainbowColorProvider(6));

		try {
			chart.areaControl.setHorizontalPartition(
					new HorizontalPartition.HorizontalPartitionBuilder()
					.setLeftAxis(0.1)
					.setLeftLegend(0.15)
					.setData(0.5)
					.setRightAxis(0.1)
					.setRightLegend(0.15).build()
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chart.settingControl.setRightLegend(true);
		chart.settingControl.setColumns(20);
		chart.settingControl.setAutoScroll(true);
		chart.settingControl.setLineChartIsDotOnTheLine(false);
		chart.drawerControl.setSettingControl(chart.settingControl);
		chart.flowControl.setLeftIndex(0);
		
		return chart;
	}
}
