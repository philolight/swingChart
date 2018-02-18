package main.component.interaction;

import java.awt.Polygon;

public class ToString {
	public static String toLine(Polygon polygon){
		String str = "";
		for(int i = 0; i < polygon.npoints; i++){
			str += "(" + polygon.xpoints[i] + "," + polygon.ypoints[i] + ")";
		}
		
		return str;
	}
}
