package test.component.chart.control.area;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestRectangle {
	private Rectangle sut;
	
	@Before
	public void setUp(){
		sut = Mockito.spy(Rectangle.class);
	}
	
	@Test
	public void testRectangleValueEquality_Rectangle객체는값으로동치성을판단해야한다(){
		Rectangle area 		= new Rectangle(12345,56789,12345,56789);
		Rectangle areaSame 	= new Rectangle(12345,56789,12345,56789);
		
		assertEquals(area, areaSame);
	}
	
	@Test
	public void testEmpty(){
		sut.setBounds(0,0,0,0);
		
		assertTrue(sut.isEmpty());
	}
	
	@Test
	public void testRectangleIntersects(){
		Rectangle area1 = new Rectangle(-10, -10, 1, 1);
		Rectangle area2 = new Rectangle(-10, -10, 1, 1);
		
		assertTrue(area1.intersects(area2));
	}
	
	@Test
	public void testMinusArea(){
		sut.setBounds(-1, -1, 0, 0);
		
		assertFalse(sut.isEmpty());
		
		assertEquals((int)((double)-1 + (double)0/2.0), sut.getCenterX());
	}
}
