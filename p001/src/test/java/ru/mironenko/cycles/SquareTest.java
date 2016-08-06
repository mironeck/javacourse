package ru.mironenko.cycles;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.mironenko.cycles.Square;

public class SquareTest{
	@Test
	public void calculateTest(){
		final Square square =  new Square(3f, 2f, 1f);
		float checked = 1f;
		final float result = square.calculate(0);
		assertThat(result, is(checked));
	}
}