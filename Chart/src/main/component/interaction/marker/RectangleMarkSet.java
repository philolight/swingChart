package main.component.interaction.marker;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import main.component.interaction.IPiece;
import main.component.interaction.linePiece.Directions;

public class RectangleMarkSet extends MarkSet{
	protected Rectangle area;
	
	IPiece piece = null;
	@Override public void setPiece(IPiece piece){
		this.piece = piece;
	}
		
	public RectangleMarkSet(Rectangle area){
		initMarks();
		setArea(area);
	}
	
	private void initMarks(){
		marks.add(new ConnectableMark(Directions.TOP));
		marks.add(new ConnectableMark(Directions.BOTTOM));
		marks.add(new ConnectableMark(Directions.LEFT));
		marks.add(new ConnectableMark(Directions.RIGHT));
		marks.add(new NoneConnectableMark(Directions.LEFT_TOP));
		marks.add(new NoneConnectableMark(Directions.RIGHT_TOP));
		marks.add(new NoneConnectableMark(Directions.LEFT_BOTTOM));
		marks.add(new NoneConnectableMark(Directions.RIGHT_BOTTOM));		
	}
	
	private void setArea(Rectangle area){
		this.area = area;
		reposition();
	}
	
	@Override public void reposition() {
		if(piece != null) this.area = piece.getArea();
		
		for(IMark each : marks){
			switch(each.getDirection()){
			case TOP:
				each.setPosition((int)area.getCenterX()	, area.y);
				break;
			case BOTTOM:
				each.setPosition((int)area.getCenterX()	, (int)area.getMaxY());
				break;
			case LEFT:
				each.setPosition((int)area.x			, (int)area.getCenterY());
				break;
			case RIGHT:
				each.setPosition((int)area.getMaxX()	, (int)area.getCenterY());
				break;
			case LEFT_TOP:
				each.setPosition((int)area.x			, area.y);
				break;
			case RIGHT_TOP:
				each.setPosition((int)area.getMaxX()	, area.y);
				break;
			case LEFT_BOTTOM:
				each.setPosition((int)area.x			, (int)area.getMaxY());
				break;
			case RIGHT_BOTTOM:
				each.setPosition((int)area.getMaxX()	, (int)area.getMaxY());
				break;
			default:
				break;
			}
		}
	}
	
	@Override public IMark mouseDragged(int fromX, int fromY, int toX, int toY, IMark mark){
		IMark ownDirectionedMark = null;
		for(IMark each : marks){
			if(each.getDirection() == mark.getDirection()){
				ownDirectionedMark = each;
				break;
			}
		}
		
		for(IMark each : marks){
			if(each.getDirection() == mark.getDirection()){
				each.mouseDragged(toX, toY, mark.getDirection());
				switch(each.getDirection()){
				case TOP :
					topDragged(ownDirectionedMark, mark.getDirection(), toY);
					break;
				case BOTTOM	:
					bottomDragged(ownDirectionedMark, mark.getDirection(), toY);
					break;
				case LEFT :
					leftDragged(ownDirectionedMark, mark.getDirection(), toX);
					break;
				case RIGHT :
					rightDragged(ownDirectionedMark, mark.getDirection(), toX);
					break;
				case LEFT_TOP :
					topDragged(ownDirectionedMark, mark.getDirection(), toY);
					leftDragged(ownDirectionedMark, mark.getDirection(), toX);
					break;
				case RIGHT_TOP :
					rightDragged(ownDirectionedMark, mark.getDirection(), toX);
					topDragged(ownDirectionedMark, mark.getDirection(), toY);
					break;
				case LEFT_BOTTOM :
					leftDragged(ownDirectionedMark, mark.getDirection(), toX);
					bottomDragged(ownDirectionedMark, mark.getDirection(), toY);
					break;
				case RIGHT_BOTTOM :
					rightDragged(ownDirectionedMark, mark.getDirection(), toX);
					bottomDragged(ownDirectionedMark, mark.getDirection(), toY);
					break;
				default:
					break;
				}
			}
		}
		
		checkWidthFlipedOver();
		checkHeightFlipedOver();
		
		for(IMark each : marks){
			if(each.isSelected()) return each;
		}
		
		return null;
	}
	
	private void topDragged(IMark mark, Directions direction, int toY){
		int heightWillBe = area.height - (toY - area.y);
		if(heightWillBe == 0) return;
		
		area.y = mark.getY();
		area.height = heightWillBe;
	}
	
	private void bottomDragged(IMark mark, Directions direction, int toY){
		int heightWillBe = mark.getY() - area.y;
		if(heightWillBe == 0) return;
		
		area.height = heightWillBe;
	}
	
	private void leftDragged(IMark mark, Directions direction, int toX){
		int widthWillBe = area.width - (toX - area.x);
		if(widthWillBe == 0) return;
		
		area.x = mark.getX();
		area.width = widthWillBe;
	}
	
	private void rightDragged(IMark mark, Directions direction, int toX){
		int widthWillBe = mark.getX() - area.x;
		if(widthWillBe == 0) return;
		
		area.width = widthWillBe;
	}
	
	private void checkWidthFlipedOver(){
		if(area.width > 0) return;

		area.x = Math.min(area.x, area.x + area.width);
		area.width = Math.abs(area.width);
		
		for(IMark each : marks){
			switch(each.getDirection()){
			case LEFT_TOP:
				each.setDirection(Directions.RIGHT_TOP);
				break;
			case RIGHT_TOP:
				each.setDirection(Directions.LEFT_TOP);
				break;
			case LEFT:
				each.setDirection(Directions.RIGHT);
				break;
			case RIGHT:
				each.setDirection(Directions.LEFT);
				break;
			case LEFT_BOTTOM:
				each.setDirection(Directions.RIGHT_BOTTOM);
				break;
			case RIGHT_BOTTOM:
				each.setDirection(Directions.LEFT_BOTTOM);
				break;
			default:
				break;
			}
		}
	}
	
	private void checkHeightFlipedOver(){
		if(area.height > 0) return;
		
		area.y = Math.min(area.y,  area.y + area.height);
		area.height = Math.abs(area.height);
		
		for(IMark each : marks){
			switch(each.getDirection()){
			case LEFT_TOP:
				each.setDirection(Directions.LEFT_BOTTOM);
				break;
			case RIGHT_TOP:
				each.setDirection(Directions.RIGHT_BOTTOM);
				break;
			case TOP:
				each.setDirection(Directions.BOTTOM);
				break;
			case BOTTOM:
				each.setDirection(Directions.TOP);
				break;
			case LEFT_BOTTOM:
				each.setDirection(Directions.LEFT_TOP);
				break;
			case RIGHT_BOTTOM:
				each.setDirection(Directions.RIGHT_TOP);
				break;
			default:
				break;
			}
		}
	}
}
