package main.component.interaction;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.component.ArrayMinMax;
import main.component.interaction.marker.IMark;

public class PieceCollection {
	private List<IPiece> pieces = new ArrayList<IPiece>();
	private Rectangle area = new Rectangle();
	private Map<Integer, Map<Integer, List<IPiece>>> piecesPerArea;
	private int gridLength = 100;
	
	public void forPieceArea(IStrategy strategy, List<Polygon> areas){
		Rectangle rect = getRectangle(areas);
		
		for(int i = rect.x / gridLength; i < rect.getMaxX() / gridLength; i++){
			Map<Integer, List<IPiece>> map = piecesPerArea.get(i);
			
			for(int j = rect.y / gridLength; j < rect.getMaxY() / gridLength; j++){
				List<IPiece> list = map.get(j);
				strategy.strategy(list);
			}
		}
	}
	
	private Rectangle getRectangle(List<Polygon> areas){
		if(areas.size() == 0) return new Rectangle(0,0,0,0);
		
		int minX, minY, maxX, maxY;
		
		minX = ArrayMinMax.arrayMin(areas.get(0).xpoints);
		minY = ArrayMinMax.arrayMin(areas.get(0).ypoints);
		
		maxX = ArrayMinMax.arrayMax(areas.get(0).xpoints);
		maxY = ArrayMinMax.arrayMax(areas.get(0).ypoints);
		
		for(int i = 1; i < areas.size(); i++){
			int x[] = areas.get(i).xpoints;
			int y[] = areas.get(i).ypoints;
			
			int min = ArrayMinMax.arrayMin(x);
			int max = ArrayMinMax.arrayMax(x);
			
			if(minX > min) minX = min;
			if(maxX < max) maxX = max;
			
			min = ArrayMinMax.arrayMin(y);
			max = ArrayMinMax.arrayMax(y);
			
			if(minY > min) minY = min;
			if(maxY < max) maxY = max;
		}
		
		Rectangle rect = new Rectangle();
		rect.x = Math.max(minX, 0);
		rect.y = Math.max(minY, 0);
		rect.width = Math.min(maxX, (int)area.getMaxX()) - rect.x;
		rect.height = Math.min(maxY, (int)area.getMaxY()) - rect.y;
//		System.out.println(rect);
		return rect;
	}
	
	public void add(List<IPiece> pieces){
		for(IPiece piece : pieces){
			add(piece);
		}
	}
	
	public void add(IPiece piece){
		if(pieces.contains(piece) == false) pieces.add(piece);
		IStrategy.ADD.setPiece(piece);
		forPieceArea(IStrategy.ADD, piece.getAreas());
	}
		
	public void remove(IPiece piece){
		IStrategy.REMOVE.setPiece(piece);
		forPieceArea(IStrategy.REMOVE, piece.getAreas());
	}
	
	public Set<IPiece> getPiecesInArea(List<Polygon> polygonList){
		Set<IPiece> set = new HashSet<IPiece>();
		IStrategy.GET_PIECES_IN_AREA.setSet(set);
		IStrategy.GET_PIECES_IN_AREA.setAreas(polygonList);
		forPieceArea(IStrategy.GET_PIECES_IN_AREA, polygonList);
		
		return set;
	}
	
	public void setArea(Rectangle area){	
		this.area = area;
		piecesPerArea = new HashMap<Integer, Map<Integer, List<IPiece>>>();
		
		Rectangle grid = new Rectangle();
		for(int i = 0; i < area.width / gridLength + 1; i++){
			Map<Integer, List<IPiece>> map = new HashMap<Integer, List<IPiece>>();
			piecesPerArea.put(i, map);
			for(int j = 0; j < area.height / gridLength + 1; j++){
				grid.setBounds(i * gridLength, j * gridLength, gridLength, gridLength);
				
				List<IPiece> list = new ArrayList<IPiece>();
				map.put(j, list);
				for(IPiece planePiece : pieces){
					if(planePiece.intersects(grid)) list.add(planePiece);
				}
			}
		}
	}
	
