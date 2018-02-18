package main.component.chart.drawer.axis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import main.component.chart.GraphicProperty;
import main.component.chart.drawer.Drawer;
import main.component.chart.drawer.data.DataDrawer;
import main.component.chart.theme.stroke.DefaultStrokeFactory;

abstract public class AxisDrawer extends Drawer{
	protected List<DataDrawer> dataDrawers = new ArrayList<DataDrawer>();
	
	protected Color lineColor = Color.BLACK;
	protected int 	lineWidth = 1;
	protected Stroke stroke = DefaultStrokeFactory.create(lineWidth);
	
	protected Color fontColor = Color.BLACK;
	protected Font 	font = new Font("돋움", Font.PLAIN, 9);
	protected int 	textHeight = (int)(9 * GraphicProperty.textHeightAdjustment());
	
	protected int markLineLength = 3;	// 축의 매 간격(labelInterval) 마다 표시되는 선의 길이
		
	@Override public void setGraphics2D(Graphics2D g){
		super.setGraphics2D(g);
		setFont(font);
	}
	
	public void setLineColor(Color lineColor){ this.lineColor = lineColor; }
	public void setTextColor(Color fontColor){ this.fontColor = fontColor; }
	
	public void setLineWidth(int lineWidth){
		this.lineWidth = lineWidth;
		stroke = DefaultStrokeFactory.create(lineWidth);
	}
	
	public void setFont(Font font){ 
		this.font = font;
		textHeight = (int)(g.getFontMetrics(font).getHeight() * GraphicProperty.textHeightAdjustment());
	}
	
	@Override
	abstract public void draw();
		
	public void add(DataDrawer dataDrawer) {
		dataDrawers.add(dataDrawer);
	}
}
