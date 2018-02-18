package main.component.chart.factory;

import java.awt.Color;
import java.util.List;

import main.component.chart.Chart;
import main.component.chart.Side;
import main.component.chart.color.ExternalValuedColorProvider;
import main.component.chart.data.IMetaDataSet;
import main.component.chart.data.MetaDataSet;
import main.component.chart.drawer.data.bar.BarDrawer;
import main.component.chart.drawer.data.label.LabelDrawer;

public class ExternalValuedColorChartFactory {
	public static Chart create(List<Integer> indexCondition, IMetaDataSet... metaDatas) {
		MetaDataSet metaDataSet = new MetaDataSet();
		for(IMetaDataSet each : metaDatas) metaDataSet.addMetaData(each);

		Chart chart = new Chart();
		chart.settingControl.setTitle("External Valued Color Chart Factory");

		BarDrawer barDrawer;
		barDrawer = new BarDrawer(Side.LEFT);
		chart.drawerControl.addLeft(barDrawer, metaDataSet);

		ExternalValuedColorProvider colorConditioner = new ExternalValuedColorProvider();
		colorConditioner.setIndexCondition(indexCondition);

		Color[] newColors = {
				new Color(0x80, 0x80, 0x80),
				new Color(0x00, 0xFF, 0x00),
				new Color(0x00, 0xFF, 0xFF),
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

		chart.settingControl.setAutoScroll(false);
		chart.settingControl.setColumns(24);
		chart.flowControl.setLeftIndex(0);

		return chart;
	}
}