package main.component.chart.drawer.data.bar;

import main.component.chart.Side;
import main.component.chart.control.minMax.ZeroCenteredMinMaxControl;

public class ZeroCenteredBarDrawer extends BarDrawer{
	
	public ZeroCenteredBarDrawer(Side side) { super(side); }

	@Override
	protected void setMinMaxControl(Side side){
		plotter.setMinMaxControl(new ZeroCenteredMinMaxControl(side));
	}

	@Override
	protected void drawData(int index) {
		int x = plotter.getDataPositionX(index);
		double[] values = plotter.getData(index);
		int[] y = plotter.getDataPositionY(index);

		int xStart = x - barWidth / 2;
		int xWidth = barWidth / values.length + barOverlapWidth;
		for(int series = 0; series < values.length; series++){
			int height = Math.abs(plotter.centerY - y[series]);
			y[series] = (y[series] < plotter.centerY)? y[series] : plotter.centerY;
			
			g.setPaint(colorProvider.getNormalColor(index, series, values[series]));
			g.fillRect(xStart + (xWidth - 2) * series, y[series], xWidth, height);
			
			g.setPaint(colorProvider.getBrightColor(index, series, values[series]));
			g.setStroke(stroke);
			g.drawRect(xStart + (xWidth - 2) * series, y[series], xWidth, height);
		}
	}
}
