package main.component.interaction.marker;

import java.awt.Color;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import main.component.chart.theme.Theme;
import main.component.chart.theme.stroke.DefaultStrokeFactory;
import main.component.interaction.IPiece;
import main.component.interaction.linePiece.Directions;

abstract public class Mark implements IMark{
	protected int lineWidth = 1;
	protected Stroke stroke = DefaultStrokeFactory.create(lineWidth);
	protected Color markColor = Color.BLACK;
	protected Color focusFillColor = Color.RED;
	protected int size = 7;
	protected boolean isSelected = false;
	
	IPiece piece = null;
	
	Directions direction = Directions.NONE;
	
	Point2D point = new Point2D.Float();
	
	@Override public int getX(){ return (int)point.getX(); }
	@Override public int getY(){ return (int)point.getY(); }
	@Override public void setPosition(int x, int y){
		point.setLocation(x, y);
	}
		
	@Override public boolean isSelected(){ return isSelected; }
	
	@Override public Directions getDirection(){ return direction; }
	@Override public void 		setDirection(Directions direction){ this.direction = direction; }
	
	@Override public boolean mouseFocused(int x, int y){
		return point.distance(x, y) < size / 2;
	}
		
	@Override
	public void mouseDragged(int toX, int toY, Directions direction){
		int destinationX = direction.isMovableX() ? toX : getX();
		int destinationY = direction.isMovableY() ? toY : getY();
		setPosition(destinationX, destinationY);
	}
	
	@Override public void mousePressed(int x, int y){
		isSelected = mouseFocused(x, y);
	}
	
	@Override public void mouseReleased(){
		isSelected = false;
	}
	
	public int getSize() { return size; }
	public void setSize(int size) { this.size = size; }
	
	public void setTheme(Theme theme){
		markColor = theme.mark.markColor;
		focusFillColor = theme.mark.focusFillColor;
		lineWidth = theme.mark.lineWidth;
		stroke = DefaultStrokeFactory.create(lineWidth);
		size = theme.mark.size;
	}
}
