package main.component.chart.data;

public interface IMetaDataSet{
	public static final IMetaDataSet NULL = new NullMetaData();
	public int size();
	public double[] get(int index);
	public double getMinBetween(int leftIndex, int rightIndex);
	public double getMaxBetween(int leftIndex, int rightIndex);
	public double getGlobalMin();
	public double getGlobalMax();
	public String getName(int index);
	public void onUpdateData();
	public int getSetSize();
	public void setName(int i, String name);
	public void reset();
}
