package ru.mironenko.cycles;

/**
*Class Square for calculation function y = a*x2 + b * x + c
*@author mironenko
*@since 01.08.2016
*@version 1
*/

public class Square{
	
	private double a;
	private double b;
	private double c;
	
	private int rangeOne;
	private int rangeTwo;
	private int step;
	
	public Square (double a, double b, double c){
		this.a = a;
		this.b = b;
		this.c = c;		
	}

	
	/**
	*function evaluatio
	*@param x variable
	*@params a, b, c coefficients
	*@return result
	*/
	public float calculate(int x){
		return (float)(this.a * Math.pow(x, 2) + this.b * x + this.c);
		
	}
	
	/**
	*print value of function in diapasone beetween rangeOne and rangeTwo
	*@params rangeOne, rangeTwo diapasone
	 */
	public void showDiapasone(){

		for (int i = rangeOne; i <= rangeTwo; i = i + step){
			System.out.println(calculate(i));
		}
	}
	
	
}