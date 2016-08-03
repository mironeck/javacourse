package ru.mironenko.jvmbytecode;


public class CalcInit{

	public static void main(String [] args){
	
	Calculator calk =  new Calculator();
	double a = 1;
	double b = 2;
	
	calk.add(a, b);
	System.out.println("add " + a + " and " + b + " is "+calk.getResult());

	calk.substruct(a, b);
	System.out.println("substruct " + a + " and " + b + " is "+calk.getResult());

	calk.div(a, b);
	System.out.println("div " + a + " and " + b + " is "+calk.getResult());

	calk.multiple(a, b);
	System.out.println("multiple " + a + " and " + b + " is "+calk.getResult());
	}
}