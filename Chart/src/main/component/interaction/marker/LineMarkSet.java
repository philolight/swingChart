package main.component.interaction.marker;

import main.component.interaction.IPiece;
import main.component.interaction.LinePiece;
import main.component.interaction.linePiece.Directions;

public class LineMarkSet extends MarkSet{
	LinePiece piece = null;
	@Override public void setPiece(IPiece piece){
		this.piece = (LinePiece)piece;
	}
		
	public LineMarkSet(){
		initMarks();
	}
	
	protected void initMarks(){
		marks.add(new NoneConnectableMark(Directions.LINE_START));
		marks.add(new NoneConnectableMark(Directions.LINE_END));
	}
		
	@Override public void reposition() {
		for(IMark each : marks){
			switch(each.getDirection()){
			case LINE_START:
				each.setPosition((int)piece.getStartPoint().getX(), (int)piece.getStartPoint().getY());
				break;
			case LINE_END:
				each.setPosition((int)piece.getEndPoint().getX(), (int)piece.getEndPoint().getY());
				break;
			default:
				break;
			}
		}
	}
	
	@Override public IMark mouseDragged(int fromX, int fromY, int toX, int toY, IMark mark){
		for(IMark each : marks){
			if(each.getDirection() == mark.getDirection()){
				each.mouseDragged(toX, toY, mark.getDirection());
				switch(each.getDirection()){
				case LINE_START:
					piece.setStartPoint(each.getX(), each.getY());
					break;
				case LINE_END:
					piece.setEndPoint(each.getX(), each.getY());
					break;
				default:
					break;
				}
			}
		}
		
		return mark;
	}

	public IMark getSelectedMark(int x, int y) {
		for(IMark each : marks){
			if(each.mouseFocused(x, y))
				return each;
		}
		return null;
	}
}