	public IPiece get(int x, int y){
		int i = x / gridLength;
		int j = y / gridLength;

		Map<Integer, List<IPiece>> map = piecesPerArea.get(i);
		List<IPiece> list = map.get(j);
		
		for(IPiece piece : list){
			if(piece.contains(x, y)) return piece;
		}
		
		return null;
	}
	
	public IPiece getPlanePiece(int x, int y) {
		int i = x / gridLength;
		int j = y / gridLength;

		Map<Integer, List<IPiece>> map = piecesPerArea.get(i);
		List<IPiece> list = map.get(j);
		
		for(IPiece piece : list){
			if(piece.contains(x, y) && piece instanceof PlanePiece) return piece;
		}
		
		return null;
	}
	
	public void draw(Graphics2D g){
		pieces.forEach(each -> each.draw(g));
	}

	public void onDragged(Set<IPiece> selected, int fromX, int fromY, int toX, int toY, IMark mark) {
		// PlanePiece와 연결된 Line들이 다시 욺직일 수 있으므로 먼저 Line 부터 이동 시킴. 
		for(IPiece piece : selected){
			if(piece instanceof LinePiece){
				IStrategy.REMOVE.setPiece(piece);
				forPieceArea(IStrategy.REMOVE, piece.getAreas());
				piece.onDragged(fromX, fromY, toX, toY, mark);
				add(piece);
				
				// LinePiece와 연결된 object들도 이동시킴.
				List<IPiece> connectedList = piece.getConnectedPieces();
				for(IPiece each : connectedList){
					IStrategy.REMOVE.setPiece(each);
					forPieceArea(IStrategy.REMOVE, each.getAreas());
					add(each);
				}
			}
		}
		
		// PlanePiece을 이동 시킴.
		for(IPiece piece : selected){
			if(piece instanceof PlanePiece){
				IStrategy.REMOVE.setPiece(piece);
				forPieceArea(IStrategy.REMOVE, piece.getAreas());
				piece.onDragged(fromX, fromY, toX, toY, mark);
				add(piece);
				
				// PlanePiece와 연결된 object들도 이동시킴.
				List<IPiece> connectedList = piece.getConnectedPieces();
				for(IPiece each : connectedList){
					IStrategy.REMOVE.setPiece(each);
					forPieceArea(IStrategy.REMOVE, each.getAreas());
					add(each);
				}
			}
		}
	}
	
	private boolean isSelectable = true;
	public boolean isSelectable(){ return isSelectable; }
	public void setSelectable(boolean isSelectable) {
		this.isSelectable = isSelectable;
		pieces.forEach(each -> each.setSelectable(isSelectable));
	}
	
	private boolean isMoveable = true;
	public boolean isMoveable(){ return isMoveable; }
	public void setMoveable(boolean isMoveable) {
		this.isMoveable = isMoveable;
		pieces.forEach(each -> each.setMoveable(isMoveable));
	}
}

interface IStrategy{
	public static AddPiece 			ADD 				= new AddPiece();
	public static RemovePiece 		REMOVE 				= new RemovePiece();
	public static GetPiecesInArea 	GET_PIECES_IN_AREA 	= new GetPiecesInArea();
	public void strategy(List<IPiece> list);
}

class AddPiece implements IStrategy{
	IPiece piece;
	public void setPiece(IPiece piece){ this.piece = piece; }
	public void strategy(List<IPiece> list){
		list.add(piece);
	}
}

class RemovePiece implements IStrategy{
	IPiece piece;
	public void setPiece(IPiece piece){ this.piece = piece; }
	public void strategy(List<IPiece> list){
		list.remove(piece);
	}
}

class GetPiecesInArea implements IStrategy{
	Set<IPiece> set;
	List<Polygon> areas;
	public void setSet(Set<IPiece> set){ this.set = set; }
	public void setAreas(List<Polygon> areas){ this.areas = areas; }
	public void strategy(List<IPiece> list){
		for(IPiece piece : list){
			if(Contains.contains(areas, piece.getAreas())) set.add(piece);
		}
	}
}