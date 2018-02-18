package main.component.interaction.control;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class MouseStateSpaceDragging extends MouseState{
	@Override public void draw(Graphics2D g){
		g.setPaint(Color.WHITE);
		g.drawRect(
				Math.min(x, dragStartX),
				Math.min(y, dragStartY),
				Math.abs(x - dragStartX),
				Math.abs(y - dragStartY)
						);
	}
	
	public MouseState onRelease(int x2, int y2) {
		List<Polygon> polygonList = new ArrayList<Polygon>();
		Polygon polygon = new Polygon();
		polygon.addPoint(Math.min(x2, dragStartX), Math.min(y2, dragStartY));
		polygon.addPoint(Math.min(x2, dragStartX), Math.max(y2, dragStartY));
		polygon.addPoint(Math.max(x2, dragStartX), Math.max(y2, dragStartY));
		polygon.addPoint(Math.max(x2, dragStartX), Math.min(y2, dragStartY));
		
		polygonList.add(polygon);			
		
		selected.clear();
		
		selected.addAll(pieces.getPiecesInArea(polygonList));
		
		if(area.contains(x2, y2)) return FOCUSED;
		return UNFOCUSED;
	}

	public void onDragged(int x2, int y2) {
	}
}
