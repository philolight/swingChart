package main.component.interaction;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

import main.component.chart.color.IColorProvider;
import main.component.chart.theme.stroke.DefaultStrokeFactory;
import main.component.common.VerticalPosition;
import main.component.interaction.marker.RectangleMarkSet;

public class BarGraphPiece extends RectanglePiece{
	TextPiece titleText;
	TextPiece valueText;
	RectanglePiece box;
	
	double maxValue = 1.0;
	double minValue = 1.0;
	double value = 0.0;
	double barWidthRatio = 0.6;
	double titleRatio = 0.1;
	protected int barLineWidth = 1;
	protected Stroke stroke = DefaultStrokeFactory.create(barLineWidth);
	protected IColorProvider colorProvider = IColorProvider.NULL;
	
	public BarGraphPiece(){
		initTitleText();
		initValueText();
		initBox();
		
		setInnerPieceAreas();
	}
	
	protected void initTitleText(){
		titleText = new TextPiece();
		titleText.setTextPosition(VerticalPosition.CENTER);
	}
	
	protected void initValueText(){
		valueText = new TextPiece();
		titleText.setTextPosition(VerticalPosition.CENTER);
	}
	
	protected void initBox(){
		box = new RectanglePiece();
	}
	
	@Override
	public void draw(Graphics2D g) {
		int barWidth = calculateBarWidth();
		int barX = (int)(area.getCenterX() - barWidth / 2);
		int barTop = area.y + (int)(area.height * (maxValue - value) / (maxValue - minValue));

		g.setPaint(colorProvider.getNormalColor(0, 0, value));
		g.fillRect(barX, barTop, barWidth, area.height);
		
		g.setPaint(colorProvider.getBrightColor(0, 0, value));
		g.setStroke(stroke);
		g.drawRect(barX, barTop, barWidth, area.height);
	}
	
	protected int calculateBarWidth(){
		return (int)(area.width * barWidthRatio);
	}
	
	@Override
	public void setArea(Rectangle area) {
		super.setArea(area);
		setInnerPieceAreas();
	}
	
	protected void setInnerPieceAreas(){
		int titleTextHeight = (int)(area.height * titleRatio);
		titleText.setBounds(area.x, area.y, area.width, titleTextHeight);
		valueText.setBounds(area.x, area.y + titleTextHeight, area.width, area.height - titleTextHeight);
		box.setArea(area);
	}
	
	public void setColorConditioner(IColorProvider colorProvider) { this.colorProvider = colorProvider; }
	public void setMaxValue(double maxValue) { this.maxValue = maxValue; }
	public void setMinValue(double minValue) { this.minValue = minValue; }
	public void setValue(double value) { this.value = value; }
	public void setBarWidthRatio(double barWidthRatio) { this.barWidthRatio = barWidthRatio; }
	public void setTitleRatio(double titleRatio) { this.titleRatio = titleRatio; }
	public void setBarLineWidth(int barLineWidth) {
		this.barLineWidth = barLineWidth;
		stroke = DefaultStrokeFactory.create(barLineWidth);
	}
}
