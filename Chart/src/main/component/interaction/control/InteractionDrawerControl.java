package main.component.interaction.control;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Set;

import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;
import main.component.interaction.PieceCollection;
import main.component.interaction.GroupPiece;
import main.component.interaction.IPiece;
import main.component.interaction.panel.drawer.InteractionBackground;
import main.component.interaction.panel.drawer.MouseDrawer;
import main.component.interaction.panel.drawer.PieceDrawer;

public class InteractionDrawerControl{
	protected Theme 				theme = null;
	protected Rectangle 			area = new Rectangle(0, 0, 100, 100);
	protected InteractionBackground backgroundDrawer = new InteractionBackground();
	protected PieceDrawer 			pieceDrawer = new PieceDrawer();
	protected MouseDrawer			mouseDrawer = new MouseDrawer();
			
	BufferedImage backImage = null;
	BufferedImage finalImage = null;
	
	Graphics2D g = null;
		
	public InteractionDrawerControl(){
		setArea(area);
	}
	
	public void setArea(Rectangle area){
		this.area = area;
		backgroundDrawer.setArea(area);
		pieceDrawer.setArea(area);
		mouseDrawer.setArea(area);
		initImages();
		setGraphics();
	}
		
	protected void initImages(){
		backImage = new BufferedImage(area.width, area.height, BufferedImage.TYPE_INT_RGB);
		finalImage = new BufferedImage(area.width, area.height, BufferedImage.TYPE_INT_RGB);
	}
	
	protected void setGraphics(){
		if(backImage == null) return;

		initDrawerGraphics();
		initBackgroundGraphics();
	}
	
	protected void initDrawerGraphics(){
		g = (Graphics2D) finalImage.createGraphics();
		pieceDrawer.setGraphics2D(g);
		mouseDrawer.setGraphics2D(g);
	}
	
	protected void initBackgroundGraphics(){
		Graphics2D backgroundGraphics = (Graphics2D) backImage.createGraphics();
		backgroundGraphics.setColor(Color.WHITE);
		backgroundGraphics.fillRect(0, 0, area.width-1, area.height-1);
		backgroundDrawer.setGraphics2D(backgroundGraphics);
		backgroundDrawer.draw();
	}
	
	public void setTheme(Theme theme){
		this.theme = theme;
		backgroundDrawer.setTheme(theme);
		pieceDrawer.setTheme(theme);
		mouseDrawer.setTheme(theme);
		backgroundDrawer.draw();
	}

	public void reset(){
		backgroundDrawer.reset();
		pieceDrawer.reset();
		mouseDrawer.reset();
	}
	
	public void onUpdateData() {
		backgroundDrawer.onUpdateData();
		pieceDrawer.onUpdateData();
		mouseDrawer.onUpdateData();
	}
	
	public void setSettingControl(SettingControl settingControl) {
		backgroundDrawer.setSettingControl(settingControl);
		pieceDrawer.setSettingControl(settingControl);
		mouseDrawer.setSettingControl(settingControl);
	}

	public void drawAll(Graphics g2d) {
		g.drawImage(backImage, 0, 0, area.width-1, area.height-1, 0, 0, area.width-1, area.height-1, null);
		pieceDrawer.draw();
		mouseDrawer.draw();
		g2d.drawImage(finalImage, 0, 0, null);
	}
	
	public void setPieceCollection(PieceCollection pieces) {
		pieceDrawer.setPieceCollection(pieces);
	}

	public void selectedPieceGroup(Set<IPiece> selected) {
		pieceDrawer.selectedPieceGroup(selected);
	}

	public void setMouseControl(MouseControl mouseControl) {
		mouseDrawer.setMouseControl(mouseControl);
	}
}
