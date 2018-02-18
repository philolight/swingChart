package main.component.interaction;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.component.chart.theme.stroke.DefaultStrokeFactory;
import main.component.interaction.linePiece.Directions;
import main.component.interaction.marker.IMark;
import main.component.interaction.marker.IMarkSet;
import main.component.interaction.marker.LineMarkSet;
import main.component.interaction.shape.LineType;

public class LinePiece implements IPiece{
	protected boolean isSelectable = true;
	protected boolean isMoveable = true;
			
	protected Point2D start;
	protected Point2D end;
		
	protected List<Connection> connections = new ArrayList<Connection>();
	
	protected Rectangle area = null;
	protected Polygon polygon = null;
	protected List<Polygon> areas = new ArrayList<Polygon>();
	
	protected IMarkSet markSet = null;
	
	protected List<Point2D> points = new ArrayList<Point2D>();
	protected List<Directions> directions = new ArrayList<Directions>();

	protected int lineWidth = 1;
	protected Stroke stroke = DefaultStrokeFactory.create(lineWidth);
	protected Stroke focusStroke = DefaultStrokeFactory.create(1);
	protected Color lineColor = Color.BLACK;
	
	public void setLineWidth(int lineWidth){
		this.lineWidth = lineWidth;
		stroke = DefaultStrokeFactory.create(lineWidth);
	}
	public void setColor(Color lineColor){ this.lineColor = lineColor; }
	
	public LinePiece(){
		init();
	}
	
	protected void init(){
		start = new Point2D.Float(0,0);
		end = new Point2D.Float(0,0);
		
		area = new Rectangle(0,0,0,0);
		
		polygon = new Polygon();
		areas.add(polygon);
		
		markSet = new LineMarkSet();
		markSet.setPiece(this);
		
		setStartPoint(0,0);
		setEndPoint(0,0);
	}
	
	protected void lineToPolygon(Polygon polygon, int x1, int y1, int x2, int y2){
		if(x1 == x2 && y1 == y2) return;
		
		float a = (y2 - y1) == 0 ? 2.0f : (float)(x2 - x1) / (float)(y2 - y1);
		int xOffset = (a * a) > 1.0f ? 0 : 2;
		int yOffset = (a * a) > 1.0f ? 2 : 0;
		
		a = a > 0 ? 1.0f : -1.0f;
		
		polygon.reset();		
		
		polygon.addPoint((int)(x1 + xOffset * a), (int)(y1 + yOffset * a));
		polygon.addPoint((int)(x2 + xOffset * a), (int)(y2 + yOffset * a));
		polygon.addPoint((int)(x2 - xOffset * a), (int)(y2 - yOffset * a));
		polygon.addPoint((int)(x1 - xOffset * a), (int)(y1 - yOffset * a));
	}
	
	protected void lineToArea(Rectangle rect, int x1, int y1, int x2, int y2){
		if(x1 == x2 && y1 == y2) return;
		area.x = Math.min(x1,  x2);
		area.y = Math.min(y1,  y2);
		area.width = Math.abs(x2 - x1);
		area.height = Math.abs(y2 - y1);
	}

	public Point2D getStartPoint(){ return start;}
	public Point2D getEndPoint(){ return end;}
	
