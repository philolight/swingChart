package main.component.chart.drawer.data;

import java.awt.Rectangle;

import main.component.chart.Side;
import main.component.chart.control.minMax.MinMaxControl;
import main.component.chart.drawer.Drawer;

public abstract class DataDrawer extends Drawer{
	protected Side side;
	
	public Side getSide(){ return side; }
	
	public DataDrawer(Side side){
		this.side = side;
		setMinMaxControl(side);
	}
	
	protected void setMinMaxControl(Side side){
		plotter.setMinMaxControl(new MinMaxControl(side));
	}
	
	public void draw(){
		for(int i = plotter.getLeftIndex(); i <= plotter.getRightIndex(); i++){
			drawData(i);
		}
	}
	
	abstract protected void drawData(int index);
	abstract public void drawLegend(int index, Rectangle area);
	public void drawLegendText(int index, Rectangle area){
		g.drawString(plotter.getName(index), area.x, area.y + area.height);
	}
	
	public int getSetSize(){ return plotter.getSetSize(); }
	public int getStringWidth(int index) {
		return g.getFontMetrics().stringWidth(plotter.getName(index));
	}
	
	protected int index(int n){ return n; }
	protected int series(int n){ return n; }
}