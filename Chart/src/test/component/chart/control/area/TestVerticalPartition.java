package test.component.chart.control.area;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import main.component.chart.control.area.VerticalPartition;

public class TestVerticalPartition {
	@Spy VerticalPartition sut;
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSameHeight_dataHeight는leftAxis와leftLegend와rightAxis와rightLegend와같아야한다() throws Exception{
		int height = 1000;
		
		assertEquals(sut.getDataHeight(height), sut.getLeftAxisHeight(height));
		assertEquals(sut.getDataHeight(height), sut.getLeftLegendHeight(height));
		assertEquals(sut.getDataHeight(height), sut.getRightAxisHeight(height));
		assertEquals(sut.getDataHeight(height), sut.getRightLegendHeight(height));
	}
	
	@Test
	public void testSumOfHeight() throws Exception{
		int height = 1000;
		int sum = 0;
		sum += sut.getTitleHeight(height);
		sum += sut.getGapBetweenTitleAndDataHeight(height);
		sum += sut.getDataHeight(height);
		sum += sut.getBottomAxisHeight(height);
		sum += sut.getBottomLegendHeight(height);
		sum += sut.getFooterHeight(height);
		
		assertEquals(height, sum);
	}
}
