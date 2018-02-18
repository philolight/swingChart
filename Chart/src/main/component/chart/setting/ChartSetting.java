package main.component.chart.setting;

import main.component.chart.Side;
import main.component.chart.data.IMetaDataSet;
import main.component.chart.data.MetaDataSet;
import main.component.chart.drawer.data.DataDrawer;
import main.component.chart.drawer.data.bar.BarDrawer;

public class ChartSetting {
	private boolean isLeftAxis = true;
	private boolean isRightAxis = false;
	private boolean isBottomLegend = false;
	private DataDrawer leftDataDrawer = new BarDrawer(Side.LEFT);
	private DataDrawer rightDataDrawer = new BarDrawer(Side.LEFT);
	
	private MetaDataSet leftMetaDataSet = new MetaDataSet();
	private MetaDataSet rightMetaDataSet = new MetaDataSet();
	
	public boolean isLeftAxis() { return isLeftAxis; }
	public boolean isRightAxis() { return isRightAxis; }
	public boolean isBottomLegend() { return isBottomLegend; }
	public DataDrawer getLeftDataDrawer() { return leftDataDrawer; }
	public DataDrawer getRightDataDrawer() { return rightDataDrawer; }
	public MetaDataSet getLeftMetaDataSet() { return leftMetaDataSet; }
	public MetaDataSet getRightMetaDataSet() { return rightMetaDataSet; }

	public void setLeftAxis(boolean isLeftAxis) { this.isLeftAxis = isLeftAxis; onChange();}
	public void setRightAxis(boolean isRightAxis) { this.isRightAxis = isRightAxis; onChange();}
	public void setBottomLegend(boolean isBottomLegend) { this.isBottomLegend = isBottomLegend; onChange();}
	public void setLeftDataDrawer(DataDrawer leftDataDrawer) { this.leftDataDrawer = leftDataDrawer; onChange();}
	public void setRightDataDrawer(DataDrawer rightDataDrawer) { this.rightDataDrawer = rightDataDrawer; onChange();}
	
	public void addLeft(IMetaDataSet... metaDatas){
		for(IMetaDataSet each : metaDatas) leftMetaDataSet.addMetaData(each);
		onChange();
	}
	
	public void addRight(IMetaDataSet... metaDatas){
		for(IMetaDataSet each : metaDatas) rightMetaDataSet.addMetaData(each);
		onChange();
	}
	
	private void onChange(){
		if(isLeftAxis == true) leftDataDrawer.setMetaData(leftMetaDataSet);
		if(isRightAxis == true) rightDataDrawer.setMetaData(rightMetaDataSet);
	}
}
