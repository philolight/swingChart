package test.component.chart.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.component.chart.data.MetaData;
import main.component.chart.data.calculator.Calculators;

public class TestMetaData{	
	private MetaData<Integer> cut;
	
	@Before
	public void setUp_1부터10까지를list에담은CUT를만든다(){
		cut = new MetaData<Integer>();
		cut.setCalculator(Calculators.INTEGER);
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < 10; i++) list.add(i+1);
		cut.setData(list);
	}
	
	@Test
	public void testGet_1부터10까지중에서인덱스3의값은_4이다(){
		double[] getIndex3 = cut.get(3);
		Assert.assertTrue(4.0 == getIndex3[0]);
	}
	
	@Test
	public void testCalculateGlobalMin_1부터10까지중에서최소값은_1이다(){
		cut.onUpdateData();
		assertTrue(cut.getGlobalMin() == 1);
	}
	
	@Test
	public void testCalculateGlobalMin_1부터10까지중에서최대값은_10이다(){
		cut.onUpdateData();
		assertTrue(cut.getGlobalMax() == 10);
	}
	
	@Test
	public void testCalculateGlobalMin_인덱스5에서8중최대값은_9이다(){
		cut.onUpdateData();
		
		assertEquals(9, cut.getMaxBetween(5, 8), 0.0);
	}
	
	@Test
	public void testCalculateGlobalMin_인덱스3에서5중최소값은_4이다(){
		cut.onUpdateData();
		
		assertEquals(4, cut.getMinBetween(3, 5), 0.0);
	}
	
	@Test
	public void testTimeDouble(){
		List<Double> list = new ArrayList<Double>();
		for(int i = 0; i < 1000000; i++){
			list.add(Math.random() * 1000);
		}
		MetaData<Double> metaData = new MetaData<Double>("", list, Calculators.DOUBLE);
		for(int i = 0; i < 1000000; i++){
			double a = metaData.get(i)[0] * metaData.get(i)[0] + metaData.get(i)[0] - metaData.get(i)[0];
			int b = (int)a;
		}
	}
	
	@Test
	public void testTimeFloat(){
		List<Float> list = new ArrayList<Float>();
		for(int i = 0; i < 1000000; i++){
			list.add((float)(Math.random() * 1000));
		}
		MetaData<Float> metaData = new MetaData<Float>("", list, Calculators.FLOAT);
		for(int i = 0; i < 1000000; i++){
			float a = (float)(metaData.get(i)[0] * metaData.get(i)[0] + metaData.get(i)[0] - metaData.get(i)[0]);
			int b = (int)a;
		}
	}
}