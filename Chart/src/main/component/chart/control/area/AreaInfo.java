package main.component.chart.control.area;

import java.awt.Rectangle;
import java.security.InvalidParameterException;

public class AreaInfo {
	protected Rectangle area;

	public int left = 0;
	public int right = 0;
	public int top = 0;
	public int bottom = 0;
	public int width = 0;
	public int height = 0;
	public int centerX = 0;
	public int centerY = 0;
	
	public void setArea(Rectangle area){
		
		this.area = area; 
		
		this.left 	= area.x;
		this.top 	= area.y;
		this.right 	= area.x + area.width - 1;
		this.bottom = area.y + area.height - 1;
		this.width 	= area.width;
		this.height = area.height;
		this.centerX = area.x + area.width / 2;
		this.centerY = area.y + area.height / 2;
	}
	
	public Rectangle getArea(){ return area; }
}
