package ru.mironenko.conditionaloperator;

public class FindMaxSide{

    public double max(ru.mironenko.conditionaloperator.Triangle triangle){

        double [] sides = new double[3];
        sides[0] = triangle.a.distanceTo(triangle.b);
        sides[1] = triangle.b.distanceTo(triangle.c);
        sides[2] = triangle.c.distanceTo(triangle.a);

        double maxSide = sides[0];
        for (int i = 0; i < sides.length; i++) {
            if (maxSide < sides[i]){
                maxSide = sides[i];
            }
        }
        return maxSide;
    }
}