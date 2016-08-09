package ru.mironenko.cycles;

import java.io.ByteArrayOutputStream;

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
	
	private final StringBuilder out = new StringBuilder();
	
	
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
	*@param step
	 */

    ByteArrayOutputStream output =  new ByteArrayOutputStream();
    byte[] bytes;

	public void showDiapasone(int rangeOne, int rangeTwo, int step){

		for (int i = rangeOne; i <= rangeTwo; i = i + step){
			System.out.println(calculate(i));
            output.write((int)calculate(i));
		}
        bytes = output.toByteArray();
	}


}