package main.component.interaction;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import main.component.common.VerticalPosition;

public class ImagePiece extends RectanglePiece{
	Image image;
	TextPiece text;
	
	public ImagePiece(){
		initText();
	}
	
	public void initText(){
		text = new TextPiece();
		text.setTextPosition(VerticalPosition.CENTER);
	}
	
	@Override
	public void setArea(Rectangle area) {
		super.setArea(area);
		setInnerPieceAreas();
	}
	
	protected void setInnerPieceAreas(){
		text.setBounds(area.x, area.y, area.width, area.height);
	}
	
	public void setImage(Image image){ this.image = image; }
	
	public void setFont(Font font){ text.setFont(font); }
	public void setTextColor(Color color){ text.setColor(color); }
	public void setText(String textString){ text.setText(textString); }
	public void setTextPosition(VerticalPosition textPosition){ text.setTextPosition(textPosition); }
		
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(image, area.x, area.y, (int)area.getMaxX(), (int)area.getMaxY(), 0, 0, image.getWidth(null), image.getHeight(null), null);
		text.draw(g);
	}
}
