package ru.mironenko.testtask;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.mironenko.testtask.TestTask;
/*
*@author mironenko
*@since 13.08.2016
*/

public class TestTaskTest{
	@Test
	public void whenOriginContainsSub(){
		final TestTask testtask = new TestTask();
		boolean checked = true;
		
		boolean result = testtask.contains("Mironenko", "Miron");
		assertThat(result, is(checked));
		
	}
}