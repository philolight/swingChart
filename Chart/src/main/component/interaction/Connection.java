package main.component.interaction;

import main.component.interaction.marker.IMark;

public class Connection {
	LinePiece linePiece;
	PlanePiece planePiece;
	IMark lineMark;
	IMark planeMark;
	
	public void setConnection(LinePiece linePiece, PlanePiece planePiece, IMark lineMark, IMark planeMark){
		this.linePiece = linePiece;
		this.planePiece = planePiece;
		
		this.lineMark = lineMark;
		this.planeMark = planeMark;
	}
}
