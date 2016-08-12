package ru.mironenko.arrays;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.mironenko.arrays.Duplicates;
/*
*@author mironenko
*@since 11.08.2016
*/

public class DuplicatesTest{
	@Test
	public void whenDuplicatesDelete(){
		final Duplicates dupl = new Duplicates();
		String[] checked = new String[]{"a", "b"};
		
		String[] forCheck = new String[]{"a", "b", "a"};
		String[] result = dupl.deleteDuplicates(forCheck);
		assertThat(result, is(checked));
	}
	
}