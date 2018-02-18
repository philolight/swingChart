package main.component.interaction.marker;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.List;

import main.component.interaction.BendedLinePiece;
import main.component.interaction.IPiece;
import main.component.interaction.linePiece.Directions;

public class BendedLineMarkSet extends LineMarkSet{
	BendedLinePiece piece = null;
	
	@Override public void setPiece(IPiece piece){
		this.piece = (BendedLinePiece)piece;
	}
		
	@Override protected void initMarks(){
		marks.add(new ConnectableMark(Directions.LINE_START));
		marks.add(new ConnectableMark(Directions.LINE_END));
	}
	
	List<Directions> directions;
	List<Point2D> points;
	
	public void setDirectionList(List<Directions> directions){
		this.directions = directions;
	}
	
	public void setPointList(List<Point2D> points){
		this.points = points;
	}
	
	@Override public void reposition() {
		IMark startMark = marks.get(0);
		IMark endMark = marks.get(marks.size()-1);
		
		marks.clear();
		marks.add(startMark);
		marks.add(endMark);
		
		startMark.setPosition((int)points.get(0).getX(), (int)points.get(0).getY());
		endMark.setPosition((int)points.get(points.size()-1).getX(), (int)points.get(points.size()-1).getY());
		
		for(int i = 1; i < points.size()-1; i++){
			IMark mark = new NoneConnectableMark(directions.get(i));
			mark.setPosition((int)points.get(i).getX(), (int)points.get(i).getY());
			marks.add(i, mark);
			switch(mark.getDirection()){
			case HORIZONTAL_LINE_CENTER :
				mark.setPosition((int)(points.get(i-1).getX() + points.get(i+1).getX()) / 2, mark.getY());
				break;
			case VERTICAL_LINE_CENTER :
				mark.setPosition(mark.getX(), (int)(points.get(i-1).getY() + points.get(i+1).getY()) / 2);
				break;
			default :
				break;
			}
		}
	}
	
	@Override public IMark mouseDragged(int fromX, int fromY, int toX, int toY, IMark mark){
		int i = -1;
		for(IMark each : marks){
			if(each.getDirection() == mark.getDirection()){
				each.mouseDragged(toX, toY, mark.getDirection());
				switch(each.getDirection()){
				case LINE_START:
					piece.setStartPoint(each.getX(), each.getY());
					return mark;
				case LINE_END:
					piece.setEndPoint(each.getX(), each.getY());
					return mark;
				case HORIZONTAL_LINE_CENTER:
					i = getMarkIndex(mark);
					if(i == -1) return mark;
					points.get(i-1).setLocation(points.get(i-1).getX(), mark.getY());
					points.get(i+1).setLocation(points.get(i+1).getX(), mark.getY());
					return mark;
				case VERTICAL_LINE_CENTER:
					i = getMarkIndex(mark);
					if(i == -1) return mark;
					points.get(i-1).setLocation(points.get(i-1).getX(), mark.getY());
					points.get(i+1).setLocation(points.get(i+1).getX(), mark.getY());
					return mark;
				default:
					break;
				}
			}
		}
		
		return mark;
	}
	
	private int getMarkIndex(IMark mark){
		for(int i = 0; i < marks.size(); i++){
			if(mark.equals(marks.get(i))) return i;
		}
		
		return -1;
	}

	public IMark getSelectedMark(int x, int y) {
		for(IMark each : marks){
			if(each.mouseFocused(x, y))
				return each;
		}
		return null;
	}
}
