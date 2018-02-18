package main.component.interaction;

import java.awt.Polygon;
import java.util.List;

public class Contains {
	public static boolean contains(List<Polygon> contains, List<Polygon> contained){
		int points = 0;
		for(Polygon item : contained){
			points += item.npoints;
		}
		int x[] = new int[points];
		int y[] = new int[points];
		boolean isContained[] = new boolean[points];
		
		for(int i = 0; i < points; i++) isContained[i] = false;
		
		int index = 0;
		
		for(Polygon item : contained){
			for(int i = 0; i < item.npoints; i++){	
				x[index] = item.xpoints[i];
				y[index] = item.ypoints[i];
				
				index++;
			}
		}
		for(Polygon container : contains){		
			for(int i = 0; i < points; i++){
				if(container.contains(x[i], y[i])){
					isContained[i] = true;
				}
			}
		}
		
		for(int i = 0; i < points; i++){
			if(isContained[i] == false)	return false;
		}
		
		return true;
	}
}
