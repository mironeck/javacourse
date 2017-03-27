package ru.mironenko.collectionspro.iterator;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 27.03.2017.
 */
public class TDAIteratorTest {

    @Test
    public void whenNextThenReturnNextElementInTwoDmnsArray() {
        int [][] array = new int[][]{
                {1, 2},
                {3, 4}
        };
        TDAIterator tda = new TDAIterator(array);
        Iterator it = tda.iterator();

        int[] result = new int[]{(int)it.next(), (int) it.next(), (int) it.next(), (int) it.next()};
        boolean resultHasNext = it.hasNext();
        int[] checked = new int[]{1, 2, 3, 4};

        assertThat(result, is (checked));
        assertThat(resultHasNext, is (false));
    }

}