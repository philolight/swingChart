package main.component.chart.theme.themes;

import java.awt.Color;

import main.component.chart.theme.Theme;
import main.component.common.VerticalPosition;

public class GoldTheme extends Theme{
	public GoldTheme(){
		setBackgroundColor(new Color(229, 189, 67), new Color(248, 243, 151));
		setLineColor(new Color(0x33, 0x33, 0x99));
		setFontColor(new Color(0x22, 0x22, 0x99));
		
		title.backgroundStartColor = new Color(175, 129, 41);
		title.backgroundEndColor = new Color(254, 247, 169);
		title.fontColor = new Color(0xCC, 0x99, 0x44);
		
		footer.backgroundColor = new Color(214, 172, 62);
		footer.fontColor = Color.RED;
		
		dataBackground.backgroundColor = new Color(248, 243, 151);
		dataBackground.stripColor = new Color(254, 247, 169);
		dataBackground.gradationColor = new Color(229, 189, 128);
		
		labelChart.positionType = VerticalPosition.TOP;
		labelChart.fontColor = Color.BLACK;
	}
}
