package main.component.interaction.marker;

import java.awt.Graphics2D;

import main.component.interaction.IPiece;
import main.component.interaction.linePiece.Directions;

public interface IMarkSet {
	public void reposition();
	
	public void setPiece(IPiece piece);
	
	public IMark getConnectableMark(int x, int y);
	public IMark getMark(Directions direction);
	
	public IMark mouseDragged(int fromX, int fromY, int toX, int toY, IMark mark);
	public boolean mouseFocused(int x, int y);
	public IMark mousePressed(int x, int y);
	public void mouseReleased(int x, int y);
	
	public void drawMark(Graphics2D g, int x, int y);
	public void drawConnectableMark(Graphics2D g, int x, int y);
}
