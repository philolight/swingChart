package test.component.chart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.awt.Font;
import java.awt.Rectangle;
import java.security.InvalidParameterException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import main.component.chart.Text;

// TDD 두번째.
// 테스트 구현이 어려워지면 코드 품질도 나빠진다.
public class TextTest {
	@Spy Text sut;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFontValueEquality(){
		Font font 				= new Font("Arial Black", Font.PLAIN, 12345);
		Font fontSame 			= new Font("Arial Black", Font.PLAIN, 12345);
		Font fontNotSameOption 	= new Font("Arial Black", Font.BOLD, 12345);
		Font fontNotSameSize 	= new Font("Arial Black", Font.BOLD, 12345);
		
		assertEquals(font, fontSame);
		
		assertNotEquals(font, fontNotSameOption);
		assertNotEquals(font, fontNotSameSize);
	}
	
	@Test
	public void testConstructor(){
		assertEquals(sut.defaultFont, sut.usingFont);
		assertEquals(0, sut.maxTextWidth);
	}
	
	@Test(expected = InvalidParameterException.class)
	public void testSetArea_setArea에넓이0인영역이설정되면Exception을발생시킨다(){
		Rectangle area = new Rectangle(0,0,0,0);
		sut.setArea(area);
	}
	
	@Test(expected = InvalidParameterException.class)
	public void testSetArea_setArea에넓이0인영역이설정되면Exception을발생시킨다2(){
		Rectangle area = new Rectangle(0,0,0,1);
		sut.setArea(area);
	}
	
	@Test(expected = InvalidParameterException.class)
	public void testSetArea_setArea에null이설정되면Exception을발생시킨다(){
		Rectangle area = null;
		sut.setArea(area);
	}
		
	@Test
	public void testSetArea(){
		Rectangle area = new Rectangle(0,0,100,100);
		sut.setArea(area);
		
		assertEquals(area, sut.getArea());
		
		verify(sut, times(1)).resizeUsingFont();
	}
		
	@Test
	public void testResizeTextOnSetText(){
		String str = "test";
		sut.setText(str);
		
		assertEquals(str, sut.getText());
		
		verify(sut, times(1)).resizeUsingFont();
	}
	
	@Test
	public void testResize_어떤_경우에도_텍스트는_area를_넘어서는_안된다(){
		String str = "Text";
		sut.setText(str);
		
		// 리사이즈 시 text와 defaultFont 크기, area를 보고 usingFont 크기를 결정한다.
		// 다른 곳의 폰트와 맞춰야 할 수도 있다.
		// 가로가 막히면 가로, 세로가 막히면 세로가 제한 폭이 된다.
		// area를 표시할 범위가 안되면 표시하지 않는다.
		// 그림자 옵션이 있다.
		
		Rectangle area = new Rectangle(0, 0, 100, 20);
		sut.setArea(area);
		
System.out.println("1 " + sut.maxTextWidth + " " + sut.textHeight);
		assertTrue(sut.textHeight <= (int)area.getHeight());
		assertTrue(sut.maxTextWidth <= (int)area.getWidth());
		
		area.setBounds(0, 0, 100, 10);
		sut.setArea(area);
		
System.out.println("2 " + sut.maxTextWidth + " " + sut.textHeight);
		assertTrue(sut.textHeight <= (int)area.getHeight());
		assertTrue(sut.maxTextWidth <= (int)area.getWidth());
		
		area.setBounds(0, 0, 100, 1);
		sut.setArea(area);
System.out.println("3 " + sut.maxTextWidth + " " + sut.textHeight);		
		assertTrue(sut.textHeight <= (int)area.getHeight());
		assertTrue(sut.maxTextWidth <= (int)area.getWidth());
		
		area.setBounds(0, 0, 50, 20);
		sut.setArea(area);
System.out.println("4 " + sut.maxTextWidth + " " + sut.textHeight);
		assertTrue(sut.textHeight <= (int)area.getHeight());
		assertTrue(sut.maxTextWidth <= (int)area.getWidth());
		
		area.setBounds(0, 0, 1, 20);
		sut.setArea(area);
		
		assertTrue(sut.textHeight <= (int)area.getHeight());
		assertTrue(sut.maxTextWidth <= (int)area.getWidth());
	}
	
	@Test
	public void testResize_결정된폰트크기보다큰폰트를넣었을때area내에있으면안된다(){
		String str = "Text";
		
		sut.setText(str);
		
		Rectangle area = new Rectangle(0, 0, 100, 20);
		sut.setArea(area);

		Font font = new Font(sut.getUsingFont().getFontName(), sut.getUsingFont().getStyle(), sut.getUsingFont().getSize() + 1);
		
		assertTrue(sut.getTextWidth(font) > sut.maxTextWidth || sut.getTextHeight(font) > sut.textHeight);
		
		font = new Font(sut.getUsingFont().getFontName(), sut.getUsingFont().getStyle(), sut.getUsingFont().getSize());
		
		assertFalse(sut.getTextWidth(font) > sut.maxTextWidth || sut.getTextHeight(font) > sut.textHeight);
		
		area = new Rectangle(0, 0, 25, 20);
		sut.setArea(area);
		
		font = new Font(sut.getUsingFont().getFontName(), sut.getUsingFont().getStyle(), sut.getUsingFont().getSize() + 1);
		
		assertTrue(sut.getTextWidth(font) > sut.maxTextWidth || sut.getTextHeight(font) > sut.textHeight);
		
		font = new Font(sut.getUsingFont().getFontName(), sut.getUsingFont().getStyle(), sut.getUsingFont().getSize());
		
		assertFalse(sut.getTextWidth(font) > sut.maxTextWidth || sut.getTextHeight(font) > sut.textHeight);
	}
}
