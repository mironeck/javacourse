package ru.mironenko.collectionspro.iterator;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 27.03.2017.
 */
public class SimpleNumbersIteratorTest {

    @Test
    public void whenNextThenReturnSimpleNumber() {
        int [] array = new int[]{2, 3, 5, 7, 4, 29};
        SimpleNumbersIterator simpleNumbersIterator = new SimpleNumbersIterator(array);
        Iterator it = simpleNumbersIterator.iterator();

        int[] result = new int[]{(int)it.next(), (int) it.next(), (int) it.next(), (int) it.next(), (int) it.next()};
        boolean resultHasNext = it.hasNext();
        int[] checked = new int[]{2, 3, 5, 7, 29};

        assertThat(result, is (checked));
        assertThat(resultHasNext, is (false));
    }

}