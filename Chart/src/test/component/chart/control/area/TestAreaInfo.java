package test.component.chart.control.area;

import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import main.component.chart.control.area.AreaInfo;

public class TestAreaInfo {
	private AreaInfo sut;
	
	@Before
	public void setUp(){
		sut = Mockito.spy(AreaInfo.class);
	}
	
	@Test
	public void testSetArea(){
		Rectangle area = new Rectangle(0,0,0,0);
	}
}
