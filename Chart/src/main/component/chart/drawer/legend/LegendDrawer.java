package main.component.chart.drawer.legend;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import main.component.chart.GraphicProperty;
import main.component.chart.drawer.Drawer;
import main.component.chart.drawer.data.DataDrawer;
import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;

abstract public class LegendDrawer extends Drawer{
	protected List<DataDrawer> dataDrawers = new ArrayList<DataDrawer>();
	
	protected Color fontColor = Color.BLACK;
	protected Font font = new Font("돋움", Font.PLAIN, 9);
	protected int textHeight = (int)(9.0 / GraphicProperty.textHeightAdjustment());
	
	public void setFont(Font font){ 
		this.font = font;
		textHeight = (int)(g.getFontMetrics(font).getHeight() * GraphicProperty.textHeightAdjustment());
	}
	
	protected int margin = GraphicProperty.margin();	// 여백
	
	Rectangle iconArea = new Rectangle();
	Rectangle nameArea = new Rectangle();

	@Override
	public void draw() {
		initLegendAreas();
	
		for(int i = 0; i < dataDrawers.size(); i++){
			DataDrawer dataDrawer = dataDrawers.get(i);
			int count = dataDrawer.getSetSize();
			
			for(int j = 0; j < count; j++){
				dataDrawer.drawLegend(j, iconArea);
				
				g.setFont(font);
				g.setColor(fontColor);
				dataDrawer.drawLegendText(j, nameArea);
				calculateNextArea();
			}
		}
	}
		
	protected void initLegendAreas(){
		iconArea.setBounds(plotter.left + margin, plotter.top, textHeight, textHeight);
		nameArea.setBounds((int)iconArea.getMaxX() + margin, iconArea.y, plotter.width - textHeight - margin, textHeight);
	}
	
	protected void calculateNextArea(){
		iconArea.setBounds(iconArea.x, iconArea.y + textHeight + margin, iconArea.width, iconArea.height);
		nameArea.setBounds(nameArea.x, iconArea.y, plotter.width - textHeight - margin, nameArea.height);
	}
		
	protected int getMaxStringWidth(){
		int maxWidth = 0;
		
		g.setFont(font);	
		for(int i = 0; i < dataDrawers.size(); i++){
			DataDrawer dataDrawer = dataDrawers.get(i);
			int count = dataDrawer.getSetSize();
			
			for(int j = 0; j < count; j++){
				int stringWidth = dataDrawer.getStringWidth(j);
				if(maxWidth < stringWidth) maxWidth = stringWidth;
			}
		}
		
		return maxWidth;
	}
	
	@Override
	public void setTheme(Theme theme) {
		fontColor = theme.legend.fontColor;
		setFont(theme.legend.font);
	}

	@Override
	public void setSettingControl(SettingControl settingControl) {
		this.settingControl = settingControl;
		plotter.setSetting(settingControl);
	}

	public void add(DataDrawer dataDrawer) {
		dataDrawers.add(dataDrawer);
	}
}
