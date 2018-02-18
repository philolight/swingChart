package main.component.chart.color;

import java.awt.Color;
import java.security.InvalidParameterException;

public interface IColorProvider {
	public static final IColorProvider NULL = new NullBarColorProvider();
	
	public Color getNormalColor(int index, int series, double value);
	public Color getBrightColor(int index, int series, double value);
	public Color getTransparentColor(int index, int series, double value);

	public void setColorTable(Color []colors) throws InvalidParameterException;	
	public void setBrightColorBrightness(float value);
	public void setNormalColorBrightness(float value);
	public void setTransparency(int value);
}
