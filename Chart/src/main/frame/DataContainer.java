package main.frame;

import java.util.ArrayList;
import java.util.List;

public class DataContainer {
	public List<Integer> scrollList1 = new ArrayList<Integer>();
	public List<Integer> scrollList2 = new ArrayList<Integer>();
	public List<Integer> scrollList3 = new ArrayList<Integer>();
	public List<Integer> zeroCenteredList1 = new ArrayList<Integer>();
	public List<Integer> zeroCenteredList2 = new ArrayList<Integer>();
	public List<Integer> fixedSizeList1 = new ArrayList<Integer>();
	public List<Integer> fixedSizeList2 = new ArrayList<Integer>();
	public List<Integer> changeColorList1 = new ArrayList<Integer>();
	public List<Integer> changeColorList2 = new ArrayList<Integer>();
	
	public List<Integer> compareBarList1 = new ArrayList<Integer>();
	public List<Integer> compareBarList2 = new ArrayList<Integer>();
	public List<Integer> compareBarList3 = new ArrayList<Integer>();
	
	public List<Integer> compareZeroCenteredBarList1 = new ArrayList<Integer>();
	public List<Integer> compareZeroCenteredBarList2 = new ArrayList<Integer>();
	
	public List<Integer> rainbowList = new ArrayList<Integer>();
	
	public List<Integer> envelopList1 = new ArrayList<Integer>();
	public List<Integer> envelopList2 = new ArrayList<Integer>();
	public List<Integer> envelopList3 = new ArrayList<Integer>();
	
	public List<Integer> textLabelList = new ArrayList<Integer>();
	
	public List<Integer> valuedColorList = new ArrayList<Integer>();
	
	public List<Integer> indexConditionList = new ArrayList<Integer>();
	
	public DataContainer(){
		for(int i = 0; i < 24; i++){
			fixedSizeList1.add((int)(Math.random()*100));
			fixedSizeList2.add((int)(Math.random()*100));
		}
		
		scrollList1.add((int)(Math.random()*100));
		scrollList2.add((int)(Math.random()*100));
		scrollList3.add((int)(Math.random()*100));
		
		compareBarList1.add((int)(Math.random()*100));
		compareBarList2.add((int)(Math.random()*100));
		compareBarList3.add((int)(Math.random()*100));
		
		envelopList2.add((int)(Math.random()*100));
		envelopList1.add(envelopList2.get(0) + (int)(Math.random()*30));
		envelopList3.add(envelopList2.get(0) - (int)(Math.random()*30));
		
		for(int i = 0; i < 12; i++) textLabelList.add((int)(Math.random()*100));
		
		for(int i = 0; i < 24; i++){
			indexConditionList.add((int)(Math.random() * 3));
		}
	}
	
	public void onUpdateData(){
		for(int i = 0; i < 24; i++){
			fixedSizeList1.set(i, fixedSizeList1.get(i) + (int)(Math.random() * 20 - 10));
			fixedSizeList2.set(i, fixedSizeList2.get(i) + (int)(Math.random() * 20 - 10));
		}
		
		zeroCenteredList1.add((int)((Math.random() - 0.5) * 100));
		zeroCenteredList2.add((int)((Math.random() - 0.5) * 100));
		
		scrollList1.add((int)((Math.random() - 0.5) * 10 + scrollList1.get(scrollList1.size()-1)));
		scrollList2.add((int)((Math.random() - 0.5) * 10 + scrollList2.get(scrollList2.size()-1)));
		scrollList3.add((int)((Math.random() - 0.5) * 10 + scrollList3.get(scrollList3.size()-1)));
		
		changeColorList1.add((int)((Math.random() - 0.5) * 100));
		changeColorList2.add((int)((Math.random() - 0.5) * 100));
		
		compareBarList1.add((int)((Math.random() - 0.5) * 100));
		compareBarList2.add((int)((Math.random() - 0.5) * 100));
		compareBarList3.add((int)((Math.random() - 0.5) * 100));
		
		compareZeroCenteredBarList1.add((int)((Math.random() - 0.5) * 100));
		compareZeroCenteredBarList2.add((int)((Math.random() - 0.5) * 100));
		
		rainbowList.add((int)((Math.random() - 0.5) * 100));
		
		envelopList2.add((int)((Math.random() - 0.5) * 10) + envelopList2.get(envelopList2.size()-1));
		envelopList1.add((int)((Math.random() - 0.5) * 10) + envelopList1.get(envelopList1.size()-1));
		if(envelopList2.get(envelopList2.size()-1) > envelopList1.get(envelopList1.size()-1)){
			envelopList1.set(envelopList1.size()-1, envelopList2.get(envelopList2.size()-1) + (int)(Math.random() * 5));
		}
		envelopList3.add((int)((Math.random() - 0.5) * 10) + envelopList3.get(envelopList3.size()-1));
		if(envelopList2.get(envelopList2.size()-1) < envelopList3.get(envelopList3.size()-1)){
			envelopList3.set(envelopList3.size()-1, envelopList2.get(envelopList2.size()-1) - (int)(Math.random() * 5));
		}
		
		for(int i = 0; i < 12; i++){
			textLabelList.add(i, textLabelList.get(i) + (int)(Math.random() * 20 - 10));
		}
		
		valuedColorList.add((int)(Math.random() * 100));
		
		for(int i = 0; i < 24; i++)
			indexConditionList.set(i, (int)(Math.random() * 3));
	}
}
