package main.component.interaction.control;

import java.awt.Graphics2D;

import main.component.interaction.Connection;
import main.component.interaction.IPiece;
import main.component.interaction.LinePiece;
import main.component.interaction.PlanePiece;
import main.component.interaction.Unit;
import main.component.interaction.marker.IMark;

public class MouseStatePieceDragging extends MouseState{
	private IMark mark = null;
	private IPiece piece;
	private IPiece planePiece;
		
	@Override public MouseState onRelease(int x2, int y2) {
		if(piece instanceof LinePiece && mark != null){
			planePiece = pieces.getPlanePiece(x, y);
			if(planePiece != null){
				IMark planeMark = planePiece.getConnectableMark(x, y);
				if(planeMark != null){
					Connection connection = new Connection();
					connection.setConnection((LinePiece)piece, (PlanePiece)planePiece, mark, planeMark);
					piece.setConnection(connection);
					planePiece.setConnection(connection);
				}
			}
		}
		
		piece.mouseReleased(x, y);
		
		if(area.contains(x2, y2)) return FOCUSED;
		return UNFOCUSED;
	}
	
	@Override public void onPressed(int x2, int y2) {
		for(IPiece selectedPiece : selected){
			if(selectedPiece.contains(x, y)){
				piece = selectedPiece;
				mark = piece.mousePressed(x, y);
				return;
			}
		}
		
		piece = pieces.get(x, y);
		mark = piece.mousePressed(x, y);
	}

	@Override public void onDragged(int x2, int y2) {
		// check movement
		int roundedX = dragStartX + Unit.round(x2 - dragStartX);
		int roundedY = dragStartY + Unit.round(y2 - dragStartY);			
		if(dragStartX == roundedX && dragStartY == roundedY) return;
		
		if(piece instanceof LinePiece && mark != null){		
			planePiece = pieces.getPlanePiece(x, y);
		}
		
		mark = piece.onDragged(roundedX, roundedY, roundedX, roundedY, mark);
		
		pieces.onDragged(selected, dragStartX, dragStartY, roundedX, roundedY, mark);
		
		dragStartX = roundedX;
		dragStartY = roundedY;
	}
	
	@Override public void draw(Graphics2D g){
		if(planePiece != null) planePiece.drawConnectableMark(g, x, y);
	}
}
