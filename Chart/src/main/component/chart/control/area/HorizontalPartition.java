package main.component.chart.control.area;

public class HorizontalPartition {
	double leftLegend 	= 0.15;
	double leftAxis		= 0.1;
	double data 		= 0.65;
	double rightAxis 	= 0.1;
	double rightLegend 	= 0.0;
	
	public HorizontalPartition(){}
	
	public HorizontalPartition(HorizontalPartitionBuilder builder){
		this.leftLegend		= builder.leftLegend;
		this.leftAxis		= builder.leftAxis;
		this.data			= builder.data;
		this.rightAxis		= builder.rightAxis;
		this.rightLegend	= builder.rightLegend;
	}
	
	public int getTitleWidth(int width) 		{ return width; }
	public int getLeftLegendWidth(int width)	{ return (int)(width * leftLegend); }
	public int getLeftAxisWidth(int width)		{ return (int)(width * leftAxis); }
	public int getDataWidth(int width)			{ return (int)(width * data); }
	public int getRightAxisWidth(int width)		{ return (int)(width * rightAxis); }
	public int getRightLegendWidth(int width)	{ return (int)(width * rightLegend); }
	public int getBottomAxisWidth(int width)	{ return getDataWidth(width);}
	public int getBottomLegendWidth(int width) 	{ return getTitleWidth(width);}
	public int getFooterWidth(int width) 		{ return getTitleWidth(width);}
	public int getBackgroundWidth(int width) 	{ return getTitleWidth(width); }
	
	public int getTitleX(int x, int width) 			{ return (int)(x); }
	public int getLeftLegendX(int x, int width)		{ return (int)(x); }
	public int getLeftAxisX(int x, int width)		{ return (int)(getLeftLegendX(x, width) + getLeftLegendWidth(width) + 1); }
	public int getDataX(int x, int width)			{ return (int)(getLeftAxisX(x, width) + getLeftAxisWidth(width) + 1); }
	public int getRightAxisX(int x, int width)		{ return (int)(getDataX(x, width) + getDataWidth(width) + 1); }
	public int getRightLegendX(int x, int width)	{ return (int)(getRightAxisX(x, width) + getRightAxisWidth(width) + 1); }
	public int getBottomAxisX(int x, int width)		{ return getDataX(x, width);}
	public int getBottomLegendX(int x, int width) 	{ return getTitleX(x, width);}
	public int getFooterX(int x, int width) 		{ return getTitleX(x, width);}
	public int getBackgroundX(int x, int width) 	{ return getTitleX(x, width);}
	
	public static class HorizontalPartitionBuilder{
		double leftLegend;
		double leftAxis;
		double data;
		double rightAxis;
		double rightLegend;
		
		public HorizontalPartitionBuilder setLeftLegend(double leftLegend) {
			this.leftLegend = leftLegend;
			return this;
		}
		public HorizontalPartitionBuilder setLeftAxis(double leftAxis) {
			this.leftAxis = leftAxis;
			return this;
		}
		public HorizontalPartitionBuilder setData(double data) {
			this.data = data;
			return this;
		}
		public HorizontalPartitionBuilder setRightAxis(double rightAxis) {
			this.rightAxis = rightAxis;
			return this;
		}
		public HorizontalPartitionBuilder setRightLegend(double rightLegend) {
			this.rightLegend = rightLegend;
			return this;
		}
		
		public HorizontalPartition build() throws Exception{
			double sum = leftLegend + leftAxis + data + rightAxis + rightLegend;
			if(Math.abs(1.0 - sum) >= 0.0000001) throw new Exception();

			return new HorizontalPartition(this);
		}
	}
}
