package main.component.interaction.control;

import java.awt.Graphics2D;

import main.component.interaction.IPiece;

public class MouseStateFocus extends MouseState{
	@Override public void draw(Graphics2D g){
		for(IPiece piece : selected){
			if(piece.contains(x, y)){
				piece.drawSelectedMark(g, x, y);
				return;
			}
		}
		
		IPiece piece = pieces.get(x, y);
		if(piece != null){
			if(selected.contains(piece)){
				piece.drawSelectedMark(g, x, y);
			}
			else piece.drawFocus(g);
		}
	}
}
