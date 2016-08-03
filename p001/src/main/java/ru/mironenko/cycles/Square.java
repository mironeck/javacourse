package ru.mironenko.cycles;

/**
*Class Square for calculation function y = a*x2 + b * x + c
*@author mironenko
*@since 01.08.2016
*@version 1
*/

public class Square{
 
	private float a;
	private float b;
	private float c;

	public Square(float a, float b, float c){
	
		a = this.a;
		b = this.b;
		c = this.c;
	}
	
	/**
	*function evaluatio
	*@param x variable
	*@param a,b,c coefficients
	*@return result
	*/
	private float calculate(int x){
		return a * x * x + b * x + c;
	}
	
	public float getA(){
		return a;
	}

	public float getB(){
		return b;
	}
	
	public float getC(){
		return c;
	}
	
}