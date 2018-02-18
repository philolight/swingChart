package main.component.interaction;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import main.component.chart.theme.stroke.DefaultStrokeFactory;

public class TransformerPiece extends RectanglePiece{
	int circleSize = 15;
	
	protected int lineWidth = 1;
	protected Stroke stroke = DefaultStrokeFactory.create(lineWidth);
	protected Color lineColor = Color.BLACK;
	
	public void setLineWidth(int lineWidth){
		this.lineWidth = lineWidth;
		stroke = DefaultStrokeFactory.create(lineWidth);
	}
	public void setColor(Color lineColor){ this.lineColor = lineColor; }
	
	@Override
	public void draw(Graphics2D g) {
		int x, y;
		
		g.setPaint(Color.YELLOW);
			
		x = (int)(area.getCenterX() - circleSize / 2);
		y = (int)(area.getCenterY() - circleSize * 0.75);
		g.drawArc(x, y, circleSize, circleSize, 0, 360);
		
		g.drawLine((int)area.getCenterX(), area.y, (int)area.getCenterX(), y);
		
		y = (int)(area.getCenterY() - circleSize * 0.25);
		g.drawArc(x, y, circleSize, circleSize, 0, 360);
		
		g.drawLine((int)area.getCenterX(), y + circleSize, (int)area.getCenterX(), (int)area.getMaxY());
	}
}
