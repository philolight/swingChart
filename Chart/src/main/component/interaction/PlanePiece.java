package main.component.interaction;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.component.chart.theme.stroke.DefaultStrokeFactory;
import main.component.interaction.linePiece.Directions;
import main.component.interaction.marker.IMark;
import main.component.interaction.marker.IMarkSet;

abstract public class PlanePiece implements IPiece{
	private static int EMPTY_SIZE = 0;
	
	protected boolean isSelectable = true;
	protected boolean isMoveable = true;
	protected IMarkSet markSet = null;
	protected Rectangle area = null;
	protected Polygon polygon = null;
	protected List<Polygon> areas = new ArrayList<Polygon>();
	
	protected Stroke focusStroke = DefaultStrokeFactory.create(1);
	
	protected List<Connection> connections = new ArrayList<Connection>();
	
	protected void rectangleToPolygon(Polygon polygon, Rectangle rect){
		if(rect.isEmpty()) return;
		polygon.reset();
		polygon.addPoint(rect.x, rect.y);
		polygon.addPoint((int)rect.getMaxX(), rect.y);
		polygon.addPoint((int)rect.getMaxX(), (int)rect.getMaxY());
		polygon.addPoint(rect.x, (int)rect.getMaxY());
	}
	
	abstract protected void setPolygonByArea(Rectangle rect);
	
	public PlanePiece(){
		area = new Rectangle(0,0, EMPTY_SIZE, EMPTY_SIZE);
	}

	public List<Polygon> getAreas(){ return areas; }
	
	public void setArea(Rectangle rectangleArea){
		/** area 변경 API들은 항상 이 멤버함수를 호출하기 때문에 필요한 경우 이 멤버함수만 override 하면 된다. */
		this.area.setBounds(rectangleArea.x, rectangleArea.y, rectangleArea.width, rectangleArea.height);
		setPolygonByArea(area);
		markSet.reposition();

		for(Connection connection : connections){
			connection.linePiece.onResize(this);
		}
	}
		
	@Override
	public IMark onDragged(int fromX, int fromY, int toX, int toY, IMark mark){
		if(!isMoveable) return null;

		if(mark == null || !mark.getDirection().isAreal()) area.setBounds(area.x + (toX - fromX), area.y + (toY - fromY), area.width, area.height);
		else mark = markSet.mouseDragged(fromX, fromY, toX, toY, mark);
		setArea(area);		
		return mark;
	}
	
	public final void setBounds(int x, int y, int width, int height){
		area.setBounds(x, y, width, height);
		setArea(area);
	}
	
	public boolean isMoveable(){ return isMoveable; }
	public void setMoveable(boolean isMoveable){ this.isMoveable = isMoveable; }
	
	public boolean isSelectable(){ return isSelectable; }
	public void setSelectable(boolean isSelectable){ this.isSelectable = isSelectable; }
	
	@Override
	public void drawFocus(Graphics2D g) {
		if(!isSelectable) return;
		g.setStroke(focusStroke);
		g.setPaint(Color.YELLOW);
		g.drawRect(area.x, area.y, area.width, area.height);
	}
	
	@Override
	public void drawSelectedMark(Graphics2D g, int x, int y) {
		if(!isSelectable || !isMoveable) return;
		markSet.drawMark(g, x, y);
	}
	
	@Override
	public void drawConnectableMark(Graphics2D g, int x, int y) {
		if(!isSelectable || !isMoveable) return;
		markSet.drawConnectableMark(g, x, y);
	}
	
	@Override public boolean contains(IPiece piece){
		return Contains.contains(areas, piece.getAreas());
	}
	
	public boolean contains(List<Polygon> polygonList){
		return Contains.contains(areas, polygonList);
	}
	
	@Override public boolean intersects(Rectangle rect){
		return Intersect.intersects(this.areas, rect);
	}
	
	@Override public boolean intersects(IPiece piece){
		return Intersect.intersects(this.areas, piece.getAreas());
	}
	
	@Override public boolean intersects(List<Polygon> areas){
		return Intersect.intersects(this.areas, areas);
	}
	
	@Override public void setConnection(Connection connection){
		// 중복 체크
		for(Connection each : connections){
			if(each.linePiece.equals(connection.lineMark) && each.planeMark.equals(connection.planeMark)){
				return;
			}
		}
		connections.add(connection);
		setArea(area);
	}
	
	public void removeConnection(Connection connection){
		connections.remove(connection);
		
		// 중복 체크
		for(Connection each : connections){
			if(each.linePiece.equals(connection.lineMark) && each.planeMark.equals(connection.planeMark)){
				connections.remove(each);
				return;
			}
		}
	}
	
	public List<IPiece> getConnectedPieces(){
		if(connections.size() == 0) return Collections.emptyList();
		
		List<IPiece> list = new ArrayList<IPiece>();
		
		for(Connection each : connections){
			list.add(each.linePiece);
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
