package main.component.chart.data;

public class NullMetaData implements IMetaDataSet{
	double []valueSet = {};
	@Override	public	String getName(int index) { return "NULL"; }
	@Override	public int size() { return 0; }
	@Override	public double[] get(int i) { return valueSet; }
	@Override	public void onUpdateData() {}
	@Override	public double getMinBetween(int leftIndex, int rightIndex) { return 0; }
	@Override	public double getMaxBetween(int leftIndex, int rightIndex) { return 0; }
	@Override	public double getGlobalMin() { return 0; }
	@Override	public double getGlobalMax() { return 0; }
	@Override	public int getSetSize() { return 0; }
	@Override	public void setName(int i, String name) {}
	@Override	public void reset() {}
}
