package main.component.interaction.marker;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import main.component.interaction.linePiece.Directions;

abstract public class MarkSet implements IMarkSet{
	protected List<IMark> marks = new ArrayList<IMark>();
	
	@Override public IMark getConnectableMark(int x, int y){
		for(IMark each : marks){
			if(each.isConnectable() && each.mouseFocused(x, y)){
				return each;
			}
		}
		return null;
	}
	
	@Override public IMark getMark(Directions direction){
		for(IMark each : marks){
			if(each.getDirection() == direction) return each;
		}
		
		return null;
	}
	
	@Override
	public boolean mouseFocused(int x, int y) {
		for(IMark each : marks){
			if(each.mouseFocused(x, y)){
				return true;
			}
		}
		return false;
	}
	
	@Override public IMark mousePressed(int x, int y){
		IMark mark = null;
		for(IMark each : marks){
			each.mousePressed(x, y);
			if(each.isSelected()) mark = each;
		}
		return mark;
	}
	
	@Override public void mouseReleased(int x, int y){
		for(IMark each : marks){
			each.mouseReleased();
		}
	}
	
	@Override
	public void drawMark(Graphics2D g, int x, int y) {
		for(IMark each : marks) each.drawMark(g, x, y);
	}
	
	@Override
	public void drawConnectableMark(Graphics2D g, int x, int y) {
		for(IMark each : marks){
			if(each.isConnectable())
				each.drawMark(g, x, y);
		}
	}
}
