package main.component.chart.drawer.data.label;

import main.component.chart.Side;
import main.component.chart.control.minMax.ZeroCenteredMinMaxControl;

public class ZeroCenteredLabelDrawer extends LabelDrawer{
	public ZeroCenteredLabelDrawer(Side side) { super(side);}

	@Override
	protected void setMinMaxControl(Side side){
		plotter.setMinMaxControl(new ZeroCenteredMinMaxControl(side));
	}
	
	@Override
	protected void calculateLabelPointY(int y, double value){
		if(value >= 0.0) setPositiveLabelPointY(y);
		else 			 setNegativeLabelPointY(y);

	}
	
	private void setPositiveLabelPointY(int y){
		switch(positionType){
		case TOP:
			labelPoint.y = y - 2;
			labelPoint.y = labelPoint.y < plotter.top + textHeight ? plotter.top + textHeight : labelPoint.y;
			break;
		case CENTER:
			labelPoint.y = (y + plotter.centerY + textHeight) / 2 ;
			labelPoint.y = labelPoint.y > plotter.centerY ? plotter.centerY : labelPoint.y;
			
			break;
		case BOTTOM:
			labelPoint.y = plotter.centerY + textHeight;
			break;
		default:
			break;
		}		
	}
	
	private void setNegativeLabelPointY(int y){
		switch(positionType){
		case TOP:
			labelPoint.y = y + textHeight;
			labelPoint.y = labelPoint.y > plotter.bottom ? plotter.bottom : labelPoint.y;
		break;
		case CENTER:
			labelPoint.y = (y + plotter.centerY + textHeight) / 2 ;
			labelPoint.y = labelPoint.y < plotter.centerY ? plotter.centerY : labelPoint.y;
			break;
		case BOTTOM:
			labelPoint.y = plotter.centerY - 2;
			break;
		default:
			break;
		}		
	}
}
