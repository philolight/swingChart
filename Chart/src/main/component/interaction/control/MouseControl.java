package main.component.interaction.control;

import static main.component.interaction.control.MouseState.FOCUSED;
import static main.component.interaction.control.MouseState.PIECE_DRAGING;
import static main.component.interaction.control.MouseState.SPACE_DRAGING;
import static main.component.interaction.control.MouseState.UNFOCUSED;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Collections;
import java.util.Set;

import javax.swing.JPanel;

import main.component.interaction.IPiece;
import main.component.interaction.NullPieceCollection;
import main.component.interaction.PieceCollection;

public class MouseControl implements MouseListener, MouseMotionListener{
	private JPanel panel = null;

	private PieceCollection pieces = new NullPieceCollection();
	private Set<IPiece> selected = Collections.emptySet();
	
	private MouseState mouseState = UNFOCUSED;
	KeyboardControl keyboardControl = null;
	
	public int getX(){ return mouseState.getX(); }
	public int getY(){ return mouseState.getY(); }
		
	public MouseControl(JPanel panel){
		this.panel = panel;
		
		panel.addMouseMotionListener(this);
		panel.addMouseListener(this);
		
		mouseState.setPieceCollection(pieces);
		mouseState.setSelectedPieceSet(selected);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}		

	@Override
	public void mouseEntered(MouseEvent e) {
		mouseState.setPosition(e.getX(), e.getY());
		
		int x = e.getX();
		int y = e.getY();
		
		mouseState = FOCUSED;
		mouseState.setPosition(x, y);
		
		panel.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseState.setPosition(e.getX(), e.getY());
		mouseState.onRelease(e.getX(), e.getY());
		
		mouseState = UNFOCUSED;
		
		panel.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseState.setPosition(e.getX(), e.getY());
		mouseState.setDragStartPoint(e.getX(), e.getY());
				
		IPiece piece = null;
		
		for(IPiece selectedPiece : selected){
			if(selectedPiece.contains(e.getX(), e.getY())){
				piece = selectedPiece;
				break;
			}
		}
		
		if(piece == null) piece = pieces.get(e.getX(), e.getY());
		
		if(piece == null){
			selected.clear();
			mouseState = SPACE_DRAGING;
		}
		else{
			if(keyboardControl.controlPushed()){
				if(selected.contains(piece) == true) selected.remove(piece);
				else selected.add(piece);
			}
			else{
				if(selected.contains(piece) == false) selected.clear();
				selected.add(piece);
			}
			mouseState = PIECE_DRAGING;
		}
		mouseState.onPressed(e.getX(), e.getY());
		panel.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseState.setPosition(e.getX(), e.getY());
		
		mouseState = mouseState.onRelease(e.getX(), e.getY());
		
		panel.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseState.setPosition(e.getX(), e.getY());

		mouseState.onDragged(e.getX(), e.getY());
		
		panel.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseState.setPosition(e.getX(), e.getY());
		
		panel.repaint();
	}

	public void setPieceCollection(PieceCollection pieces) {
		this.pieces = pieces;
		mouseState.setPieceCollection(pieces);
	}

	public void setSelectedPieceSet(Set<IPiece> selected) {
		this.selected = selected;
		mouseState.setSelectedPieceSet(selected);
	}

	public void setArea(Rectangle area) {
		pieces.setArea(area);
		mouseState.setArea(area);
	}
	
	public MouseState getState() {
		return mouseState;
	}
	
	public void setKeyboardControl(KeyboardControl keyboardControl) {
		this.keyboardControl = keyboardControl;
	}
}
