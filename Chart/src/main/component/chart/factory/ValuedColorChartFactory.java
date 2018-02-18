package main.component.chart.factory;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import main.component.chart.Chart;
import main.component.chart.Side;
import main.component.chart.color.ValuedColorProvider;
import main.component.chart.data.IMetaDataSet;
import main.component.chart.data.MetaDataSet;
import main.component.chart.drawer.data.bar.BarDrawer;
import main.component.chart.drawer.data.label.LabelDrawer;

public class ValuedColorChartFactory {
	public static Chart create(IMetaDataSet... metaDatas) {
		MetaDataSet metaDataSet = new MetaDataSet();
		for(IMetaDataSet each : metaDatas) metaDataSet.addMetaData(each);
		
		Chart chart = new Chart();
		chart.settingControl.setTitle("Valued Color Scroll Chart");
				
		BarDrawer barDrawer;
		barDrawer = new BarDrawer(Side.LEFT);
		chart.drawerControl.addLeft(barDrawer, metaDataSet);
		
		List<Double> valueList = new ArrayList<Double>();
		valueList.add(5.0);
		valueList.add(10.0);
		valueList.add(15.0);
		valueList.add(20.0);
		valueList.add(25.0);
		valueList.add(30.0);
		valueList.add(35.0);
		valueList.add(40.0);
		valueList.add(45.0);
		valueList.add(50.0);
		valueList.add(55.0);
		valueList.add(60.0);
		valueList.add(65.0);
		valueList.add(70.0);
		valueList.add(75.0);
		valueList.add(80.0);
		valueList.add(85.0);
		valueList.add(90.0);
		valueList.add(95.0);
		
		ValuedColorProvider colorConditioner = new ValuedColorProvider();
		colorConditioner.setValueIndex(valueList);
		
		Color[] newColors = {
			new Color(0x00, 0x00, 0xFF),
			new Color(0x00, 0x2F, 0xFF),
			new Color(0x00, 0x5F, 0xFF),
			new Color(0x00, 0x8F, 0xFF),
			new Color(0x00, 0xBF, 0xFF),
			new Color(0x00, 0xFF, 0xFF),
			new Color(0x00, 0xFF, 0xBF),
			new Color(0x00, 0xFF, 0x8F),
			new Color(0x00, 0xFF, 0x5F),
			new Color(0x00, 0xFF, 0x2F),
			new Color(0x00, 0xFF, 0x00),
			new Color(0x2F, 0xFF, 0x00),
			new Color(0x5F, 0xFF, 0x00),
			new Color(0x8F, 0xFF, 0x00),
			new Color(0xBF, 0xFF, 0x00),
			new Color(0xFF, 0xFF, 0x00),
			new Color(0xFF, 0xBF, 0x00),
			new Color(0xFF, 0x8F, 0x00),
			new Color(0xFF, 0x5F, 0x00),
			new Color(0xFF, 0x3F, 0x00),
			new Color(0xFF, 0x00, 0x00)
		};
		
		colorConditioner.setColorTable(newColors);
		
		barDrawer.setColorConditioner(colorConditioner);
		
		LabelDrawer labelDrawer;
		labelDrawer = new LabelDrawer(Side.LEFT);
		chart.drawerControl.addLeft(labelDrawer, metaDataSet);	
		
		chart.settingControl.setAutoScroll(true);
		chart.flowControl.setLeftIndex(0);
		
		return chart;
	}
}
