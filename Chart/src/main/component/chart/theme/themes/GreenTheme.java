package main.component.chart.theme.themes;

import java.awt.Color;

import main.component.chart.theme.Theme;
import main.component.common.VerticalPosition;

public class GreenTheme extends Theme{
	public GreenTheme(){
		setBackgroundColor(new Color(0x11, 0x33, 0x11), new Color(0x33, 0x99, 0x33));
		setLineColor(new Color(0xCC, 0xCC, 0xCC));
		setFontColor(new Color(0xCC, 0xCC, 0xCC));
		
		title.backgroundStartColor = new Color(0x11, 0x33, 0x11);
		title.backgroundEndColor = new Color(0x55, 0x99, 0x55);
//		title.fontColor = new Color(0x66, 0x66, 0x33); 
		title.fontColor = new Color(0xAA, 0xAA, 0x66);
//		title.fontBaseColor = new Color(0x00, 0x00, 0x00);
		
		footer.backgroundColor = new Color(0x33, 0x66, 0x33);
		footer.fontColor = Color.ORANGE;
		
		dataBackground.backgroundColor = new Color(0xEE, 0xEE, 0xBB);
		dataBackground.stripColor = new Color(0xEE, 0xDD, 0xBB);
		dataBackground.gradationColor = new Color(0xAA, 0xAA, 0xAA);
		
		labelChart.positionType = VerticalPosition.TOP;
		labelChart.fontColor = Color.LIGHT_GRAY;
	}
}
