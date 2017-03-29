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

        int[] result = new int[]{(int)it.next(), (int) it.next(),(int) it.next(),(int) it.next()};
        int[] checked = new int[]{1, 2, 3, 4};
        boolean resultHasNext = it.hasNext();

        assertThat(result, is (checked));
        assertThat(resultHasNext, is (false));
    }

    @Test
    public void simple() {
        int [][] array = new int[][]{
                {1, 2},
        };
        TDAIterator tda = new TDAIterator(array);
        Iterator it = tda.iterator();
        it.next();
        boolean has = it.hasNext();
        assertThat(has, is (true));
    }

}