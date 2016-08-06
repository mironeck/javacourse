package ru.mironenko.arrays;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.mironenko.arrays.BubbleSort;
/*
*@author mironenko
*@since 06.08.2016
*/

public class BubbleSortTest{
	@Test
	public void whenMethodSort(){
		final BubbleSort bubbleSort= new BubbleSort();
		int [] checked = new int[]{0, 1, 2, 3, 4, 5};
		int [] arr = new int[]{3, 5, 0, 2, 4, 1};
		
		bubbleSort.bubbleSort(arr);
		assertThat(arr, is(checked));
	}
}