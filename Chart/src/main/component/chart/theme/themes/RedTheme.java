package main.component.chart.theme.themes;

import java.awt.Color;

import main.component.chart.theme.Theme;
import main.component.common.VerticalPosition;

public class RedTheme extends Theme{
	public RedTheme(){
		setBackgroundColor(new Color(0x11, 0x11, 0x11), new Color(0x66, 0x11, 0x11));
		setLineColor(new Color(0xCC, 0xCC, 0xCC));
		setFontColor(new Color(0xCC, 0xCC, 0xCC));
		
		title.backgroundStartColor = new Color(0x00, 0x00, 0x00);
		title.backgroundEndColor = new Color(0x33, 0x00, 0x00);
		title.fontColor = new Color(0xAA, 0xAA, 0x66);
		
		footer.backgroundColor = new Color(0x33, 0x00, 0x00);
		footer.fontColor = Color.ORANGE;
		
		dataBackground.backgroundColor = new Color(0x22, 0x22, 0x22);
		dataBackground.stripColor = new Color(0x11, 0x11, 0x11);
		dataBackground.gradationColor = new Color(0x55, 0x00, 0x00);
		
		labelChart.positionType = VerticalPosition.TOP;
		labelChart.fontColor = Color.LIGHT_GRAY;
	}
}
