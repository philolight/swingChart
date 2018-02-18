package main.component.interaction;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.List;

import main.component.interaction.linePiece.Directions;
import main.component.interaction.marker.IMark;

public interface IPiece {
	
	public boolean isMoveable();
	public void setMoveable(boolean isMoveable);
	
	public boolean isSelectable();
	public void setSelectable(boolean isSelectable);

	public Rectangle getArea();
	public List<Polygon>getAreas();
	
	public boolean contains(int x, int y);
	public boolean contains(IPiece piece);
	public boolean contains(List<Polygon> polygonList);
	public boolean intersects(IPiece piece);
	public boolean intersects(List<Polygon> areas);
	public boolean intersects(Rectangle rect);
	
	public void draw(Graphics2D g);
	public void drawFocus(Graphics2D g);
	public void drawSelectedMark(Graphics2D g, int x, int y);
	public void drawConnectableMark(Graphics2D g, int x, int y);
	
	public IMark onDragged(int fromX, int fromY, int toX, int toY, IMark mark);	
	public void mouseFocused(int x, int y);
	public IMark mousePressed(int x, int y);
	public void mouseReleased(int x, int y);
	
	public void setConnection(Connection connection);
	public List<IPiece> getConnectedPieces();
	
	public IMark getMark(Directions direction);
	public IMark getConnectableMark(int x, int y);
}
