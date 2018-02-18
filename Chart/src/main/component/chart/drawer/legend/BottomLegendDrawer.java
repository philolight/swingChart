package main.component.chart.drawer.legend;

import main.component.chart.control.area.AreaControl;
import main.component.chart.drawer.data.DataDrawer;

public class BottomLegendDrawer extends LegendDrawer{
	
	@Override
	public void setAreaControl(AreaControl areaControl) {
		plotter.setArea(areaControl.getBottomLegendArea());
		this.setArea(areaControl.getBottomLegendArea());
	}
	
	protected boolean isVertical = false;
	protected double widthHeightRatio = 4.0;
	protected int maxStringWidth; 

	@Override
	protected void initLegendAreas(){
		maxStringWidth = getMaxStringWidth();
		if(plotter.height > (textHeight + margin) * widthHeightRatio) isVertical = true;
		else isVertical = false;
		
		if(isVertical){
			super.initLegendAreas();
			return;
		}
		
		int startx = plotter.left + (plotter.width - calculateTotalWidth()) / 2;
					
		iconArea.setBounds(startx, plotter.top, textHeight, textHeight);
		nameArea.setBounds((int)iconArea.getMaxX() + margin, 
				iconArea.y, 
				maxStringWidth, 
				textHeight);
	}
	
	@Override
	protected void calculateNextArea(){
		if(isVertical){
			super.calculateNextArea();
			return;
		}
		
		iconArea.setBounds(nameArea.x + nameArea.width + textHeight + margin, plotter.top, textHeight, textHeight);
		nameArea.setBounds(iconArea.x + iconArea.width + margin, 
				iconArea.y, 
				maxStringWidth, 
				textHeight);
	}
	
	protected int calculateTotalWidth(){
		int total = 0;
 
		g.setFont(font);
		for(int i = 0; i < dataDrawers.size(); i++){
			DataDrawer dataDrawer = dataDrawers.get(i);
			int count = dataDrawer.getSetSize();
			
			for(int j = 0; j < count; j++){
				total += margin + textHeight + margin + dataDrawer.getStringWidth(j);
			}
		}
		
		return total;
	}
}
