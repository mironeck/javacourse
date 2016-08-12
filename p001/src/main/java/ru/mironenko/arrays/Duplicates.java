package ru.mironenko.arrays;

import java.util.Arrays;

/**
*Class Duplicates for delete duplicates 
*@author mironenko
*@since 11.08.2016
*@version 1
*/

public class Duplicates{
	/*
	*delete duplicates from String array
	*@param arrayWithDuplicates
	*@param numberOfUniqueElements
	*creat new array with length of arrayWithDuplicates
	*if find duplicate replace it with last unique element
	*and decrement numberOfUniqueElements
	*then creat new array with length of numberOfUniqueElements
	*/
	public String[] deleteDuplicates(String[] arrayWithDuplicates){
		
		int numberOfUniqueElements = arrayWithDuplicates.length;
		
		for (int i = 0; i < numberOfUniqueElements; i++){
			for (int j = i + 1; j < numberOfUniqueElements; j++){
				if (arrayWithDuplicates[i].equals(arrayWithDuplicates[j])){
					arrayWithDuplicates[j] = arrayWithDuplicates[numberOfUniqueElements - 1];
					numberOfUniqueElements--;
					j--;
				}
			}
		}
		
		
		String[] arrayWithoutDuplicates = new String[numberOfUniqueElements];
		for (int i = 0; i < numberOfUniqueElements; i++){
			arrayWithoutDuplicates[i] = arrayWithDuplicates[i];
		}
		
		return arrayWithoutDuplicates;
	}
}