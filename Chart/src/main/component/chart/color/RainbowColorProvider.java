package main.component.chart.color;

import java.awt.Color;
import java.security.InvalidParameterException;

public class RainbowColorProvider extends ColorAdjustment{	
	int skew = 0;
	
	public RainbowColorProvider(){
		this(0);
	}
		
	public RainbowColorProvider(int skew){
		setSkew(skew);
		adjustmentAll();
	}
	
	public void setSkew(int skew){
		this.skew = skew;
	}
	
	public int getSkew(){ return skew; }
	
	@Override public Color getNormalColor(int index, int series, double value){ 
		return normalColors[(series + skew) % normalColors.length];
	}
	
	@Override public Color getBrightColor(int index, int series, double value){ 
		return brightColors[(series + skew) % brightColors.length]; 
	}
	
	@Override public Color getTransparentColor(int index, int series, double value){ 
		return transparentColors[(series + skew) % transparentColors.length]; 
	}
}
