package main.component.interaction;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Stroke;

import main.component.chart.theme.stroke.DefaultStrokeFactory;
import main.component.interaction.linePiece.Directions;
import main.component.interaction.marker.IMark;
import main.component.interaction.marker.RectangleMarkSet;

public class RectanglePiece extends PlanePiece{
	Color lineColor = Color.GRAY;
	Color backgroundColor = Color.WHITE;

	protected int lineWidth = 1;
	protected Stroke stroke = DefaultStrokeFactory.create(lineWidth);
	public void setLineWidth(int lineWidth){
		this.lineWidth = lineWidth;
		stroke = DefaultStrokeFactory.create(lineWidth);
	}
	
	public void setLineColor(Color color){ this.lineColor = color; }
	public void setBackgroundColor(Color color){ this.backgroundColor = color; }
		
	public RectanglePiece(){
		polygon = new Polygon();
		rectangleToPolygon(polygon, area);
		areas.add(polygon);
		markSet = new RectangleMarkSet(area);
		markSet.setPiece(this);
	}
	
	protected void setPolygonByArea(Rectangle rect){
		rectangleToPolygon(polygon, area);
	}
	
	@Override
	public void draw(Graphics2D g) {
		if(backgroundColor != null){
			g.setPaint(backgroundColor);
			g.fillRect(area.x, area.y, area.width, area.height);
		}
		
		g.setPaint(lineColor);
		g.setStroke(stroke);
		g.drawRect(area.x, area.y, area.width, area.height);
	}
	
	@Override
	public void setArea(Rectangle area) {
		super.setArea(area);
	}
	
	@Override
	public boolean contains(int x, int y){
		if(!isSelectable) return false;
		if(markSet.mouseFocused(x, y)) return true;
		return area.contains(x, y);
	}
				
	@Override public final void mouseFocused(int x, int y){
		markSet.mouseFocused(x, y);
	}
	
	@Override public final IMark mousePressed(int x, int y){
		return markSet.mousePressed(x, y);
	}
	
	@Override public final void mouseReleased(int x, int y){
		markSet.mouseReleased(x, y);
	}

	@Override
	public Rectangle getArea() {
		return area; 
	}
}
