package ru.mironenko.cycles;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.mironenko.cycles.Square;
/*
*@author mironenko
*@since 06.08.2016
*/
public class SquareTest{
	@Test
	public void whenCalculate(){
		final Square square = new Square(3,2,1);
        float checked = 6f;

        final float result = square.calculate(1);;
        assertThat(result, is(checked));
	}
}