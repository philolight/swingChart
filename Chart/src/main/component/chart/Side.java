package main.component.chart;

public enum Side {
	LEFT(0)
	,RIGHT(1)
	,CENTER(2)
	;
	
	private int index;
	private Side(int index){ this.index = index; }
	public int index(){ return index; }
	public static int size(){
		return values().length;
	}
}
