package main.component;

public class ArrayMinMax {
	public static int arrayMin(int []arr){
		int result = arr[0];
		
		for(int i = 1; i < arr.length; i++){
			if(result > arr[i]) result = arr[i];
		}
		
		return result;
	}
	
	public static int arrayMax(int []arr){
		int result = arr[0];
		
		for(int i = 1; i < arr.length; i++){
			if(result < arr[i]) result = arr[i];
		}
		
		return result;
	}
}
