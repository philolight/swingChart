package test.component.chart.control.area;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Rectangle;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import main.component.chart.control.area.AreaControl;
import main.component.chart.control.area.HorizontalPartition;
import main.component.chart.control.area.VerticalPartition;
import main.component.chart.control.drawer.DrawerControl;
import main.component.chart.control.flow.FlowControl;
import main.component.chart.setting.SettingControl;

public class TestAreaControl extends TestAreaInfo{
	@Spy private SpyAreaControl sut;
	
	@Spy private SettingControl settingControl;
	@Spy private FlowControl flowControl;
	private DrawerControl drawerControl;
	@Spy private VerticalPartition verticalPartition;
	@Spy private HorizontalPartition horizontalPartition;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		drawerControl = spy(new DrawerControl(flowControl));
		drawerControl.setSettingControl(settingControl);
		drawerControl.setAreaControl(sut);		
		sut.setDrawerControl(drawerControl);
	}
	
	@Test
	public void testSetArea(){
		sut.setArea(new Rectangle(0, 0, 1000, 1000));
		
		verify(sut).onAreaChange();
	}

	@Test
	public void testSetVerticalPartition() throws Exception{
		sut.setVerticalPartition(verticalPartition);
		
		verify(sut).onAreaChange();		
	}
	
	@Test
	public void testSetHorizontalPartition() throws Exception{
		sut.setHorizontalPartition(horizontalPartition);
		
		verify(sut).onAreaChange();
	}
	
	@Test
	public void testOnAreaChange() throws Exception{
		sut.onAreaChange();
		
		verify(sut).calculatePartitionAreas();
		verify(drawerControl, Mockito.times(2)).setAreaControl(sut);
		
		verify(sut).calculateTitleArea();
		verify(sut).calculateLeftLegendArea();
		verify(sut).calculateLeftAxisArea();
		verify(sut).calculateDataArea();
		verify(sut).calculateRightAxisArea();
		verify(sut).calculateRightLegendArea();
		verify(sut).calculateBottomAxisArea();
		verify(sut).calculateBottomLegendArea();
		verify(sut).calculateFooterArea();
	}
	
	@Test
	public void testCalculateTitleArea() throws Exception{
		sut.setVerticalPartition(verticalPartition);
		sut.setHorizontalPartition(horizontalPartition);
		sut.onAreaChange();
		
		verify(horizontalPartition, atLeast(1)).getTitleX(sut.left, sut.width);
		verify(verticalPartition, atLeast(1)).getTitleY(sut.top, sut.height);
		verify(horizontalPartition, atLeast(1)).getTitleWidth(sut.width);
		verify(verticalPartition, atLeast(1)).getTitleHeight(sut.height);		
	}
	
	@Test
	public void testCalculateLeftLegendArea() throws Exception{
		sut.setVerticalPartition(verticalPartition);
		sut.setHorizontalPartition(horizontalPartition);
		sut.onAreaChange();
		
		verify(horizontalPartition, atLeast(1)).getLeftLegendX(sut.left, sut.width);
		verify(verticalPartition, atLeast(1)).getLeftLegendY(sut.top, sut.height);
		verify(horizontalPartition, atLeast(1)).getLeftLegendWidth(sut.width);
		verify(verticalPartition, atLeast(1)).getLeftLegendHeight(sut.height);
	}
	
	@Test
	public void testCalculateLeftAxisArea() throws Exception{
		sut.setVerticalPartition(verticalPartition);
		sut.setHorizontalPartition(horizontalPartition);
		sut.onAreaChange();
		
		verify(horizontalPartition, atLeast(1)).getLeftAxisX(sut.left, sut.width);
		verify(verticalPartition, atLeast(1)).getLeftAxisY(sut.top, sut.height);
		verify(horizontalPartition, atLeast(1)).getLeftAxisWidth(sut.width);
		verify(verticalPartition, atLeast(1)).getLeftAxisHeight(sut.height);
	}
	
	@Test
	public void testCalculateDataArea() throws Exception{
		sut.setVerticalPartition(verticalPartition);
		sut.setHorizontalPartition(horizontalPartition);
		sut.onAreaChange();
		
		verify(horizontalPartition, atLeast(1)).getDataX(sut.left, sut.width);
		verify(verticalPartition, atLeast(1)).getDataY(sut.top, sut.height);
		verify(horizontalPartition, atLeast(1)).getDataWidth(sut.width);
		verify(verticalPartition, atLeast(1)).getDataHeight(sut.height);
	}
	
	@Test
	public void testCalculateRightAxisArea() throws Exception{
		sut.setVerticalPartition(verticalPartition);
		sut.setHorizontalPartition(horizontalPartition);
		sut.onAreaChange();
		
		verify(horizontalPartition, atLeast(1)).getRightAxisX(sut.left, sut.width);
		verify(verticalPartition, atLeast(1)).getRightAxisY(sut.top, sut.height);
		verify(horizontalPartition, atLeast(1)).getRightAxisWidth(sut.width);
		verify(verticalPartition, atLeast(1)).getRightAxisHeight(sut.height);
	}
	
	@Test
	public void testCalculateRightLegendArea() throws Exception{
		sut.setVerticalPartition(verticalPartition);
		sut.setHorizontalPartition(horizontalPartition);
		sut.onAreaChange();
		
		verify(horizontalPartition, atLeast(1)).getRightLegendX(sut.left, sut.width);
		verify(verticalPartition, atLeast(1)).getRightLegendY(sut.top, sut.height);
		verify(horizontalPartition, atLeast(1)).getRightLegendWidth(sut.width);
		verify(verticalPartition, atLeast(1)).getRightLegendHeight(sut.height);
	}

	@Test
	public void testCalculateBottomAxisArea() throws Exception{
		sut.setVerticalPartition(verticalPartition);
		sut.setHorizontalPartition(horizontalPartition);
		sut.onAreaChange();
		
		verify(horizontalPartition, atLeast(1)).getBottomAxisX(sut.left, sut.width);
		verify(verticalPartition, atLeast(1)).getBottomAxisY(sut.top, sut.height);
		verify(horizontalPartition, atLeast(1)).getBottomAxisWidth(sut.width);
		verify(verticalPartition, atLeast(1)).getBottomAxisHeight(sut.height);
	}

	@Test
	public void testCalculateBottomLegendArea() throws Exception{
		sut.setVerticalPartition(verticalPartition);
		sut.setHorizontalPartition(horizontalPartition);
		sut.onAreaChange();
		
		verify(horizontalPartition, atLeast(1)).getBottomLegendX(sut.left, sut.width);
		verify(verticalPartition, atLeast(1)).getBottomLegendY(sut.top, sut.height);
		verify(horizontalPartition, atLeast(1)).getBottomLegendWidth(sut.width);
		verify(verticalPartition, atLeast(1)).getBottomLegendHeight(sut.height);
	}

	@Test
	public void testCalculateFooterAreaArea() throws Exception{
		sut.setVerticalPartition(verticalPartition);
		sut.setHorizontalPartition(horizontalPartition);
		sut.onAreaChange();
		
		verify(horizontalPartition, atLeast(1)).getFooterX(sut.left, sut.width);
		verify(verticalPartition, atLeast(1)).getFooterY(sut.top, sut.height);
		verify(horizontalPartition, atLeast(1)).getFooterWidth(sut.width);
		verify(verticalPartition, atLeast(1)).getFooterHeight(sut.height);
	}
	
	@Test
	public void testSameY_LeftLegendLeftAxisDataRightAxisRightLegend의Y값은_같아야한다() throws Exception{
		sut.setVerticalPartition(verticalPartition);
		sut.setHorizontalPartition(horizontalPartition);
		sut.onAreaChange();
		
		Rectangle leftLegend = sut.getLeftLegendArea();
		Rectangle leftAxis = sut.getLeftAxisArea();
		Rectangle data = sut.getDataArea();
		Rectangle rightAxis = sut.getRightAxisArea();
		Rectangle rightLegend = sut.getRightLegendArea();
		
		assertEquals(leftLegend.y, leftAxis.y);
		assertEquals(leftLegend.y, data.y);
		assertEquals(leftLegend.y, rightAxis.y);
		assertEquals(leftLegend.y, rightLegend.y);
	}
	
	@Test
	public void testSameY_LeftLegendLeftAxisDataRightAxisRightLegend의Height값은_같아야한다() throws Exception{
		sut.setVerticalPartition(verticalPartition);
		sut.setHorizontalPartition(horizontalPartition);
		sut.onAreaChange();
		
		Rectangle leftLegend = sut.getLeftLegendArea();
		Rectangle leftAxis = sut.getLeftAxisArea();
		Rectangle data = sut.getDataArea();
		Rectangle rightAxis = sut.getRightAxisArea();
		Rectangle rightLegend = sut.getRightLegendArea();
		
		assertEquals(leftLegend.height, leftAxis.height);
		assertEquals(leftLegend.height, data.height);
		assertEquals(leftLegend.height, rightAxis.height);
		assertEquals(leftLegend.height, rightLegend.height);
	}
	
	@Test
	public void testSameY_Title과LeftLegend와BottomLegend와Footer의X값은_같아야한다() throws Exception{
		sut.setVerticalPartition(verticalPartition);
		sut.setHorizontalPartition(horizontalPartition);
		sut.onAreaChange();
		
		Rectangle title = sut.getTitleArea();
		Rectangle leftLegend = sut.getLeftLegendArea();
		Rectangle bottomLegend = sut.getBottomLegendArea();
		Rectangle footer = sut.getFooterArea();
		
		assertEquals(title.x, leftLegend.x);
		assertEquals(title.x, bottomLegend.x);
		assertEquals(title.x, footer.x);
	}
}

class SpyAreaControl extends AreaControl{
	@Override public void onAreaChange() { super.onAreaChange(); }
	@Override public void calculatePartitionAreas() { super.calculatePartitionAreas(); }	
	@Override public void calculateTitleArea() { super.calculateTitleArea(); }
	@Override public void calculateLeftLegendArea() {super.calculateLeftLegendArea();};
	@Override public void calculateLeftAxisArea() {super.calculateLeftAxisArea();};
	@Override public void calculateDataArea() {super.calculateDataArea();};
	@Override public void calculateRightAxisArea() {super.calculateRightAxisArea();};
	@Override public void calculateRightLegendArea() {super.calculateRightLegendArea();};
	@Override public void calculateBottomAxisArea() {super.calculateBottomAxisArea();};
	@Override public void calculateBottomLegendArea() {super.calculateBottomLegendArea();};
	@Override public void calculateFooterArea() {super.calculateFooterArea(); }
}