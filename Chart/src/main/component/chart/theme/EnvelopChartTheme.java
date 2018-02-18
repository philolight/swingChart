package main.component.chart.theme;

import java.awt.Stroke;

import main.component.chart.theme.stroke.DefaultStrokeFactory;

public class EnvelopChartTheme {
	public int lineWidth = 1;
	public Stroke stroke = DefaultStrokeFactory.create(lineWidth);
}
