package main.component.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.security.InvalidParameterException;

import org.junit.Assert;

import main.component.chart.control.area.AreaInfo;

/**
 * 1. 텍스트 사이즈를 area 크기에 맞게 조정한다.
 * 2. 가장 작은 텍스트 사이즈는 MIN_FONT_SIZE로 고정한다.
 * 3. 
 */

public class Text extends AreaInfo{
	public static final int MIN_FONT_SIZE = 4;
	
	public String text = "";
	public Color fontColor = Color.BLACK;
	public Font defaultFont = new Font("Arial Black", Font.PLAIN, 16);
	public Font usingFont;
	public int fontHeight = (int)(16 / GraphicProperty.textHeightAdjustment());
	public int textLines = 1;
	public int maxTextWidth = 0;
	public int textHeight = 0;
	public int fontSize = 16;
	
	public Text(){
		super();
		resizeUsingFont();
	}
	
	@Override
	public void setArea(Rectangle area) throws InvalidParameterException{
		if(area == null || area.isEmpty()) throw new InvalidParameterException();
		
		super.setArea(area);
		resizeUsingFont();
	}
	
	public void setDefaultFont(Font font){
		if(font == null) throw new InvalidParameterException();
		
		this.defaultFont = font;
		resizeUsingFont();
	}
	
	public void setText(String text){
		this.text = text;
		textLines = 1;
		resizeUsingFont();
	}
	
	public String getText(){
		return text;
	}
	
	public void resizeUsingFont(){
		if(text.equals("") || area == null){
			usingFont = defaultFont;
			maxTextWidth = 0;
			textHeight = 0;
			textLines = 1;
			return;
		}
		calculateTextSize();
	}

	AffineTransform affinetransform = new AffineTransform();
	FontRenderContext frc = new FontRenderContext(affinetransform,true,true);	
	
	private void calculateTextSize(){
		usingFont = defaultFont;
		
		maxTextWidth = getTextWidth(usingFont);
		textHeight = getTextHeight(usingFont);
		
		int properFontSize = (int)(area.getHeight() * GraphicProperty.textHeightAdjustment() / textLines) - 1;
		
		if(textHeight * textLines > area.getHeight()){
			usingFont = new Font(defaultFont.getFontName(), defaultFont.getStyle(), properFontSize);
		}
		
		maxTextWidth = getTextWidth(usingFont);
		textHeight = getTextHeight(usingFont);

		int newFontSize = properFontSize;

		while(maxTextWidth > area.getWidth() && newFontSize > 1){
			newFontSize = (int)(newFontSize * area.getWidth() / maxTextWidth);
			if(newFontSize > properFontSize) return;
			
			usingFont = new Font(defaultFont.getFontName(), defaultFont.getStyle(), newFontSize);
			
			maxTextWidth = getTextWidth(usingFont);
			textHeight = getTextHeight(usingFont);

		}
		
		fontSize = newFontSize;
	}
	
	public Font getUsingFont(){
		return usingFont;
	}

	public int getTextWidth(Font font) {
		return (int)(font.getStringBounds(text, frc).getWidth());
	}

	public int getTextHeight(Font font) {
		return (int)(font.getStringBounds(text, frc).getHeight());
	}
}
