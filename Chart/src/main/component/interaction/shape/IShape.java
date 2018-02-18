package main.component.interaction.shape;

import java.awt.Polygon;
import java.awt.Rectangle;

public interface IShape {
	public Polygon getArea();
	public void setArea(Rectangle rectangle);
	public boolean contacts(int x, int y);
	public boolean contains(int x, int y);
	public boolean intersects(IShape iShape);
	public void setLocation(int x, int y);	
	public void moveOffset(int x, int y);
}
