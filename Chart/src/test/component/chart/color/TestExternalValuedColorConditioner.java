package test.component.chart.color;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import main.component.chart.color.ExternalValuedColorProvider;

public class TestExternalValuedColorConditioner {
	ExternalValuedColorProvider sut;
	List<Integer> indexValue = null;
	
	
	@Before
	public void setUp(){
		sut = new ExternalValuedColorProvider();
		List<Integer> indexValue = new ArrayList<Integer>();
		indexValue.add(2);
		indexValue.add(1);
		indexValue.add(3);
	}
	
	public void testGetColor(){
		
	}
}
