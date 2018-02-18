package main.component.interaction.panel;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import main.component.chart.theme.Theme;
import main.component.interaction.IPiece;
import main.component.interaction.PieceCollection;
import main.component.interaction.control.InteractionDrawerControl;
import main.component.interaction.control.KeyboardControl;
import main.component.interaction.control.MouseControl;

public class InteractionPanel extends JPanel{
	PieceCollection pieces = new PieceCollection();
	Set<IPiece> selected = new HashSet<IPiece>();
	
	MouseControl mouseControl;
	KeyboardControl keyboardControl;
	InteractionDrawerControl drawerControl = new InteractionDrawerControl();
	
	public InteractionPanel(){
		keyboardControl = new KeyboardControl(this);
		keyboardControl.setPieceCollection(pieces);
		keyboardControl.setSelectedPieceSet(selected);
		
		mouseControl = new MouseControl(this);
		mouseControl.setPieceCollection(pieces);
		mouseControl.setSelectedPieceSet(selected);
		mouseControl.setKeyboardControl(keyboardControl);
		drawerControl.setPieceCollection(pieces);
		drawerControl.selectedPieceGroup(selected);
		drawerControl.setMouseControl(mouseControl);
		
		
	}
	
	public void add(IPiece piece){
		pieces.add(piece);
	}
	
	@Override
	public void setSize(int width, int height) {
		Rectangle area = new Rectangle(0,0,width,height);
		mouseControl.setArea(area);
		drawerControl.setArea(new Rectangle(0,0,width,height));
		super.setSize(width, height);
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	@Override
	public void paint(Graphics g) {
		drawerControl.drawAll(g);
	}

	public void setTheme(Theme theme) {
		drawerControl.setTheme(theme);
	}
}
