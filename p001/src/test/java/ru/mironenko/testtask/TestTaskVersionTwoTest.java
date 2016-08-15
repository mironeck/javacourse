package ru.mironenko.testtask;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.mironenko.testtask.TestTaskVersionTwo;
/*
*@author mironenko
*@since 14.08.2016
*/

public class TestTaskVersionTwoTest{
	@Test
	public void whenOriginContainsSub(){
		final TestTaskVersionTwo testtasktwo = new TestTaskVersionTwo();
		boolean checked = true;
		
		boolean result = testtasktwo.contains("Mironenko", "Miron");
		assertThat(result, is(checked));
		
	}
}