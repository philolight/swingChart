package main.component.chart.control.area;

public class VerticalPartition {
	double title 		= 0.1;
	double gapBetweenTitleAndData 	= 0.05;
	double data 		= 0.65;
	double bottomAxis 	= 0.05;
	double footer 		= 0.05;
	double bottomLegend = 0.1;
	
	public VerticalPartition(){}
	
	public VerticalPartition(VerticalPartitionBuilder builder){
		this.title 			= builder.title;
		this.gapBetweenTitleAndData = builder.gapBetweenTitleAndData;
		this.data 			= builder.data;
		this.bottomAxis		= builder.bottomAxis;
		this.bottomLegend 	= builder.bottomLegend;
		this.footer 		= builder.footer;
	}
	
	public int getRemainedHeight(int height){
		return height 
				- getTitleHeight(height) 
				- getDataHeight(height) 
				- getBottomAxisHeight(height) 
				- getBottomLegendHeight(height) 
				- getFooterHeight(height);
	}
	
	public int getTitleHeight(int height)			{ return (int)(height * title); }
	public int getGapBetweenTitleAndDataHeight(int height)	{ return getRemainedHeight(height); }
	public int getDataHeight(int height)			{ return (int)(height * data); }
	public int getBottomAxisHeight(int height)		{ return (int)(height * bottomAxis); }
	public int getBottomLegendHeight(int height)	{ return (int)(height * bottomLegend); }
	public int getFooterHeight(int height)			{ return (int)(height * footer); }
	public int getLeftAxisHeight(int height) 		{ return getDataHeight(height);}
	public int getLeftLegendHeight(int height) 		{ return getDataHeight(height);}
	public int getRightAxisHeight(int height) 		{ return getDataHeight(height);}
	public int getRightLegendHeight(int height) 	{ return getDataHeight(height);}
	public int getBackgroundHeight(int height) 		{ return height - getTitleHeight(height) - getFooterHeight(height); }
	
	public int getTitleY(int y, int height)			{ return y; }
	public int getGapBetweenTitleAndDataY(int y, int height)	{ return (int)(getTitleY(y, height) + getTitleHeight(height)); }
	public int getDataY(int y, int height)			{ return (int)(getTitleY(y, height) + getTitleHeight(height) + getGapBetweenTitleAndDataHeight(height)); }
	public int getBottomAxisY(int y, int height)	{ return (int)(getDataY(y, height) + getDataHeight(height)); }
	public int getBottomLegendY(int y, int height)	{ return (int)(getBottomAxisY(y, height) + getBottomAxisHeight(height)); }
	public int getFooterY(int y, int height)		{ return (int)(getBottomLegendY(y, height) + getBottomLegendHeight(height)); }
	public int getLeftAxisY(int y, int height) 		{ return getDataY(y, height);}
	public int getLeftLegendY(int y, int height) 	{ return getDataY(y, height);}
	public int getRightAxisY(int y, int height) 	{ return getDataY(y, height);}
	public int getRightLegendY(int y, int height) 	{ return getDataY(y, height);}
	public int getBackgroundY(int y, int height) 	{ return getGapBetweenTitleAndDataY(y, height);}
	
	public static class VerticalPartitionBuilder{
		double title;
		double gapBetweenTitleAndData;
		double data;
		double bottomAxis;
		double bottomLegend;
		double footer;
		
		public VerticalPartitionBuilder setTitle(double title) {
			this.title = title;
			return this;
		}
		
		public VerticalPartitionBuilder setGapBetweenTitleAndData(double gapBetweenTitleAndData) {
			this.gapBetweenTitleAndData = gapBetweenTitleAndData;
			return this;
		}

		public VerticalPartitionBuilder setData(double data) {
			this.data = data;
			return this;
		}
		
		public VerticalPartitionBuilder setBottomAxis(double bottomAxis) {
			this.bottomAxis = bottomAxis;
			return this;
		}

		public VerticalPartitionBuilder setBottomLegend(double bottom) {
			this.bottomLegend = bottom;
			return this;
		}

		public VerticalPartitionBuilder setFooter(double footer) {
			this.footer = footer;
			return this;
		}
		
		public VerticalPartition build() throws Exception{
			double sum = title + gapBetweenTitleAndData + data + bottomAxis + bottomLegend + footer;
			if(Math.abs(1.0 - sum) >= 0.0000001) throw new Exception();

			return new VerticalPartition(this);
		}
	}
}
