package main.component.chart.theme.stroke;

import java.awt.BasicStroke;
import java.awt.Stroke;

public class DefaultStrokeFactory {
	public static Stroke create(int lineWidth){
		return new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 3.0f, null, 0.0f);
	}
}
