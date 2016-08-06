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
	private int rangeOne;
	private int rangeTwo;
	private int step;
	

	public Square(float a, float b, float c, int x, int y, int z){
	
		a = this.a;
		b = this.b;
		c = this.c;
		x = this.rangeOne;
		y = this.rangeTwo;
		z = this.step;
		
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

		for (int i = this.rangeOne; i <= this.rangeTwo; i = i + this.step){
			System.out.println(calculate(i));
		}
	}
	
	
}