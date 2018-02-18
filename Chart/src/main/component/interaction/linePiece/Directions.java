package main.component.interaction.linePiece;

public enum Directions {
	TOP{
		public boolean isMovableX(){ return false; }
	}
	,BOTTOM{
		public boolean isMovableX(){ return false; }
	}
	,LEFT{
		public boolean isMovableY(){ return false; }
	}
	,RIGHT{
		public boolean isMovableY(){ return false; }
	}
	,LEFT_TOP
	,RIGHT_TOP
	,LEFT_BOTTOM
	,RIGHT_BOTTOM
	,LINE_START{
		@Override public boolean isAreal(){ return false; }
		@Override public boolean isLine(){ return true; }
	}
	,LINE_END{
		@Override public boolean isAreal(){ return false; }
		@Override public boolean isLine(){ return true; }
	}
	,VERTICAL_LINE_CENTER{
		public boolean isMovableY(){ return false; }
		@Override public boolean isAreal(){ return false; }
		@Override public boolean isLine(){ return true; }
	}
	,HORIZONTAL_LINE_CENTER{
		public boolean isMovableX(){ return false; }
		@Override public boolean isAreal(){ return false; }
		@Override public boolean isLine(){ return true; }
	}
	,NONE{
		@Override public boolean isAreal(){ return false; }
	}
	;
	
	public static int size(){ return values().length; }	
	public boolean isMovableX(){ return true; }
	public boolean isMovableY(){ return true; }
	public boolean isAreal(){ return true; }
	public boolean isLine(){ return false; }
}
