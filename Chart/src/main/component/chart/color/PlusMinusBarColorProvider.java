package main.component.chart.color;

import java.awt.Color;

public class PlusMinusBarColorProvider extends ColorAdjustment{	
	public PlusMinusBarColorProvider(){
		normalColors = new Color[]{new Color(0xAA, 0x00, 0x00), new Color(0x00, 0x88, 0x00)};
		adjustmentAll();
	}
		
	@Override public Color getNormalColor(int index, int series, double value){
		if(value >= 0.0) return normalColors[0];
		return normalColors[1];
	}
	
	@Override public Color getBrightColor(int index, int series, double value){
		if(value >= 0.0) return brightColors[0];
		return brightColors[1];
	}
	
	@Override public Color getTransparentColor(int index, int series, double value) {
		if(value >= 0.0) return transparentColors[0];
		return transparentColors[1];
	}
}
