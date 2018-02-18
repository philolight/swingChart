package main.component.interaction;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import main.component.interaction.marker.IMark;

public class GroupPiece extends RectanglePiece{
	private List<IPiece> pieces = new ArrayList<IPiece>();
	
	public void setPieces(List<IPiece> newPieces){
		newPieces.forEach(each -> add(each));
	}
	
	public void add(IPiece piece) {
		pieces.add(piece);
		piece.getAreas().forEach(each -> areas.add(each));
		if(area.isEmpty()){
			Rectangle pieceArea = piece.getArea();
			area.setBounds(pieceArea.x, pieceArea.y, pieceArea.width, pieceArea.height);
		}
		else area.add(piece.getArea());
		
		setArea(area);
	}
	
	public List<IPiece> getPieces(){ return pieces; }
		
	public void remove(IPiece piece) {
		piece.getAreas().forEach(each -> areas.remove(each));
		pieces.remove(piece);
	}
	public void clear() {
		pieces.clear();
		areas.clear();
		area.setBounds(0,0,0,0);
	}
	
	@Override
	public boolean contains(int x, int y) {
		for(IPiece piece : pieces){
			if(piece.contains(x, y)) return true;
		}
		return false;
	}
	
	@Override
	public boolean intersects(Rectangle rect) {
		return area.intersects(rect);
	}
	
	@Override public void draw(Graphics2D g) { pieces.forEach(each -> each.draw(g)); }
	
	@Override public IMark onDragged(int fromX, int fromY, int toX, int toY, IMark mark){
		pieces.forEach(each -> each.onDragged(fromX, fromY, toX, toY, mark));
		area.setBounds(0,0,0,0);
		
		List<IPiece> list = new ArrayList<IPiece>();
		pieces.forEach(each -> list.add(each));
		pieces.clear();
		setPieces(list);
		
		return mark;
	}
	
	@Override public void setMoveable(boolean isMoveable){
		this.isMoveable = isMoveable; 
		pieces.forEach(each -> each.setMoveable(isMoveable));
	}
	
	@Override public void setSelectable(boolean isSelectable) {
		this.isSelectable = isSelectable;
		pieces.forEach(each -> each.setSelectable(isSelectable));
	}
}
