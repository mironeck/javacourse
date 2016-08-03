package ru.mironenko.conditionaloperator;

public class CalculateArea{

	public static void main(String [] args){
	
	    Triangle triangle = new Triangle(new Point(1, 1), new Point(2, 2), new Point(3, 1));

        double area = triangle.area();
        System.out.format("%.4f%n", area);

        FindMaxSide findMaxSide = new FindMaxSide();
        System.out.format("The max length is %.2f%n", findMaxSide.max(triangle));

    }
  
}