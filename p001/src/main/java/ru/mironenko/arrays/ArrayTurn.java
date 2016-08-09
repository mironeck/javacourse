package ru.mironenko.arrays;


/**
*Class ArrayTurn rotate array on 90 degree
*@author mironenko
*@since 09.08.2016
*@version 1
*/

public class ArrayTurn{
	/*
	*rotate array on 90 degree
	*@param values is square array
	*/
	public void turn(int[][] values){
        int e = values.length - 1;
        int c = e / 2;
        int b = e % 2;
        for (int r = c; r >= 0; r--) {
            for (int d = c - r; d < c + r + b; d++) {
                int t   = values[c - r][d];
                values[c - r][d] = values[d][e - c + r];
                values[d][e - c + r] = values[e - c + r][e - d];
                values[e - c + r][e - d] = values[e - d][c - r];
                values[e - d][c - r] = t;
            }
         }
	}
}