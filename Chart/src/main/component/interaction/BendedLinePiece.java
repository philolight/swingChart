package main.component.interaction;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import main.component.ArrayMinMax;
import main.component.interaction.linePiece.Directions;
import main.component.interaction.marker.BendedLineMarkSet;
import main.component.interaction.marker.LineMarkSet;

public class BendedLinePiece extends LinePiece{		
	BendedLineMarkSet bendedLineMarkSet;
	
	@Override protected void init(){
		start = new Point2D.Float(0,0);
		end = new Point2D.Float(0,0);
		
		area = new Rectangle(0,0,0,0);
		
		polygon = new Polygon();
		areas.add(polygon);
					
		bendedLineMarkSet = new BendedLineMarkSet();
		bendedLineMarkSet.setDirectionList(directions);
		bendedLineMarkSet.setPointList(points);
		
		markSet = bendedLineMarkSet;
		markSet.setPiece(this);
				
		setStartPoint(0,0);
		setEndPoint(100,100);		
	}
	
	@Override public void setStartPoint(int x, int y){
		start.setLocation(x, y);
		
		setMiddlePoints();

		lineToPolygon();
		lineToArea();
		bendedLineMarkSet.reposition();
	}
	
	@Override public void setEndPoint(int x, int y){ 
		end.setLocation(x, y);
		
		setMiddlePoints();

		lineToPolygon();
		lineToArea();
				
		bendedLineMarkSet.reposition();
	}
	
