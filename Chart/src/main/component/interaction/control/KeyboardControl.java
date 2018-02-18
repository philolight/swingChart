package main.component.interaction.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import main.component.interaction.GroupPiece;
import main.component.interaction.IPiece;
import main.component.interaction.NullPieceCollection;
import main.component.interaction.PieceCollection;

public class KeyboardControl implements KeyListener{
	private PieceCollection pieces = new NullPieceCollection();
	private Set<IPiece> selected = Collections.emptySet();
	
	boolean controlPushed = false;
	
	public boolean controlPushed(){ return controlPushed; }
	
//	private List<ICommand> redo = new ArrayList<ICommand>();
//	private List<ICommand> undo = new ArrayList<ICommand>();
	
	public KeyboardControl(JPanel panel){
		panel.addKeyListener(this);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_Z){	// ctrl + z
			System.out.println("ctrl + z");
		}
		else if(code == KeyEvent.VK_Y){	// ctrl + y
			System.out.println("ctrl + y");
		}
		else if(code == KeyEvent.VK_CONTROL){// ctrl
			controlPushed = true;
			return;
		}
		else if(code == KeyEvent.VK_G){// ctrl + g
			System.out.println("ctrl + g");

			selected.forEach(each -> pieces.remove(each));
			
			if(selected.size() > 1){
				makeGroup(selected);
			}
			else if(selected.size() == 1){
				releaseGroup(selected.iterator().next());
			}
		}
		else if(code == KeyEvent.VK_S){// ctrl + s
			System.out.println("ctrl + s");
			pieces.setSelectable(!pieces.isSelectable());
		}
		else if(code == KeyEvent.VK_M){// ctrl + m
			System.out.println("ctrl + m");
			pieces.setMoveable(!pieces.isMoveable());
		}
	}
	
	private void makeGroup(Set<IPiece> set){
		GroupPiece newGroup = new GroupPiece();
		
		set.forEach(each -> newGroup.add(each));
		set.forEach(each -> pieces.remove(each));
		set.clear();
		set.add(newGroup);
		pieces.add(newGroup);
	}
	
	private void releaseGroup(IPiece piece){
		if((piece instanceof GroupPiece) == false) return;
			
		GroupPiece group = (GroupPiece)piece;
		pieces.remove(group);
		
		List<IPiece> groupPieces = group.getPieces();
		groupPieces.forEach(each -> pieces.add(each));
		
		selected.clear();
		groupPieces.forEach( each -> selected.add(each));
	}

	@Override public void keyReleased(KeyEvent e) {
		controlPushed = false;
	}
	@Override public void keyTyped(KeyEvent e) {}

	public void setPieceCollection(PieceCollection pieces) {
		this.pieces = pieces;
	}

	public void setSelectedPieceSet(Set<IPiece> selected) {
		this.selected = selected;
	}
}
