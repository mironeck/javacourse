package ru.mironenko.collectionspro.iterator.converttask;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 27.03.2017.
 */
public class ConvertIteratorTest {

    @Test
    public void whenConvertIteratorOfIteratorsThenReturnIntegers() {

        Iterator<Integer> i1 = Arrays.asList(4, 2, 0, 4, 6, 4, 9).iterator();
        Iterator<Integer> i2 = Arrays.asList(0, 9, 8, 7, 5).iterator();
        Iterator<Integer> i3 = Arrays.asList(1, 3, 5, 6, 7, 0, 9, 8, 4).iterator();
        ConvertIterator convertIterator = new ConvertIterator();

        Iterator<Iterator<Integer>> iterator = Arrays.asList(i1, i2, i3).iterator();
        Iterator<Integer> convertedIterator = convertIterator.convert(iterator);
        List<Integer> result = new ArrayList<>();
        while (convertedIterator.hasNext())
        {
            result.add(convertedIterator.next());
        }
        List<Integer> checked = Arrays.asList(4,2,0,4,6,4,9,0,9,8,7,5,1,3,5,6,7,0,9,8,4);

        assertThat(result, is (checked));
    }

}