	public void setStartPoint(int x, int y){
		start.setLocation(x, y);
		lineToPolygon(polygon, (int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
		lineToArea(area, (int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
		markSet.reposition();
	}
	
	public void setEndPoint(int x, int y){ 
		end.setLocation(x, y);
		lineToPolygon(polygon, (int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
		lineToArea(area, (int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
		markSet.reposition();
	}

	@Override
	public void draw(Graphics2D g){
		g.setStroke(stroke);
		g.setPaint(lineColor);
		g.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
	}

	public void onResize(PlanePiece planePiece){
		for(Connection each : connections){
			if(each.planePiece.equals(planePiece)){
				switch(each.lineMark.getDirection()){
				case LINE_START:
					setStartPoint(each.planeMark.getX(), each.planeMark.getY());
					break;
				case LINE_END:
					setEndPoint(each.planeMark.getX(), each.planeMark.getY());
					break;
				default :
					break;
				}
			}
		}
	}
	
	@Override
	public IMark onDragged(int fromX, int fromY, int toX, int toY, IMark mark){
		if(!isMoveable) return null;
			
		if(mark == null || !mark.getDirection().isLine()){
			setStartPoint((int)(start.getX() + (toX - fromX)), (int)(start.getY() + (toY - fromY)));
			setEndPoint((int)(end.getX() + (toX - fromX)), (int)(end.getY() + (toY - fromY)));
		}
		else markSet.mouseDragged(fromX, fromY, toX, toY, mark);
		
		return mark;
	}

	@Override
	public boolean contains(int x, int y) {
		if(!isSelectable) return false;
		if(markSet.mouseFocused(x, y)) return true;
		return polygon.contains(x, y);
	}
	
	@Override
	public Rectangle getArea() {
		return area;
	}

	@Override
	public List<Polygon> getAreas() {
		return areas;
	}

	@Override
	public boolean intersects(Rectangle rect) {
		return Intersect.intersects(areas, rect);
	}

	@Override
	public void drawFocus(Graphics2D g) {
		if(!isSelectable) return;
		g.setPaint(Color.YELLOW);
		g.setStroke(focusStroke);
		g.drawPolygon(polygon);
	}
	
	@Override public void drawSelectedMark(Graphics2D g, int x, int y){
		if(!isSelectable || !isMoveable) return;
		markSet.drawMark(g, x, y);
	}
	
	@Override
	public void drawConnectableMark(Graphics2D g, int x, int y) {
			markSet.drawConnectableMark(g, x, y);
	}
	
	public boolean isMoveable(){ return isMoveable; }
	public void setMoveable(boolean isMoveable){ this.isMoveable = isMoveable; }
	
	public boolean isSelectable(){ return isSelectable; }
	public void setSelectable(boolean isSelectable){ this.isSelectable = isSelectable; }
	
	@Override public void mouseFocused(int x, int y){
		mouseFocused(x, y);
	}
	
	@Override public IMark mousePressed(int x, int y){
		return markSet.mousePressed(x, y);
	}
	
	@Override public void mouseReleased(int x, int y){
		markSet.mouseReleased(x, y);
		
		for(int i = connections.size() - 1; i >= 0; i--){
			Connection connection = connections.get(i);
			if(connection.lineMark.getX() != connection.planeMark.getX() ||
					connection.lineMark.getY() != connection.planeMark.getY()
					){
				connection.planePiece.removeConnection(connection);
				connections.remove(connection);
			}
		}
	}

	@Override
	public boolean intersects(IPiece piece) {
		return Intersect.intersects(areas, piece.getAreas());
	}

	@Override
	public boolean intersects(List<Polygon> areas) {
		return Intersect.intersects(this.areas, areas);
	}

	@Override
	public boolean contains(IPiece piece) {
		return false;
	}

	@Override
	public boolean contains(List<Polygon> polygonList) {
		return false;
	}
	
	@Override public void setConnection(Connection connection){
		// 중복 체크
		for(Connection each : connections){
			if(each.linePiece.equals(connection.lineMark) && each.planeMark.equals(connection.planeMark)) return;
		}
		connections.add(connection);
	}
	
	public List<IPiece> getConnectedPieces(){
		if(connections.size() == 0) return Collections.emptyList();
		
		List<IPiece> list = new ArrayList<IPiece>();
		
		for(Connection each : connections){
			list.add(each.planePiece);
		}		
		
		return list;
	}
	
	@Override public IMark getConnectableMark(int x, int y){
		return markSet.getConnectableMark(x, y);
	}
	
	@Override public IMark getMark(Directions direction){
		return markSet.getMark(direction);
	}
}
