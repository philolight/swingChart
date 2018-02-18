package main.component.chart.theme.themes;

import java.awt.Color;

import main.component.chart.theme.Theme;
import main.component.common.VerticalPosition;

public class YellowTheme extends Theme{
	public YellowTheme(){
		setBackgroundColor(new Color(0xFF, 0xFF, 0xDD), new Color(0xFF, 0xFF, 0xFF));
		setLineColor(new Color(0x33, 0x33, 0x99));
		setFontColor(new Color(0x22, 0x22, 0x99));
		
		title.backgroundStartColor = new Color(0xDD, 0xDD, 0xBB);
		title.backgroundEndColor = new Color(0xFF, 0xFF, 0xDD);
		title.fontColor = new Color(0xCC, 0x99, 0x55);
		
		footer.backgroundColor = new Color(0xFF, 0xFF, 0xEE);
		footer.fontColor = Color.RED;
		
		dataBackground.backgroundColor = new Color(0xEE, 0xEE, 0xEE);
		dataBackground.stripColor = new Color(0xE0, 0xE0, 0xE0);
		dataBackground.gradationColor = new Color(0xCC, 0xFF, 0xCC);
		
		labelChart.positionType = VerticalPosition.TOP;
		labelChart.fontColor = Color.BLACK;
	}
}
