package main.component.chart.color;

import java.awt.Color;
import java.security.InvalidParameterException;

abstract public class ColorAdjustment implements IColorProvider{
	public static final int BRIGHTNESS_INDEX = 2;

	public static final float 	DEFAULT_BRIGHT_COLOR_BRIGHTNESS = 0.95f;
	public static final float 	DEFAULT_NORMAL_COLOR_BRIGHTNESS = 0.65f;
	public static final int 	DEFAULT_TRANSPARENCY 			= 0x80;
	
	protected float brightColorBrightness 	= DEFAULT_BRIGHT_COLOR_BRIGHTNESS;
	protected float normalColorBrightness 	= DEFAULT_NORMAL_COLOR_BRIGHTNESS;	
	protected int 	transparency 			= DEFAULT_TRANSPARENCY;
	
	protected Color[] normalColors = {
			new Color(0xFF, 0x00, 0x00), new Color(0x00, 0xFF, 0x00), new Color(0x00, 0x00, 0xFF),
			new Color(0xFF, 0xFF, 0x00), new Color(0x00, 0xFF, 0xFF), new Color(0xFF, 0x00, 0xFF),
			new Color(0xCC, 0x66, 0x00), new Color(0x00, 0xCC, 0x66), new Color(0xCC, 0x00, 0x66),
			new Color(0x66, 0xCC, 0x00), new Color(0x00, 0x66, 0xCC), new Color(0x66, 0x00, 0xCC)
		};
		
	protected Color[] brightColors = {};
	protected Color[] transparentColors = {};
	
	@Override
	public void setColorTable(Color[] colors) throws InvalidParameterException{
		if(colors == null || colors.length == 0) throw new InvalidParameterException();
		
		this.normalColors = colors;
		adjustmentAll();
	}
	
	protected void adjustmentAll(){
		brightColorBrightnessAdjustment();
		normalColorBrightnessAdjustment();
		transparencyAdjustment();
	}
	
	public void brightColorBrightnessAdjustment() {
		brightColors = adjustment(normalColors, brightColorBrightness, 0xFF);
	}

	public void normalColorBrightnessAdjustment() {
		normalColors = adjustment(normalColors, normalColorBrightness, 0xFF);
	}

	public void transparencyAdjustment() {
		transparentColors = adjustment(normalColors, normalColorBrightness, transparency);
	}
		
	public static Color[] adjustment(Color[] baseColors, float brightness, int transparency){
	    float[] hsb;
	    
	    Color[] colors = new Color[baseColors.length];
	    
		for(int i = 0; i < colors.length; i++){
			hsb = Color.RGBtoHSB(baseColors[i].getRed(), baseColors[i].getGreen(), baseColors[i].getBlue(), null);
			colors[i] = Color.getHSBColor(hsb[0], hsb[1], brightness);
			colors[i] = new Color(colors[i].getRed(), colors[i].getGreen(), colors[i].getBlue(), transparency);
		}

		return colors;
	}
	
	@Override
	public void setBrightColorBrightness(float newBrightness) {
		this.brightColorBrightness = newBrightness;
		brightColorBrightnessAdjustment();
	}
	
	@Override
	public void setNormalColorBrightness(float normalColorBrightness) {
		this.normalColorBrightness = normalColorBrightness;
		normalColorBrightnessAdjustment();
	}
	
	@Override
	public void setTransparency(int newTransparency) {
		this.transparency = newTransparency;
		transparencyAdjustment();
	}
	
	public float getBrightColorBrightness() {
		return brightColorBrightness;
	}
	
	public float getNormalColorBrightness() {
		return normalColorBrightness;
	}
	
	public int getTransparency() {
		return transparency;
	}
		
	public float getAdjustedBrightColorBrightness(){
		return brightnessOfColor(brightColors[0]);
	}
	
	public float getAdjustedNormalColorBrightness() {
		return brightnessOfColor(normalColors[0]);
	}
	
	public int getAdjustedTranparentColorTransparency(){
		return transparentColors[0].getAlpha();
	}
	
	public float brightnessOfColor(Color color){
	    float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
	    return hsb[BRIGHTNESS_INDEX];
	}
}
