package main.component.chart.data.calculator;

/** Generic 클래스 내부에서 수와 연관된 데이터 타입들의 연산이 자유롭지 못하기 때문에  
 * 
 * */

public class Calculators{
    public static final ShortCalculator SHORT = new ShortCalculator();
    public static final IntegerCalculator INTEGER = new IntegerCalculator();
    public static final LongCalculator 	LONG = new LongCalculator();
    public static final FloatCalculator FLOAT = new FloatCalculator();
    public static final DoubleCalculator DOUBLE = new DoubleCalculator();
}

class ShortCalculator implements ICalculator<Short>{
    @Override public Short zero() { return 0; }
    @Override public Short value(Short a) { return a; }
    @Override public Short add(Short a, Short b) { return (short)(a + b); }
    @Override public Short sub(Short a, Short b) { return (short)(a - b); }
    @Override public Short multiplier(Short a, Short b) { return (short)(a * b); }
    @Override public Short divide(Short a, Short b) { return (short)(a / b); }
    @Override public int compareTo(Short a, Short b) { return (a > b) ? -1 : (a == b) ? 0 : 1;}
    @Override public short shortValue(Short a){ return a.shortValue(); }
    @Override public int intValue(Short a){ return a.intValue(); }
    @Override public long longValue(Short a){ return a.longValue(); }
    @Override public float floatValue(Short a){ return a.floatValue(); }
    @Override public double doubleValue(Short a){ return a.doubleValue(); }
}

class IntegerCalculator implements ICalculator<Integer>{
    @Override public Integer zero() { return 0; }
    @Override public Integer value(Integer a) { return a; }
    @Override public Integer add(Integer a, Integer b) { return a + b; }
    @Override public Integer sub(Integer a, Integer b) { return a - b; }
    @Override public Integer multiplier(Integer a, Integer b) { return a * b; }
    @Override public Integer divide(Integer a, Integer b) { return a / b; }
    @Override public int compareTo(Integer a, Integer b) { return (a > b) ? -1 : (a == b) ? 0 : 1;}
    @Override public short shortValue(Integer a){ return a.shortValue(); }
    @Override public int intValue(Integer a){ return a.intValue(); }
    @Override public long longValue(Integer a){ return a.longValue(); }
    @Override public float floatValue(Integer a){ return a.floatValue(); }
    @Override public double doubleValue(Integer a){ return a.doubleValue(); }
}

class LongCalculator implements ICalculator<Long>{
    @Override public Long zero() { return 0L; }
    @Override public Long value(Long a) { return a; }
    @Override public Long add(Long a, Long b) { return a + b; }
    @Override public Long sub(Long a, Long b) { return a - b; }
    @Override public Long multiplier(Long a, Long b) { return a * b; }
    @Override public Long divide(Long a, Long b) { return a / b; }
    @Override public int compareTo(Long a, Long b) { return (a > b) ? -1 : (a == b) ? 0 : 1;}
    @Override public short shortValue(Long a){ return a.shortValue(); }
    @Override public int intValue(Long a){ return a.intValue(); }
    @Override public long longValue(Long a){ return a.longValue(); }
    @Override public float floatValue(Long a){ return a.floatValue(); }
    @Override public double doubleValue(Long a){ return a.doubleValue(); }
}

class FloatCalculator implements ICalculator<Float>{
    @Override public Float zero() { return 0.0f; }
    @Override public Float value(Float a) { return a; }
    @Override public Float add(Float a, Float b) { return a + b; }
    @Override public Float sub(Float a, Float b) { return a - b; }
    @Override public Float multiplier(Float a, Float b) { return a * b; }
    @Override public Float divide(Float a, Float b) { return a / b; }
    @Override public int compareTo(Float a, Float b) { return (a > b) ? -1 : (a == b) ? 0 : 1;}
    @Override public short shortValue(Float a){ return a.shortValue(); }
    @Override public int intValue(Float a){ return a.intValue(); }
    @Override public long longValue(Float a){ return a.longValue(); }
    @Override public float floatValue(Float a){ return a.floatValue(); }
    @Override public double doubleValue(Float a){ return a.doubleValue(); }
}

class DoubleCalculator implements ICalculator<Double>{
    @Override public Double zero() { return 0.0; }
    @Override public Double value(Double a) { return a; }
    @Override public Double add(Double a, Double b) { return a + b; }
    @Override public Double sub(Double a, Double b) { return a - b; }
    @Override public Double multiplier(Double a, Double b) { return a * b; }
    @Override public Double divide(Double a, Double b) { return a / b; }
    @Override public int compareTo(Double a, Double b) { return (a > b) ? -1 : (a == b) ? 0 : 1;}
    @Override public short shortValue(Double a){ return a.shortValue(); }
    @Override public int intValue(Double a){ return a.intValue(); }
    @Override public long longValue(Double a){ return a.longValue(); }
    @Override public float floatValue(Double a){ return a.floatValue(); }
    @Override public double doubleValue(Double a){ return a.doubleValue(); }
}
