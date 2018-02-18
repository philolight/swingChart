package main.component.interaction.marker;

import java.awt.Graphics2D;

import main.component.interaction.IPiece;
import main.component.interaction.linePiece.Directions;

public class NullMark implements IMark{
	@Override public int getX(){ return 0;}
	@Override public int getY(){ return 0;}
	@Override public void setPosition(int x, int y){}
	
	@Override public boolean isSelected(){ return false; }
	
	@Override public boolean isConnectable() { return false; }
	
	@Override public Directions getDirection(){ return Directions.NONE; }
	@Override public void 		setDirection(Directions direction){}
	
	@Override public boolean mouseFocused(int x, int y) { return false; }
	@Override public void mouseDragged(int toX, int toY, Directions direction) {}
	@Override public void mousePressed(int x, int y){}
	@Override public void mouseReleased(){}
	
	@Override public void drawMark(Graphics2D g, int x, int y) {}
}
