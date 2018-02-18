package main.component.chart.theme.themes;

import java.awt.Color;

import main.component.chart.theme.Theme;
import main.component.common.VerticalPosition;

public class BlueTheme extends Theme{
	public BlueTheme(){
		setBackgroundColor(new Color(0x00, 0x00, 0x22), new Color(0x00, 0x00, 0x80));
		setLineColor(new Color(0xCC, 0xCC, 0xCC));
		setFontColor(new Color(0xCC, 0xCC, 0xCC));
		
		title.backgroundStartColor = new Color(0x00, 0x00, 0x33);
		title.backgroundEndColor = new Color(0x22, 0x22, 0x99);

		title.fontColor = new Color(0xAA, 0xAA, 0x66);
		
		footer.backgroundColor = new Color(0x66, 0x66, 0xCC);
		footer.fontColor = Color.ORANGE;
		
		dataBackground.backgroundColor = new Color(0xEE, 0xEE, 0xFF);
		dataBackground.stripColor = new Color(0xDD, 0xDD, 0xEE);
		dataBackground.gradationColor = new Color(0x00, 0x00, 0xFF);
		
		labelChart.positionType = VerticalPosition.TOP;
		labelChart.fontColor = Color.DARK_GRAY;
	}
}
