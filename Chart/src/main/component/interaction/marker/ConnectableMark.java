package main.component.interaction.marker;

import java.awt.Graphics2D;
import java.awt.Stroke;

import main.component.chart.theme.stroke.DefaultStrokeFactory;
import main.component.interaction.IPiece;
import main.component.interaction.linePiece.Directions;

public class ConnectableMark extends Mark{
	private int leftX;
	private int topY;
	
	public ConnectableMark(Directions direction){
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
			g.fillRect(leftX, topY, size, size);
		}
		
		g.setPaint(markColor);
		g.drawRect(leftX, topY, size, size);
	}

	@Override public boolean isConnectable() { return true; }
}
