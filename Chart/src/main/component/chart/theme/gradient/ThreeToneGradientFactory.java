package main.component.chart.theme.gradient;

import java.awt.Color;
import java.awt.LinearGradientPaint;
import java.awt.geom.Point2D;

public class ThreeToneGradientFactory {
	public static LinearGradientPaint create(Color startColor, Color endColor, int top, int height){
		float[] dist = {0.0f, 0.5f, 1.0f};
	    Point2D start = new Point2D.Float(0, top);
	    Point2D end = new Point2D.Float(0, top + height);
		Color colors[] = {startColor, endColor, startColor};
		LinearGradientPaint gradient = new LinearGradientPaint(start, end, dist, colors);
		
		return gradient;
	}
}
