package main.component.interaction.control;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import main.component.interaction.Connection;
import main.component.interaction.IPiece;
import main.component.interaction.LinePiece;
import main.component.interaction.PieceCollection;
import main.component.interaction.PlanePiece;
import main.component.interaction.Unit;
import main.component.interaction.marker.IMark;

abstract public class MouseState {
	public static MouseState UNFOCUSED = new MouseStateUnfocus();
	public static MouseState FOCUSED = new MouseStateFocus();
	public static MouseState PIECE_DRAGING = new MouseStatePieceDragging();
	public static MouseState SPACE_DRAGING = new MouseStateSpaceDragging();

	static int x = 0;
	static int y = 0;
	static PieceCollection pieces;
	static Set<IPiece> selected;
	static Rectangle area = null;
	
	public void setPosition(int x, int y){ this.x = x; this.y = y; }
	public int getX(){return x;}
	public int getY(){return y;}
	public MouseState onRelease(int x2, int y2){ return this; }
	public void onDragged(int x2, int y2){}
	public void setPieceCollection(PieceCollection pieces) { this.pieces = pieces; }
	public void setSelectedPieceSet(Set<IPiece> selected) { this.selected = selected; }
	public void draw(Graphics2D g){}
	public void setArea(Rectangle area){ this.area = area; }
	
	static int dragStartX = 0;
	static int dragStartY = 0;
	public void setDragStartPoint(int x2, int y2){
		dragStartX = x2;
		dragStartY = y2;
	}
	
	public void onPressed(int x2, int y2) {}
}
