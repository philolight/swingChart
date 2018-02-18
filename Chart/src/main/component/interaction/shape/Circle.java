package main.component.interaction.shape;

import java.awt.Polygon;
import java.awt.Rectangle;

public class Circle extends PlaneShape{
	@Override public void setArea(Rectangle rectangle){
		// 타원 x*x / r1*r1 + y*y / r2*r2 = 1
		double r1 = rectangle.width / 2.0;
		double r2 = rectangle.height / 2.0;
		
		// 직선 y = ax + b
		double a = rectangle.width / rectangle.height;
		double b = - rectangle.width / 2;
		
//		int sin45OfHalfWidth = (int)(Math.sin(Math.PI / 4.0);
		
		area = new Polygon();
		area.addPoint((int)rectangle.getCenterX(), rectangle.y); 				// TOP
		area.addPoint((int)rectangle.getCenterX(), rectangle.y); 				// RIGHT-TOP
		area.addPoint((int)rectangle.getMaxX(), (int)rectangle.getCenterY()); 	// RIGHT
		
		area.addPoint((int)rectangle.getCenterX(), (int)rectangle.getMaxY());	// BOTTOM

		area.addPoint((int)rectangle.x, (int)rectangle.getCenterY());			// LEFT
	}
}
