package main.component.interaction;

import java.awt.Rectangle;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class NullPieceCollection extends PieceCollection {
	public void forPieceArea(IStrategy strategy, Rectangle rect){}
	public void add(IPiece piece){}
	public void add(List<IPiece> pieces){}
	public void remove(IPiece piece){}
	public Set<IPiece> getPiecesInArea(Rectangle rect){ return Collections.emptySet(); }	
	public void setArea(Rectangle area){}	
	public IPiece get(int x, int y){ return null; }
}
