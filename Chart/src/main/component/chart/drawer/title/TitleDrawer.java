package main.component.chart.drawer.title;

import java.awt.Color;
import java.awt.Font;
import java.awt.LinearGradientPaint;
import java.awt.Rectangle;

import main.component.chart.GraphicProperty;
import main.component.chart.control.area.AreaControl;
import main.component.chart.drawer.Drawer;
import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;
import main.component.chart.theme.gradient.ThreeToneGradientFactory;

public class TitleDrawer extends Drawer{
	public Color backgroundStartColor = Color.WHITE;
	public Color backgroundEndColor = Color.BLACK;
	LinearGradientPaint gradient;
	
	private Rectangle dataArea;
	private Color fontColor = Color.BLACK;
	private Font font = new Font("Arial Black", Font.PLAIN, 16);

	private int textHeight = (int)(20 / GraphicProperty.textHeightAdjustment());
	
	public void setFont(Font font){ 
		this.font = font;
		textHeight = (int)(g.getFontMetrics(font).getHeight() * GraphicProperty.textHeightAdjustment());
	}
	
	@Override
	public void draw() {
		drawBackground();
		drawTitle();
	}
	
	private void drawTitle(){
		g.setFont(font);
		String str = settingControl.getTitle();
		
		int textWidth = g.getFontMetrics().stringWidth(str);
		
		g.setColor(Color.BLACK);
		g.drawString(str, (int)dataArea.getCenterX() - textWidth / 2 + 1, plotter.centerY + textHeight / 2 + 1);

		g.setColor(fontColor);
		g.drawString(str, (int)dataArea.getCenterX() - textWidth / 2, plotter.centerY + textHeight / 2);
	}

	@Override
	public void setTheme(Theme theme) {
		backgroundStartColor = theme.title.backgroundStartColor;
		backgroundEndColor = theme.title.backgroundEndColor;
		gradient = ThreeToneGradientFactory.create(backgroundStartColor, backgroundEndColor, plotter.top, plotter.height);
		fontColor = theme.title.fontColor;
		setFont(theme.title.font);
		textHeight = (int)(g.getFontMetrics(font).getHeight() * GraphicProperty.textHeightAdjustment());
	}

	@Override
	public void setAreaControl(AreaControl areaControl) {
		plotter.setArea(areaControl.getTitleArea());
		setArea(areaControl.getTitleArea());
		dataArea = areaControl.getDataArea();
		gradient = ThreeToneGradientFactory.create(backgroundStartColor, backgroundEndColor, plotter.top, plotter.height);
	}

	@Override
	public void setSettingControl(SettingControl settingControl) {
		this.settingControl = settingControl;
		plotter.setSetting(settingControl);
	}
	
	protected void drawBackground(){		
		g.setPaint(gradient);
		g.fillRect(plotter.left, plotter.top, plotter.width, plotter.height);
	}
}
