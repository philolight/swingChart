package main.component.interaction.marker;

import java.awt.Graphics2D;

import main.component.interaction.IPiece;
import main.component.interaction.linePiece.Directions;

public class NullMarkSet implements IMarkSet{

	@Override public void reposition() {}
	@Override public void drawMark(Graphics2D g, int x, int y) {}
	@Override public void drawConnectableMark(Graphics2D g, int x, int y) {}
	@Override public void setPiece(IPiece piece) {}
	@Override public IMark mouseDragged(int fromX, int fromY, int toX, int toY, IMark mark) { return null; }
	@Override public boolean mouseFocused(int x, int y) { return false; }
	@Override public IMark mousePressed(int x, int y) { return null; }
	@Override public void mouseReleased(int x, int y) {}
	@Override public IMark getConnectableMark(int x, int y){ return null; }
	@Override public IMark getMark(Directions direction){ return null; }
}
