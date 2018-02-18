package test.component.chart.control.area;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import main.component.chart.control.area.HorizontalPartition;

public class TestHorizontalPartition {
	@Spy HorizontalPartition sut;
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testTitleWidth_titleWidth는width전체와같아야한다() throws Exception{
		int width = 1000;
		int sum = 0;
		sum += sut.getTitleWidth(width);
		
		assertEquals(width, sum);
	}
	
	@Test
	public void testSumOfWidth() throws Exception{
		int width = 1000;
		int sum = 0;
		sum += sut.getLeftLegendWidth(width);
		sum += sut.getLeftAxisWidth(width);
		sum += sut.getDataWidth(width);
		sum += sut.getRightAxisWidth(width);
		sum += sut.getRightLegendWidth(width);
		
		assertEquals(width, sum);
	}
	
	@Test
	public void testCompareDataAndBottomAxisWidth_Data와BottomAxis의Width는같아야한다() throws Exception{
		int width = 1000;
		assertEquals(sut.getDataWidth(width), sut.getBottomAxisWidth(width));
	}
	
	@Test
	public void testSumOfWidth_bottomLegendWidth는width전체와같아야한다() throws Exception{
		int width = 1000;
		int sum = 0;
		sum += sut.getBottomLegendWidth(width);
		
		assertEquals(width, sum);
	}
	
	@Test
	public void testSumOfWidth_footerWidth는width전체와같아야한다() throws Exception{
		int width = 1000;
		int sum = 0;
		sum += sut.getFooterWidth(width);
		
		assertEquals(width, sum);
	}
}
