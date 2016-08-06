package ru.mironenko.arrays;

/**
*Class BubbleSort for sort array 
*@author mironenko
*@since 06.08.2016
*@version 1
*/

public class BubbleSort{
	
	public void bubbleSort(int [] arr){
		
		for (int i = arr.length - 1; i >= 0; i--){
			for (int j = 0; j < i; j++){
				if (arr[j] > arr[j + 1]){
					int tmp = arr [j];
					arr[j] = arr[j + 1];
					arr[j+1] = tmp;
				}
			}
		}
	}
}