package test.component.chart.color;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.security.InvalidParameterException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import main.component.chart.color.ColorAdjustment;
import main.component.chart.color.RainbowColorProvider;

public class TestColorAdjustment {
	private ColorAdjustment sut;
	
	Color[] colors = null;
	
	@Before
	public void setUp(){
		sut = Mockito.spy(ColorAdjustment.class);
		
		colors = new Color[]{Color.RED, Color.GREEN, Color.BLUE};
	}
		
	@Test
	public void testBrightColorBrightnessChange(){
		assertEquals(RainbowColorProvider.DEFAULT_BRIGHT_COLOR_BRIGHTNESS, sut.getBrightColorBrightness(), 0.00001f);

		float brightColorBrightness = 0.8f;
		sut.setBrightColorBrightness(brightColorBrightness);		
		assertEquals(brightColorBrightness, sut.getBrightColorBrightness(), 0.00001f);
		
		verify(sut, times(1)).brightColorBrightnessAdjustment();
		
		assertEquals(brightColorBrightness, sut.getAdjustedBrightColorBrightness(), 0.01f);
	}
	
	@Test
	public void testNormalColorBrightnessChange(){
		assertEquals(RainbowColorProvider.DEFAULT_NORMAL_COLOR_BRIGHTNESS, sut.getNormalColorBrightness(), 0.00001f);

		float normalColorBrightness = 0.75f;
		sut.setNormalColorBrightness(normalColorBrightness);		
		assertEquals(normalColorBrightness, sut.getNormalColorBrightness(), 0.00001f);
		
		verify(sut, times(1)).normalColorBrightnessAdjustment();
		
		assertEquals(normalColorBrightness, sut.getAdjustedNormalColorBrightness(), 0.01f);
	}
	
	@Test
	public void testTransparencyChange(){
		assertEquals(RainbowColorProvider.DEFAULT_TRANSPARENCY, sut.getTransparency());

		int newTransparency = 0xA0;
		sut.setTransparency(newTransparency);
		assertEquals(newTransparency, sut.getTransparency());
		
		verify(sut, times(1)).transparencyAdjustment();
		
		assertEquals(newTransparency, sut.getAdjustedTranparentColorTransparency());
	}
	
	@Test(expected = InvalidParameterException.class)
	public void testSetColorTable_Null(){
		Color[] nullColor = null;
		
		sut.setColorTable(nullColor);
	}
	
	@Test(expected = InvalidParameterException.class)
	public void testSetColorTable_ZeroLength(){
		Color[] zeroLengthColor = null;
		zeroLengthColor = new Color[]{};
		
		sut.setColorTable(zeroLengthColor);
	}
	
	@Test
	public void testSetColorTable_Normal(){
		Color[] colors = null;
		colors = new Color[]{Color.RED, Color.GREEN, Color.BLUE};
		
		sut.setColorTable(colors);
		
		verify(sut, times(1)).brightColorBrightnessAdjustment();
		verify(sut, times(1)).normalColorBrightnessAdjustment();
		verify(sut, times(1)).transparencyAdjustment();
		
		assertEquals(RainbowColorProvider.DEFAULT_BRIGHT_COLOR_BRIGHTNESS, 	sut.getAdjustedBrightColorBrightness(), 0.01f);
		assertEquals(RainbowColorProvider.DEFAULT_NORMAL_COLOR_BRIGHTNESS, 	sut.getAdjustedNormalColorBrightness(), 0.01f);
		assertEquals(RainbowColorProvider.DEFAULT_TRANSPARENCY, 				sut.getAdjustedTranparentColorTransparency());
	}
}
