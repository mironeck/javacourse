package ru.mironenko.cycles;

/*
*Class Factorial for calcilation factorial
*@author mironenko
*@since 06.08.2016
*@version 1
*/

public class Factorial{
	
	private int n;
	
	public Factorial(int n){
		if (n < 0){
			System.out.println("Enter n >= 0");
		}else{
			n = this.n;
		}
	}
	
	private int calculateFactorial(int n){
		int x = 1;
		for (int i = 1; i <= this.n; i++){
			x = x * i;
		}
		return x;
	}
	
}