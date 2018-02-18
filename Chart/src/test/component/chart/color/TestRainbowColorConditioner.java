package test.component.chart.color;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.security.InvalidParameterException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import main.component.chart.color.RainbowColorProvider;
// TDD 첫 시도.
// 일단 장황해지는 경향이 있다.
// 하지만 세밀하고 꼼꼼해지며, 생각하지 못했던 오류를 발견해 낼 수 있다.
// 설계가 점점 치밀해진다.
// 결과 확인을 위한 함수들(값 확인)을 많이 만들어야 한다.(getter)
// 결과 확인을 위한 함수가 구현에 미치는 영향을 분리해야 한다.
public class TestRainbowColorConditioner {
	RainbowColorProvider sut = null;
	Color[] colors = null;
	
	final int RED 	= 0;
	final int GREEN = 1;
	final int BLUE 	= 2;
	
	@Before
	public void setUp(){
		sut = Mockito.spy(RainbowColorProvider.class);
		
		colors = new Color[]{Color.RED, Color.GREEN, Color.BLUE};
	}

	@Test
	public void testConstructorCallsAdjustmentFunctions_constructor가색상맞춤함수들을호출하는지테스트(){		
		// 각 adjustment 함수 호출이 이루어졌음을 간접적으로 확인함.
		assertEquals(RainbowColorProvider.DEFAULT_BRIGHT_COLOR_BRIGHTNESS, 	sut.getAdjustedBrightColorBrightness(), 0.01f);
		assertEquals(RainbowColorProvider.DEFAULT_NORMAL_COLOR_BRIGHTNESS, 	sut.getAdjustedNormalColorBrightness(), 0.01f);
		assertEquals(RainbowColorProvider.DEFAULT_TRANSPARENCY, 				sut.getAdjustedTranparentColorTransparency());
	}

	@Test
	public void testNormalColor(){		
		sut.setColorTable(colors);
		
		Color color = sut.getNormalColor(dontCare(0), RED, dontCare(0.0f));
		assertEquals(Color.RED, dominantColor(color));
		
		color = sut.getNormalColor(dontCare(0), GREEN, dontCare(0.0f));
		assertEquals(Color.GREEN, dominantColor(color));
		
		color = sut.getNormalColor(dontCare(0), BLUE, dontCare(0.0f));
		assertEquals(Color.BLUE, dominantColor(color));
	}
	
	@Test
	public void testBrightColor(){
		sut.setColorTable(colors);
		
		Color color = sut.getBrightColor(dontCare(0), RED, dontCare(0.0f));
		assertEquals(Color.RED, dominantColor(color));
		
		color = sut.getBrightColor(dontCare(0), GREEN, dontCare(0.0f));
		assertEquals(Color.GREEN, dominantColor(color));
		
		color = sut.getBrightColor(dontCare(0), BLUE, dontCare(0.0f));
		assertEquals(Color.BLUE, dominantColor(color));
	}
		
	@Test
	public void testTransparentColor(){	
		sut.setColorTable(colors);
		
		Color color = sut.getTransparentColor(dontCare(0), RED, dontCare(0.0f));
		assertEquals(Color.RED, dominantColor(color));
		
		color = sut.getTransparentColor(dontCare(0), GREEN, dontCare(0.0f));
		assertEquals(Color.GREEN, dominantColor(color));
		
		color = sut.getTransparentColor(dontCare(0), BLUE, dontCare(0.0f));
		assertEquals(Color.BLUE, dominantColor(color));
	}
	
	private Color dominantColor(Color color){
		return (color.getRed() > color.getGreen()) ? 
				((color.getRed() > color.getBlue()) ? Color.RED : Color.BLUE) : 
					(color.getGreen() > color.getBlue()) ? Color.GREEN : Color.BLUE;
	}
	
	private int dontCare(int n){return n;}
	private float dontCare(float v){return v;}
	
	@Test
	public void testSkew(){
		assertEquals(0, sut.getSkew());
		
		sut.setColorTable(colors);
		
		int skew = 1;
		sut.setSkew(skew);
		
		int deskewedRed 	= RED 	- skew;
		int deskewedGreen 	= GREEN - skew;
		int deskewedBlue 	= BLUE 	- skew;
		
		Color color = sut.getTransparentColor(dontCare(0), deskewedRed, dontCare(0.0f));
		assertEquals(Color.RED, dominantColor(color));
		
		color = sut.getTransparentColor(dontCare(0), deskewedGreen, dontCare(0.0f));
		assertEquals(Color.GREEN, dominantColor(color));
		
		color = sut.getTransparentColor(dontCare(0), deskewedBlue, dontCare(0.0f));
		assertEquals(Color.BLUE, dominantColor(color));
	}
}
