package ru.mironenko.cycles;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.mironenko.cycles.Factorial;
/*
*@author mironenko
*@since 06.08.2016
*/
public class FactorialTest{
	@Test
	public void calculateFact(){
	
		final Factorial factorial =  new Factorial();
		int checked = 120;
		
		final int result = factorial.calculateFactorial(5);
		assertThat(result, is(checked));
		
	}
	@Test
	public void whenNumberLessThenZero(){
	
		final Factorial factorial =  new Factorial();
		int checked = -1;
		
		final int result = factorial.calculateFactorial(-5);
		assertThat(result, is(checked));
		
	}
}