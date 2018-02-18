package main.component.interaction;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.List;

public class Intersect {
	public static boolean intersects(List<Polygon> polygonList1, List<Polygon> polygonList2){
		for(int i = 0; i < polygonList1.size(); i++){
			for(int j = 0; j < polygonList2.size(); j++){
				if(intersects(polygonList1.get(i), polygonList2.get(j))) return true;
			}
		}
		
		return false;
	}
	
	public static boolean intersects(List<Polygon> polygonList1, Rectangle rect){
		for(int i = 0; i < polygonList1.size(); i++){
			if(intersects(polygonList1.get(i), rect)) return true;
		}
		
		return false;
	}
	
	public static boolean intersects(Polygon polygon, Rectangle rect){
		int []x1 = polygon.xpoints;
		int []y1 = polygon.ypoints;
		
		int x2[] = {rect.x, (int)rect.getMaxX(), (int)rect.getMaxX(), rect.x};
		int y2[] = {rect.y, rect.y, (int)rect.getMaxY(), (int)rect.getMaxY()};
		
		return intersects(x1, y1, x2, y2);
	}
	
	public static boolean intersects(Rectangle rect, Polygon polygon){
		return intersects(polygon, rect);
	}
		
	public static boolean intersects(Polygon polygon1, Polygon polygon2){
		int []x1 = polygon1.xpoints;
		int []y1 = polygon1.ypoints;
		
		int []x2 = polygon2.xpoints;
		int []y2 = polygon2.ypoints;
		
		return intersects(x1, y1, x2, y2);
	}
	
	public static boolean intersects(int[] x1, int[] y1, int[] x2, int[] y2){
		for(int i = 0; i < x1.length; i++){
			for(int j = 0; j < x2.length; j++){
				int iNext = (i + 1) % x1.length;
				int jNext = (j + 1) % x2.length;
				
				if(intersects(x1[i], y1[i], x1[iNext], y1[iNext], x2[j], y2[j], x2[jNext], y2[jNext])) return true;
			}
		}
		
		return false;		
	}
	
	public static boolean intersects(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4){
		float angle = (y4 - y3) * (x2- x1) - (x4 - x4) * (y2 - y1);
		if(angle == 0.0) return false;
		
		float ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / angle;
		float ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / angle;
		
		if(ua >= 0.0 && ua <= 1.0 && ub >= 0.0 && ub <= 1.0) return true;
		
		return false;
	}
}
