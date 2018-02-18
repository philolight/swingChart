package test.component.chart.data.calculator;

import static org.junit.Assert.*;

import org.junit.Test;

import main.component.chart.data.calculator.Calculators;
import main.component.chart.data.calculator.ICalculator;

/** Generic 클래스 내부에서 수와 연관된 데이터 타입들의 연산이 자유롭지 못하기 때문에  
 * 
 * */

public class TestCalculators{    
	double delta(double n){return n;}
	
    @Test
    public void testCalculatorShort(){
    	ICalculator<Short> sut = Calculators.SHORT;
    	assertEquals("zero()", (short)0, (short)sut.zero());
    	assertEquals("1 = 1", (short)1, (short)sut.value((short)1) );
    	assertEquals("1+1=2", (short)2, (short)sut.add((short)1, (short)1) );
    	assertEquals("1-1=0", (short)0, (short)sut.sub((short)1, (short)1) );
    	assertEquals("2*2=4", (short)4, (short)sut.multiplier((short)2, (short)2));
    	assertEquals("2/2=1", (short)1, (short)sut.divide((short)2, (short)2));    	
    }
    
    @Test
    public void testCalculatorInt(){
    	ICalculator<Integer> sut = Calculators.INTEGER;
    	assertEquals("zero()", (int)0, (int)sut.zero());
    	assertEquals("1 = 1", (int)1, (int)sut.value(1) );
    	assertEquals("1+1=2", (int)2, (int)sut.add(1, 1) );
    	assertEquals("1-1=0", (int)0, (int)sut.sub(1, 1) );
    	assertEquals("2*2=4", (int)4, (int)sut.multiplier(2, 2));
    	assertEquals("2/2=1", (int)1, (int)sut.divide(2, 2));    	
    }
    
    @Test
    public void testCalculatorLong(){
    	ICalculator<Long> sut = Calculators.LONG;
    	assertEquals("zero()", (long)0, (long)sut.zero());
    	assertEquals("1 = 1", (long)1, (long)sut.value((long)1) );
    	assertEquals("1+1=2", (long)2, (long)sut.add((long)1, (long)1) );
    	assertEquals("1-1=0", (long)0, (long)sut.sub((long)1, (long)1) );
    	assertEquals("2*2=4", (long)4, (long)sut.multiplier((long)2, (long)2));
    	assertEquals("2/2=1", (long)1, (long)sut.divide((long)2, (long)2));    	
    }
    
    @Test
    public void testCalculatorFloat(){
    	ICalculator<Float> sut = Calculators.FLOAT;
    	assertEquals("zero()", (float)0, (float)sut.zero(), delta(0.0));
    	assertEquals("1 = 1", (float)1, (float)sut.value((float)1) , delta(0.0));
    	assertEquals("1+1=2", (float)2, (float)sut.add((float)1, (float)1) , delta(0.0));
    	assertEquals("1-1=0", (float)0, (float)sut.sub((float)1, (float)1) , delta(0.0));
    	assertEquals("2*2=4", (float)4, (float)sut.multiplier((float)2, (float)2), delta(0.0));
    	assertEquals("2/2=1", (float)1, (float)sut.divide((float)2, (float)2), delta(0.0));    	
    }
    
    @Test
    public void testCalculatorDouble(){
    	ICalculator<Double> sut = Calculators.DOUBLE;
    	assertEquals("zero()", (double)0, (double)sut.zero(), delta(0.0));
    	assertEquals("1 = 1", (double)1, (double)sut.value((double)1) , delta(0.0));
    	assertEquals("1+1=2", (double)2, (double)sut.add((double)1, (double)1) , delta(0.0));
    	assertEquals("1-1=0", (double)0, (double)sut.sub((double)1, (double)1) , delta(0.0));
    	assertEquals("2*2=4", (double)4, (double)sut.multiplier((double)2, (double)2), delta(0.0));
    	assertEquals("2/2=1", (double)1, (double)sut.divide((double)2, (double)2), delta(0.0));
    }
}
