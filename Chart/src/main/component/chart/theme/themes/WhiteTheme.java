package main.component.chart.theme.themes;

import java.awt.Color;

import main.component.chart.theme.Theme;
import main.component.common.VerticalPosition;

public class WhiteTheme extends Theme{
	public WhiteTheme(){
		setBackgroundColor(new Color(0xE0, 0xE0, 0xE0), new Color(0xFF, 0xFF, 0xFF));
		setLineColor(new Color(0x33, 0x33, 0x33));
		setFontColor(new Color(0x22, 0x22, 0x22));
		
		title.backgroundStartColor = new Color(0xC0, 0xC0, 0xC0);
		title.backgroundEndColor = new Color(0xFF, 0xFF, 0xFF);
		title.fontColor = new Color(0x88, 0x88, 0x88);
		
		footer.backgroundColor = new Color(0xDD, 0xDD, 0xDD);
		footer.fontColor = Color.RED;
		
		dataBackground.backgroundColor = new Color(0xEE, 0xEE, 0xEE);
		dataBackground.stripColor = new Color(0xE0, 0xE0, 0xE0);
		dataBackground.gradationColor = new Color(0xCC, 0xCC, 0xFF);
		
		labelChart.positionType = VerticalPosition.TOP;
		labelChart.fontColor = Color.BLACK;
	}
}
