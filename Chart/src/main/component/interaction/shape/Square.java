package main.component.interaction.shape;

import java.awt.Polygon;
import java.awt.Rectangle;

public class Square extends PlaneShape{
	@Override public void setArea(Rectangle rectangle){
		area = new Polygon();
		area.addPoint(rectangle.x, rectangle.y);
		area.addPoint((int)rectangle.getMaxX(), rectangle.y);
		area.addPoint(rectangle.x, (int)rectangle.getMaxY());
		area.addPoint((int)rectangle.getMaxX(), (int)rectangle.getMaxY());
	}
}
