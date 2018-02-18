package main.component.interaction;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.List;

import main.component.chart.GraphicProperty;
import main.component.common.VerticalPosition;
import main.component.interaction.marker.IMark;
import main.component.interaction.marker.NullMarkSet;

public class TextPiece extends PlanePiece{
	protected boolean isSelectable = false;
	protected boolean isMoveable = false;
	
	private String text = "";
	private Color color = Color.BLACK;
	private Font font = new Font("돋움", Font.BOLD, 9);
	private VerticalPosition positionType = VerticalPosition.CENTER;
	private int fontHeight = (int)(9 * GraphicProperty.textHeightAdjustment());
	
	public void setFont(Font font){ this.font = font; }
	public void setColor(Color color){ this.color = color; }
	public void setText(String text){ this.text = text; }
	public void setTextPosition(VerticalPosition textPosition){ this.positionType = textPosition; }
	
	public TextPiece(){
		markSet = new NullMarkSet();
	}
	
	private int getTextX(Graphics2D g){
		return (int)(area.getCenterX() - g.getFontMetrics().stringWidth(text) / 2);
	}
	
	private int getTextY(Graphics2D g){
		fontHeight = (int)(g.getFontMetrics(font).getHeight() * GraphicProperty.textHeightAdjustment());
		
		switch(positionType){
		case TOP:
			return area.y + fontHeight;
		case CENTER:
			return (int)(area.getCenterY() + fontHeight / 2);
		case BOTTOM:
			return (int)area.getMaxY();
		}
		return (int)(area.getCenterY() + fontHeight / 2);
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setFont(font);
		g.setColor(color);
		g.drawString(text, getTextX(g), getTextY(g));
	}
	
	@Override public void drawFocus(Graphics2D g) {}
	@Override public boolean contains(int x, int y) { return false; }
	@Override public boolean intersects(Rectangle rect) { return false; }

	@Override public void setSelectable(boolean isSelectable){}
	@Override public void setMoveable(boolean isMoveable){}
	
	@Override public void mouseFocused(int x, int y){}
	@Override public IMark mousePressed(int x, int y){ return null; }
	@Override public void mouseReleased(int x, int y){}
	@Override protected void setPolygonByArea(Rectangle rect) {}
	@Override public Rectangle getArea() { return area; }
	
	@Override public boolean intersects(IPiece piece){ return false; }	
	@Override public boolean intersects(List<Polygon> areas){ return false; }
	
	@Override public void setConnection(Connection connection){}
}
