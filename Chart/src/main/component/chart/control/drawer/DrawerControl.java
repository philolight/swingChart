package main.component.chart.control.drawer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import main.component.chart.Side;
import main.component.chart.control.area.AreaControl;
import main.component.chart.control.data.DataControl;
import main.component.chart.control.flow.FlowControl;
import main.component.chart.control.minMax.MinMaxControl;
import main.component.chart.data.IMetaDataSet;
import main.component.chart.data.MetaDataSet;
import main.component.chart.drawer.Drawer;
import main.component.chart.drawer.IDrawer;
import main.component.chart.drawer.axis.AxisDrawer;
import main.component.chart.drawer.axis.BottomAxisDrawer;
import main.component.chart.drawer.axis.LeftAxisDrawer;
import main.component.chart.drawer.axis.RightAxisDrawer;
import main.component.chart.drawer.background.BackgroundDrawer;
import main.component.chart.drawer.background.DataBackgroundDrawer;
import main.component.chart.drawer.data.DataDrawer;
import main.component.chart.drawer.footer.FooterDrawer;
import main.component.chart.drawer.legend.BottomLegendDrawer;
import main.component.chart.drawer.legend.LeftLegendDrawer;
import main.component.chart.drawer.legend.LegendDrawer;
import main.component.chart.drawer.legend.NullLegendDrawer;
import main.component.chart.drawer.legend.RightLegendDrawer;
import main.component.chart.drawer.title.TitleDrawer;
import main.component.chart.setting.SettingControl;
import main.component.chart.theme.Theme;

public class DrawerControl {
	
	protected AreaControl areaControl;
	protected DataControl dataControl = new DataControl();
	protected FlowControl flowControl;
	protected SettingControl settingControl;
	protected Theme theme;
	
	protected List<IDrawer> drawersWithoutBackground = new ArrayList<IDrawer>();
	protected List<Drawer> drawers = new ArrayList<Drawer>();
	protected List<DataDrawer> soloDataDrawers = new ArrayList<DataDrawer>();
	
	protected BufferedImage backImage = null;
	protected BufferedImage finalImage = null;
	
	protected Graphics2D drawerGraphics = null;
	
	protected BackgroundDrawer backgroundDrawer = new BackgroundDrawer();
	protected DataBackgroundDrawer dataBackgroundDrawer = new DataBackgroundDrawer();
	
	protected TitleDrawer titleDrawer = new TitleDrawer();
	protected AxisDrawer bottomAxisDrawer = new BottomAxisDrawer();
	protected LegendDrawer bottomLegendDrawer = new NullLegendDrawer();
	protected FooterDrawer footerDrawer = new FooterDrawer();

	protected AxisDrawer leftAxisDrawer = new LeftAxisDrawer();
	protected LegendDrawer leftLegendDrawer = new LeftLegendDrawer();
	
	protected AxisDrawer rightAxisDrawer = new RightAxisDrawer();
	protected LegendDrawer rightLegendDrawer = new NullLegendDrawer();
		
	public DrawerControl(FlowControl flowControl){
		drawersWithoutBackground.add(leftAxisDrawer);
		drawersWithoutBackground.add(rightAxisDrawer);
		drawersWithoutBackground.add(bottomAxisDrawer);
		drawersWithoutBackground.add(titleDrawer);
		drawersWithoutBackground.add(footerDrawer);
		drawersWithoutBackground.add(leftLegendDrawer);
		drawersWithoutBackground.add(rightLegendDrawer);
		drawersWithoutBackground.add(bottomLegendDrawer);
		
		drawers.add(backgroundDrawer);
		drawers.add(dataBackgroundDrawer);
		drawers.add(leftAxisDrawer);
		drawers.add(rightAxisDrawer);
		drawers.add(bottomAxisDrawer);
		drawers.add(titleDrawer);
		drawers.add(footerDrawer);
		drawers.add(leftLegendDrawer);
		drawers.add(rightLegendDrawer);
		drawers.add(bottomLegendDrawer);
			
		setFlowControl(flowControl);
		initMinMaxControl();
		initMetaData();
	}
	
	protected MinMaxControl totalMinMaxControl;
	protected MinMaxControl leftMinMaxControl;
	protected MinMaxControl rightMinMaxControl;

	protected void newMinMaxControl(){
		totalMinMaxControl = new MinMaxControl(Side.CENTER);
		leftMinMaxControl = new MinMaxControl(Side.LEFT);
		rightMinMaxControl = new MinMaxControl(Side.RIGHT);
	}
	
