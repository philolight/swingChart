package main.component.chart.theme.gradient;

import java.awt.Color;
import java.awt.LinearGradientPaint;
import java.awt.geom.Point2D;

public class TwoToneGradientFactory {
	public static LinearGradientPaint create(Color startColor, Color endColor, int top, int height){
		float[] dist = {0.0f, 1.0f};
	    Point2D start = new Point2D.Float(0, top);
	    Point2D end = new Point2D.Float(0, top + height);
		Color colors[] = {startColor, endColor};
		LinearGradientPaint gradient = new LinearGradientPaint(start, end, dist, colors);
		
		return gradient;
	}
}
