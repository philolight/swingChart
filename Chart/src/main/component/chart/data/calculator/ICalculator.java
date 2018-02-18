package main.component.chart.data.calculator;

public interface ICalculator<N extends Number>{
    N zero();
    N value(N a);
    N add(N a, N b);
    N sub(N a, N b);
    N multiplier(N a, N b);
    N divide(N a, N b);
    int compareTo(N a, N b);
    short shortValue(N a);
    int intValue(N a);
    long longValue(N a);
    float floatValue(N a);
    double doubleValue(N a);
}