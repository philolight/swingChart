package main.component.chart.drawer.footer;

import java.awt.Color;
import java.awt.Font;

import main.component.chart.GraphicProperty;
import main.component.chart.control.area.AreaControl;
import main.component.chart.drawer.Drawer;
import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;

public class FooterDrawer extends Drawer{
	private Color backgroundColor = Color.BLACK;
	private Color fontColor = Color.WHITE;
	private Font font = new Font("Monotype Corsiva", Font.ITALIC, 13);
	private int textHeight = (int)(12.0 / GraphicProperty.textHeightAdjustment());
	
	public void setFont(Font font){ 
		this.font = font;
		textHeight = (int)(g.getFontMetrics(font).getHeight() * GraphicProperty.textHeightAdjustment());
	}
	
	@Override
	public void draw() {
//System.out.println(plotter.left + " " + plotter.right + " " + plotter.width + " " + plotter.height);
		
		g.setPaint(backgroundColor);
		g.fillRect(plotter.left, plotter.top, plotter.width, plotter.height);
		
		g.setColor(fontColor);
		g.setFont(font);
		g.drawString("Designed by Seyoung Lee", plotter.right - 150, plotter.bottom - 3);
	}

	@Override
	public void setTheme(Theme theme) {
		backgroundColor = theme.footer.backgroundColor;
		fontColor = theme.footer.fontColor;
		setFont(theme.footer.font);
	}

	@Override
	public void setAreaControl(AreaControl areaControl) {
		plotter.setArea(areaControl.getDataArea());
		setArea(areaControl.getFooterArea());
	}
	
	@Override
	public void setSettingControl(SettingControl settingControl) {
		this.settingControl = settingControl;
		plotter.setSetting(settingControl);
	}
}
