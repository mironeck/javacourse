package ru.mironenko.conditionaloperator;


import static java.lang.Math.sqrt;

public class Triangle {
   public Point a;
   public Point b;
   public Point c;

   public Triangle(Point a, Point b, Point c) {
      this.a = a;
      this.b = b;
      this.c = c;
   }


   public double area() {
      //calculate the triangle area
      double area;
      if (((c.x - a.x) / (b.x - a.x)) == ((c.y - a.y) / (b.y - a.y))){
         //System.out.println("Points are on the one straight line");
         area = 0;
      }else {
         double sideOne = a.distanceTo(b);
         double sideTwo = b.distanceTo(c);
         double sideThree = c.distanceTo(a);
         double p = (sideOne + sideTwo + sideThree) / 2;
         area = sqrt(p * (p - sideOne) * (p - sideTwo) * (p - sideThree));
      }
	
	return area;
   }
}
