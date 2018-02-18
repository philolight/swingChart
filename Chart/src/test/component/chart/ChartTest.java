package test.component.chart;

import static org.mockito.Mockito.*;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import main.component.chart.Chart;
import main.component.chart.control.area.AreaControl;
import main.component.chart.control.drawer.DrawerControl;
import main.component.chart.control.flow.FlowControl;
import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;

public class ChartTest extends JPanel{	
	@Mock private DrawerControl drawerControl;
	@Mock private SettingControl settingControl;
	@Mock private FlowControl flowControl;
	@Mock private Theme theme;
	@Mock private AreaControl areaControl;
	@Spy private ChartFake sut;
		
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		
		sut.setSettingControl(settingControl);
		sut.setFlowControl(flowControl);
		sut.setAreaControl(areaControl);
		sut.setDrawerControl(drawerControl);

	}
	
	@Test
	public void testSetTheme() throws Exception{
		sut.setTheme(theme);
		verify(drawerControl).setTheme(theme);
	}
	
	@Test
	public void testSetDependencies() throws Exception{
		sut.setDependencies();
		
		verify(areaControl).setDrawerControl(drawerControl);
		verify(flowControl).setSettingControl(settingControl);
		verify(drawerControl).setSettingControl(settingControl);
		verify(drawerControl).setAreaControl(areaControl);
		verify(drawerControl).setFlowControl(flowControl);
	}
	
	@Test
	public void testSetSize() throws Exception{
		sut.setDependencies();
		sut.setSize(100, 100);

		verify(areaControl).setArea(Mockito.any());
		verify(drawerControl, times(2)).setAreaControl(areaControl);
	}
		
	@Test
	public void testPaint() throws Exception{
		sut.setDependencies();
		sut.update(null);	// g
		
		verify(flowControl).onUpdateData();
		verify(drawerControl).onUpdateData();
		verify(drawerControl).drawAll(Mockito.any());
	}
}

class ChartFake extends Chart{
	@Override
	protected  void init(){}
	
	@Override
	public void setDependencies() {
		super.setDependencies();
	}
}