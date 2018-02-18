package main.component.interaction.shape;

import java.awt.Polygon;

abstract public class PlaneShape implements IShape{
	protected Polygon area = new Polygon();
	
	@Override
	public Polygon getArea() {
		return area;
	}
	
	@Override public boolean contacts(int x, int y){
		int[] xPoints = area.xpoints;
		int[] yPoints = area.ypoints;
		
		for(int i = 0; i < xPoints.length; i++){
			if(distance(x, y, xPoints[i], yPoints[i]) < 2.0) return true;
		}
		
		return false;
	}
	
	@Override public boolean contains(int x, int y){
		return area.contains(x, y);
	}
	
	@Override public boolean intersects(IShape iShape){
		int[] xPoints = iShape.getArea().xpoints;
		int[] yPoints = iShape.getArea().ypoints;
		
		for(int i = 0; i < xPoints.length; i++){
			if(contains(xPoints[i], yPoints[i])) return true;
		}
		
		return false;
	}
	
	@Override public void setLocation(int x, int y){
		int xDifference = x - getMin(area.xpoints);
		int yDifference = y - getMin(area.ypoints);
		
		moveOffset(xDifference, yDifference);
	}
	
	@Override public void moveOffset(int x, int y){
		Polygon newArea = new Polygon();
		
		int[] xPoints = area.xpoints;
		int[] yPoints = area.ypoints;
				
		for(int i = 0; i < xPoints.length; i++){
			newArea.addPoint(x + xPoints[i], y + yPoints[i]);
		}
	}
	
	private int getMin(int[] array){
		if(array.length == 0) return -1;
		
		int min = array[0];
		for(int i = 1 ; i < array.length; i++){
			if(array[i] < min) min = array[i];
		}
		
		return min;
	}
	
	private double distance(int x1, int y1, int x2, int y2){
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}
}
