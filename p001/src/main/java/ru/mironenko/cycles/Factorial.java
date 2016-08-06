package ru.mironenko.cycles;

/*
*Class Factorial for calculation factorial
*@author mironenko
*@since 06.08.2016
*@version 1
*/

public class Factorial{
	/**
	*calculate factorial of number n
	*@param n number
	*@return result
	*/
	public int calculateFactorial(int n){
		 if (n < 0){
			 return -1;
		 }else{
			 int result = 1; 
			 for (int i = 1; i <= n; i++){
				result *= i;
			 }
			 return result;		 
		}
	}
	
}