	protected void initMinMaxControl(){
		newMinMaxControl();
		
		titleDrawer.setMinMaxControl(totalMinMaxControl);
		backgroundDrawer.setMinMaxControl(totalMinMaxControl);
		dataBackgroundDrawer.setMinMaxControl(totalMinMaxControl);
		bottomAxisDrawer.setMinMaxControl(totalMinMaxControl);
		footerDrawer.setMinMaxControl(totalMinMaxControl);
		bottomLegendDrawer.setMinMaxControl(totalMinMaxControl);
		
		leftAxisDrawer.setMinMaxControl(leftMinMaxControl);
		leftLegendDrawer.setMinMaxControl(leftMinMaxControl);
		
		rightAxisDrawer.setMinMaxControl(rightMinMaxControl);
		rightLegendDrawer.setMinMaxControl(rightMinMaxControl);
	}
	
	protected void initMetaData(){
		MetaDataSet total = dataControl.getTotalMetaData();
		totalMinMaxControl.setMetaData(total);
		dataBackgroundDrawer.setMetaData(total);
		bottomAxisDrawer.setMetaData(total);
		footerDrawer.setMetaData(total);
		bottomLegendDrawer.setMetaData(total);
		
		MetaDataSet left = dataControl.getLeftMetaData();
		leftMinMaxControl.setMetaData(left);
		leftAxisDrawer.setMetaData(left);
		leftLegendDrawer.setMetaData(left);
		
		MetaDataSet right = dataControl.getRightMetaData();
		rightMinMaxControl.setMetaData(right);
		rightAxisDrawer.setMetaData(right);
		rightLegendDrawer.setMetaData(right);
	}
	
	protected void addDrawerCommon(DataDrawer drawer, IMetaDataSet metaDataSet){
		soloDataDrawers.add(drawer);
		drawer.setFlowControl(flowControl);
		drawer.setMetaData(metaDataSet);
		drawersWithoutBackground.add(drawer);
		drawers.add(drawer);
		bottomLegendDrawer.add(drawer);
		drawer.setSettingControl(settingControl);
		drawer.setFlowControl(flowControl);
		drawer.setAreaControl(areaControl);
		drawer.setGraphics2D(drawerGraphics);
	}
		
	public void addLeft(DataDrawer drawer, IMetaDataSet metaDataSet){
		addDrawerCommon(drawer, metaDataSet);
		leftLegendDrawer.add(drawer);
		leftAxisDrawer.add(drawer);
		dataControl.addLeft(metaDataSet);
	}
	
	public void addRight(DataDrawer drawer, IMetaDataSet metaDataSet){
		addDrawerCommon(drawer, metaDataSet);
		rightLegendDrawer.add(drawer);
		rightAxisDrawer.add(drawer);
		dataControl.addRight(metaDataSet);
	}

	public void drawAll(Graphics g) {
		dataBackgroundDrawer.draw();
		drawerGraphics.drawImage(backImage, 0, 0, areaControl.width-1, areaControl.height-1, 0, 0, areaControl.width-1, areaControl.height-1, null);
		drawersWithoutBackground.forEach(drawer -> drawer.draw());
		g.drawImage(finalImage, 0, 0, null);
	}
	
	public void setAreaControl(AreaControl areaControl) {
		this.areaControl = areaControl;
		drawers.forEach(item -> item.setAreaControl(areaControl));
		
		initImages();
		setGraphics();
	}
	
	protected void initImages(){
		backImage = new BufferedImage(areaControl.width, areaControl.height, BufferedImage.TYPE_INT_RGB);
		finalImage = new BufferedImage(areaControl.width, areaControl.height, BufferedImage.TYPE_INT_RGB);
	}
	
	protected void setGraphics(){
		if(backImage == null) return;

		initDrawerGraphics();		
		initBackgroundGraphics();
	}
	
	protected void initDrawerGraphics(){
		drawerGraphics = (Graphics2D) finalImage.createGraphics();
		drawersWithoutBackground.forEach( drawer -> drawer.setGraphics2D(drawerGraphics));
	}
	
	protected void initBackgroundGraphics(){
		Graphics2D backgroundGraphics = (Graphics2D) backImage.createGraphics();
		backgroundGraphics.setColor(Color.WHITE);
		backgroundGraphics.fillRect(0, 0, areaControl.width-1, areaControl.height-1);
		backgroundDrawer.setGraphics2D(backgroundGraphics);
		backgroundDrawer.draw();
		dataBackgroundDrawer.setGraphics2D(backgroundGraphics);
	}
	
	public void setTheme(Theme theme){
		this.theme = theme;
		drawers.forEach(item -> item.setTheme(theme));
		backgroundDrawer.draw();
	}

