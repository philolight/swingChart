package main.component.interaction.marker;

import java.awt.Graphics2D;

import main.component.interaction.IPiece;
import main.component.interaction.linePiece.Directions;

public interface IMark {	
	public int getX();
	public int getY();
	public void setPosition(int x, int y);
	
	public boolean isConnectable();
	
	public boolean isSelected();
	
	public Directions 	getDirection();
	public void 		setDirection(Directions direction);
	
	public void mouseDragged(int toX, int toY, Directions direction);
	public boolean mouseFocused(int x, int y);
	public void mousePressed(int x, int y);
	public void mouseReleased();
	
	public void drawMark(Graphics2D g, int x, int y);
}
