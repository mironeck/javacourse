package ru.mironenko.arrays;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.mironenko.cycles.Factorial;
/*
*@author mironenko
*@since 09.08.2016
*/

public class ArrayTurnTest{
	@Test
	public void whenArrayTurn(){
		final ArrayTurn arrayTurn =  new ArrayTurn();
		int[][] checked = {
						{3, 6, 9},
						{2, 5, 8},
						{1, 4, 7}
					};
		
		int[][] arr = {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 9}
			};
		arrayTurn.turn(arr);	
		assertThat(arr, is(checked));
	}
}