	public void setFlowControl(FlowControl flowControl) {
		this.flowControl = flowControl;
		drawers.forEach(item -> item.setFlowControl(flowControl));
	}

	public void setSettingControl(SettingControl settingControl) {
		this.settingControl = settingControl;
		drawers.forEach(item -> item.setSettingControl(settingControl));
			
		if(settingControl.isLeftLegend()){
			enableLeftLegend();
			disableBottomLegend();
		}
		
		if(settingControl.isRightLegend()){
			enableRightLegend();
			disableBottomLegend();
		}
		
		if(settingControl.isBottomLegend()){
			enableBottomLegend();
			disableLeftLegend();
			disableRightLegend();
		}
	}

	public void reset(){
		// 아래는 순서가 중요함
		soloDataDrawers.forEach(each -> each.reset());
		dataControl.reset();
		totalMinMaxControl.reset();
		leftMinMaxControl.reset();
		rightMinMaxControl.reset();
	}
	
	public void onUpdateData() {
		// 아래는 순서가 중요함
		soloDataDrawers.forEach(each -> each.onUpdateData());
		dataControl.onUpdateData();
		totalMinMaxControl.onUpdateData();
		leftMinMaxControl.onUpdateData();
		rightMinMaxControl.onUpdateData();
	}
	
	protected void enableLegendCommon(LegendDrawer legendDrawer, 
			MinMaxControl minMaxControl,
			IMetaDataSet metaDataSet
			){
		legendDrawer.setFlowControl(flowControl);
		legendDrawer.setMinMaxControl(minMaxControl);
		legendDrawer.setSettingControl(settingControl);
		if(areaControl != null) legendDrawer.setAreaControl(areaControl);		
		legendDrawer.setMetaData(metaDataSet);
		legendDrawer.setGraphics2D(drawerGraphics);
		if(theme != null) legendDrawer.setTheme(theme);
		
		drawersWithoutBackground.add(legendDrawer);
		drawers.add(legendDrawer);
	}
	
	protected void enableBottomLegend(){
		bottomLegendDrawer = new BottomLegendDrawer();
		IMetaDataSet metaDataSet = dataControl.getTotalMetaData();
		
		enableLegendCommon(bottomLegendDrawer, totalMinMaxControl, metaDataSet);
		
		for(int i = 0; i < soloDataDrawers.size(); i++){ bottomLegendDrawer.add(soloDataDrawers.get(i)); }
	}
	
	protected void disableBottomLegend(){
		drawersWithoutBackground.remove(bottomLegendDrawer);
		drawers.remove(bottomLegendDrawer);
		bottomLegendDrawer = new NullLegendDrawer();
		if(theme != null) bottomLegendDrawer.setTheme(theme);
	}
	
	protected void enableLeftLegend(){
		leftLegendDrawer = new LeftLegendDrawer();
		IMetaDataSet metaDataSet = dataControl.getLeftMetaData();
		
		enableLegendCommon(leftLegendDrawer, leftMinMaxControl, metaDataSet);
				
		for(int i = 0; i < soloDataDrawers.size(); i++){
			Side side = soloDataDrawers.get(i).getSide();
			if(side == Side.LEFT) leftLegendDrawer.add(soloDataDrawers.get(i)); 
		}
	}
	
	protected void disableLeftLegend(){
		drawersWithoutBackground.remove(leftLegendDrawer);
		drawers.remove(leftLegendDrawer);
		leftLegendDrawer = new NullLegendDrawer();
		if(theme != null) leftLegendDrawer.setTheme(theme);
	}
	
	protected void disableRightLegend(){
		drawersWithoutBackground.remove(rightLegendDrawer);
		drawers.remove(rightLegendDrawer);
		rightLegendDrawer = new NullLegendDrawer();
		if(theme != null) rightLegendDrawer.setTheme(theme);
	}
	
	protected void enableRightLegend(){
		rightLegendDrawer = new RightLegendDrawer();
		IMetaDataSet metaDataSet = dataControl.getRightMetaData();
		
		enableLegendCommon(rightLegendDrawer, rightMinMaxControl, metaDataSet);
		
		for(int i = 0; i < soloDataDrawers.size(); i++){
			Side side = soloDataDrawers.get(i).getSide();
			if(side == Side.RIGHT) rightLegendDrawer.add(soloDataDrawers.get(i)); 
		}
	}

	public BottomAxisDrawer getBottomAxisDrawer() {
		return (BottomAxisDrawer)bottomAxisDrawer;
	}	
}