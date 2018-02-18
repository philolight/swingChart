package main.component.chart.color;

import java.awt.Color;

public class NullBarColorProvider extends ColorAdjustment{	
	public NullBarColorProvider(){
		normalColors = new Color[]{Color.BLUE};
		brightColors = new Color[]{Color.WHITE};
		transparentColors = new Color[]{new Color(normalColors[0].getRed(), normalColors[0].getGreen(), normalColors[0].getBlue(), 128)};
	}
	
	@Override public Color getNormalColor(int index, int series, double value) {return normalColors[0];}
	@Override public Color getBrightColor(int index, int series, double value) {return brightColors[0];}
	@Override public Color getTransparentColor(int index, int series, double value) {return transparentColors[0];}
}
