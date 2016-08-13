package ru.mironenko.testtask;

import java.util.Arrays;

/**
*Class TestTask for test is String origin contains String sub 
*@author mironenko
*@since 13.08.2016
*@version 1
*/

public class TestTask{
	/*
	*@param origin
	*@param sub
	*@return result return true if origin contains sub and false if not
	*
	*/
	
	
		public boolean contains(String origin, String sub){
			char[] charOrigin = origin.toCharArray();
			char[] charSub = sub.toCharArray();
			boolean result = false;
			
			for (int i = 0; i < charOrigin.length; i++){
			
			//create new char array and copy there elements if they are equals
			//then compair new array with sub array
				if (charOrigin[i] == charSub[0]){
					char[] charSubOrigin = new char[charSub.length];
					System.arraycopy(charOrigin, i, charSubOrigin, 0, charSubOrigin.length);
					result = Arrays.equals(charSubOrigin, charSub);
				}
				
			}
			return result;
		}
}