package ru.mironenko.conditionaloperator;


public class Point {
   public double x;
   public double y;

   public Point(double x, double y) {
      this.x = x;
      this.y = y;
   }
   
  public double distanceTo(Point point) {
      //calculate distance between two point
	return Math.sqrt(Math.pow(point.x - this.x, 2) + Math.pow(point.y - this.y, 2));
   }
}