	private void setMiddlePoints(){
		Connection startConnection = getStartConnection();
		Connection endConnection = getEndConnection();
		
		Directions startDirection;
		Directions endDirection;
		
		List<IPiece> connectedPieces = new ArrayList<IPiece>();
		
		if(startConnection == null) startDirection = Directions.NONE;
		else{
			startDirection = startConnection.planeMark.getDirection();
			connectedPieces.add(startConnection.planePiece);
		}
		if(endConnection == null) endDirection = Directions.NONE;
		else{
			endDirection = endConnection.planeMark.getDirection();
			connectedPieces.add(endConnection.planePiece);
		}
		
//		setStartDirection(startDirection, endDirection, connectedPieces, (int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
		
		if(startConnection == null){
			startDirection = Directions.HORIZONTAL_LINE_CENTER;
		}
		else{
			connectedPieces.add(startConnection.planePiece);
			switch(startConnection.planeMark.getDirection()){
			case LEFT:
			case RIGHT:
				startDirection = Directions.HORIZONTAL_LINE_CENTER;
				break;
			case TOP:
			case BOTTOM:
				startDirection = Directions.VERTICAL_LINE_CENTER;
				break;
			default:
				startDirection = Directions.NONE;
				break;
			}
		}
		
		if(endConnection == null) endDirection = Directions.HORIZONTAL_LINE_CENTER;
		else{
			connectedPieces.add(endConnection.planePiece);
			switch(endConnection.planeMark.getDirection()){
			case LEFT:
			case RIGHT:
				endDirection = Directions.HORIZONTAL_LINE_CENTER;
				break;
			case TOP:
			case BOTTOM:
				endDirection = Directions.VERTICAL_LINE_CENTER;
				break;
			default: 
				endDirection = Directions.NONE;
				break;
			}
		}

//		System.out.println("directions = " + directions.size());
//		for(Directions direction : directions){
//			System.out.println(direction.toString());
//		}
//		System.out.println("points = " + points.size());
		
		points.clear();
		directions.clear();
		
		points.add(new Point2D.Float((float)start.getX(), (float)start.getY()));
		directions.add(Directions.LINE_START);
		
		gatherCenterPoints(startDirection, endDirection, connectedPieces, (int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
	}
	
//	private Directions setStartDirection(Directions startDirection, Directions endDirection, List<IPiece> pieces, int x1, int y1, int x2, int y2){
//		Directions direction = Directions.NONE;
//		if(startDirection == Directions.NONE && endDirection == Directions.NONE){
//			if(start.getX() > end.getX()) return Directions.LEFT;
//			else return Directions.RIGHT;
//		}
//		
//		if(startDirection == Directions.NONE){
//			switch(endDirection){
//			case LEFT:
//				if(x1)
//				break;
//			case RIGHT:
//				break;
//			case TOP:
//				break;
//			case BOTTOM:
//				break;
//			default:
//				break;
//			}
//		}
//		
//		return direction;
//	}
	
	void gatherCenterPoints(Directions startDirection, Directions endDirection, List<IPiece> pieces, int x1, int y1, int x2, int y2){
		int x = 0, y = 0;
		
		switch(startDirection){
		case HORIZONTAL_LINE_CENTER:
			y = y1;
			switch(endDirection){
			case HORIZONTAL_LINE_CENTER:
				if(y == y2) x = x2;
				else x = (x1 + x2) / 2;
				break;
			case VERTICAL_LINE_CENTER:
				x = x2;
				for(IPiece piece : pieces){
					if(piece.contains(x, y)){
						int x3;
						if(x2 > x1) x3 = piece.getMark(Directions.LEFT).getX();
						else x3 = piece.getMark(Directions.RIGHT).getX();
						x = (x1 + x3) / 2;
						break;
					}
				}
				break;
			default:
				break;
			}
			break;
		case VERTICAL_LINE_CENTER:
			x = x1;
			switch(endDirection){
			case HORIZONTAL_LINE_CENTER:
				y = y2;
				for(IPiece piece : pieces){
					if(piece.contains(x, y)){
						int y3;
						if(y2 > y1) y3 = piece.getMark(Directions.TOP).getY();
						else y3 = piece.getMark(Directions.BOTTOM).getY();
						y = (y1 + y3) / 2;
						break;
					}
				}
				break;
			case VERTICAL_LINE_CENTER:
				if(x == x2) y = y2;
				else y = (y1 + y2);
				break;
			default:
				break;
			}
			break;
		default :
			break;
		}

		points.add(new Point2D.Float(x, y));
		
		if(startDirection == Directions.HORIZONTAL_LINE_CENTER) startDirection = Directions.VERTICAL_LINE_CENTER;
		else startDirection = Directions.HORIZONTAL_LINE_CENTER;
		
		directions.add(startDirection);
		
		if(x != x2 || y != y2){
			gatherCenterPoints(startDirection, endDirection, pieces, x, y, x2, y2);
		}
		else directions.set(directions.size()-1, Directions.LINE_END);
		
	}
	
	Connection getStartConnection(){
		for(Connection connection : connections){
			if(connection.lineMark.getDirection() == Directions.LINE_START) return connection;
		}
		
		return null;
	}
	
	Connection getEndConnection(){
		for(Connection connection : connections){
			if(connection.lineMark.getDirection() == Directions.LINE_END) return connection;
		}
		
		return null;
	}
	
	private void lineToPolygon(){
		if(points.size() < 2) return;
		polygon.reset();
		for(int i = 0; i < points.size(); i++){
			polygon.addPoint((int)points.get(i).getX() - 1, (int)points.get(i).getY() - 1);		
		}
		
		for(int i = points.size()-1; i >= 0; i--){
			polygon.addPoint((int)points.get(i).getX() + 1, (int)points.get(i).getY() + 1);		
		}
	}
	
	protected void lineToArea(){
		int minX, minY;
		int maxX, maxY;
		
		minX = ArrayMinMax.arrayMin(polygon.xpoints);
		maxX = ArrayMinMax.arrayMax(polygon.xpoints);
		
		minY = ArrayMinMax.arrayMin(polygon.ypoints);
		maxY = ArrayMinMax.arrayMax(polygon.ypoints);
		
		area.x = minX;
		area.y = minY;
		area.width = maxX - minX;
		area.height = maxY - maxY;
	}
	
	@Override
	public void draw(Graphics2D g){
		g.setStroke(stroke);
		g.setPaint(lineColor);
		if(points.size() < 2) return;
		for(int i = 0; i < points.size()-1; i++){
			g.drawLine((int)points.get(i).getX(), (int)points.get(i).getY(), (int)points.get(i+1).getX(), (int)points.get(i+1).getY());			
		}
	}
}
