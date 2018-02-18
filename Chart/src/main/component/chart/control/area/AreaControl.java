package main.component.chart.control.area;

import java.awt.Rectangle;

import main.component.chart.control.drawer.DrawerControl;

public class AreaControl extends AreaInfo{
	DrawerControl drawerControl = null;

	Rectangle titleArea 		= new Rectangle();
	Rectangle leftAxisArea 		= new Rectangle();
	Rectangle leftLegendArea 	= new Rectangle();
	Rectangle rightAxisArea 	= new Rectangle();
	Rectangle rightLegendArea 	= new Rectangle();
	Rectangle dataArea 			= new Rectangle();
	Rectangle bottomAxisArea 	= new Rectangle();
	Rectangle bottomLegendArea 	= new Rectangle();
	Rectangle footerArea 		= new Rectangle();
	Rectangle backgroundArea 	= new Rectangle();
		
	VerticalPartition verticalPartition = new VerticalPartition();
	HorizontalPartition horizontalPartition = new HorizontalPartition();
	
	public AreaControl(){
		setArea(new Rectangle(0, 0, 100, 100));
	}
		
	@Override
	public void setArea(Rectangle area) {
		super.setArea(area);
		onAreaChange();
	}
		
	protected void onAreaChange(){
		calculatePartitionAreas();
		if(drawerControl != null) drawerControl.setAreaControl(this);
	}
	
	public void setVerticalPartition(VerticalPartition verticalPartition){
		this.verticalPartition = verticalPartition;
		onAreaChange();
	}
	
	public void setHorizontalPartition(HorizontalPartition horizontalPartition){
		this.horizontalPartition = horizontalPartition;
		onAreaChange();
	}
		
	protected void calculatePartitionAreas(){
		calculateTitleArea();
		calculateLeftLegendArea();
		calculateLeftAxisArea();
		calculateDataArea();
		calculateRightAxisArea();
		calculateRightLegendArea();
		calculateBottomAxisArea();
		calculateBottomLegendArea();
		calculateFooterArea();
		calculateBackgroundArea();
	}
	
	protected void calculateTitleArea(){
		titleArea.x 		= horizontalPartition.getTitleX(area.x, area.width);
		titleArea.y 		= verticalPartition.getTitleY(area.y, area.height);
		titleArea.width 	= horizontalPartition.getTitleWidth(area.width);
		titleArea.height 	= verticalPartition.getTitleHeight(area.height);		
	}
	
	protected void calculateLeftLegendArea(){
		leftLegendArea.x 		= horizontalPartition.getLeftLegendX(area.x, area.width);
		leftLegendArea.y 		= verticalPartition.getLeftLegendY(area.y, area.height);
		leftLegendArea.width 	= horizontalPartition.getLeftLegendWidth(area.width);
		leftLegendArea.height 	= verticalPartition.getLeftLegendHeight(area.height);
	}
	
	protected void calculateLeftAxisArea(){
		leftAxisArea.x 		= horizontalPartition.getLeftAxisX(area.x, area.width);
		leftAxisArea.y 		= verticalPartition.getLeftAxisY(area.y, area.height);
		leftAxisArea.width 	= horizontalPartition.getLeftAxisWidth(area.width);
		leftAxisArea.height = verticalPartition.getLeftAxisHeight(area.height);
	}
	
	protected void calculateDataArea(){
		dataArea.x 		= horizontalPartition.getDataX(area.x, area.width);
		dataArea.y 		= verticalPartition.getDataY(area.y, area.height);
		dataArea.width 	= horizontalPartition.getDataWidth(area.width);
		dataArea.height = verticalPartition.getDataHeight(area.height);
	}
	
	protected void calculateRightAxisArea(){
		rightAxisArea.x 		= horizontalPartition.getRightAxisX(area.x, area.width);
		rightAxisArea.y 		= verticalPartition.getRightAxisY(area.y, area.height);
		rightAxisArea.width 	= horizontalPartition.getRightAxisWidth(area.width);
		rightAxisArea.height 	= verticalPartition.getRightAxisHeight(area.height);
	}
	
	protected void calculateRightLegendArea(){
		rightLegendArea.x 		= horizontalPartition.getRightLegendX(area.x, area.width);
		rightLegendArea.y 		= verticalPartition.getRightLegendY(area.y, area.height);
		rightLegendArea.width 	= horizontalPartition.getRightLegendWidth(area.width);
		rightLegendArea.height 	= verticalPartition.getRightLegendHeight(area.height);
	}

	protected void calculateBottomAxisArea(){
		bottomAxisArea.x 		= horizontalPartition.getBottomAxisX(area.x, area.width);
		bottomAxisArea.y 		= verticalPartition.getBottomAxisY(area.y, area.height);
		bottomAxisArea.width 	= horizontalPartition.getBottomAxisWidth(area.width);
		bottomAxisArea.height 	= verticalPartition.getBottomAxisHeight(area.height);
	}

	protected void calculateBottomLegendArea(){
		bottomLegendArea.x 		= horizontalPartition.getBottomLegendX(area.x, area.width);
		bottomLegendArea.y 		= verticalPartition.getBottomLegendY(area.y, area.height);
		bottomLegendArea.width 	= horizontalPartition.getBottomLegendWidth(area.width);
		bottomLegendArea.height = verticalPartition.getBottomLegendHeight(area.height);
	}

	protected void calculateFooterArea(){
		footerArea.x 		= horizontalPartition.getFooterX(area.x, area.width);
		footerArea.y 		= verticalPartition.getFooterY(area.y, area.height);
		footerArea.width 	= horizontalPartition.getFooterWidth(area.width);
		footerArea.height 	= verticalPartition.getFooterHeight(area.height);
	}
	
	protected void calculateBackgroundArea(){
		backgroundArea.x 		= horizontalPartition.getBackgroundX(area.x, area.width);
		backgroundArea.y 		= verticalPartition.getBackgroundY(area.y, area.height);
		backgroundArea.width 	= horizontalPartition.getBackgroundWidth(area.width);
		backgroundArea.height 	= verticalPartition.getBackgroundHeight(area.height);
	}
	
	public void setDrawerControl(DrawerControl drawerControl) { this.drawerControl = drawerControl; }
	
	public Rectangle getTitleArea() 			{ return titleArea; }
	public Rectangle getLeftAxisArea() 			{ return leftAxisArea; }
	public Rectangle getLeftLegendArea() 		{ return leftLegendArea; }
	public Rectangle getRightAxisArea() 		{ return rightAxisArea; }
	public Rectangle getRightLegendArea()		{ return rightLegendArea; }
	public Rectangle getDataArea() 				{ return dataArea; }
	public Rectangle getBottomAxisArea() 		{ return bottomAxisArea; }
	public Rectangle getBottomLegendArea() 		{ return bottomLegendArea; }
	public Rectangle getFooterArea() 			{ return footerArea; }
	public Rectangle getBackgroundArea() 		{ return backgroundArea; }
}
