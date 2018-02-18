package main.component.interaction.marker;

import java.awt.Graphics2D;

import main.component.interaction.IPiece;
import main.component.interaction.linePiece.Directions;

public class NoneConnectableMark extends Mark{
	private int leftX;
	private int topY;
	
	public NoneConnectableMark(Directions direction){
		this.direction = direction;
	}
			
	@Override public void setPosition(int x, int y){
		super.setPosition(x, y);
		
		leftX = (int)(point.getX() - size / 2.0);
		topY = (int)(point.getY() - size / 2.0);
	}
	
	@Override
	public void drawMark(Graphics2D g, int x, int y) {
		g.setStroke(stroke);
		
		if(isSelected || mouseFocused(x, y) == true){
			g.setPaint(focusFillColor);
			g.fillArc(leftX, topY, size, size, 0, 360);
		}
		
		g.setPaint(markColor);
		g.drawArc(leftX, topY, size, size, 0, 360);
	}

	@Override public boolean isConnectable() { return false; }
}
