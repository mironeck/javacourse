package ru.mironenko.collectionspro.map;


import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 10.04.2017.
 */
public class MyMapThreeTest {

    @Test
    public void whenAddElementThenShouldReturnTrue(){

        MyMapThree<Integer, Integer> myMap = new MyMapThree<>();
        myMap.insert(1, 1);
        myMap.insert(2, 2);
        myMap.insert(2, 4);

        boolean resultAdd = myMap.insert(3, 3);

        assertThat(resultAdd, is(true));
        assertThat(myMap.get(2), is(4));
        assertThat(myMap.getSize(), is(3));
    }

    @Test
    public void whenDeleteElementThenShouldReturnTrue(){

        MyMapThree<Integer, Integer> myMap = new MyMapThree<>();
        myMap.insert(1, 1);
        myMap.insert(2, 2);
        myMap.insert(3, 3);

        boolean resultDelete = myMap.delete(2);

        assertThat(resultDelete, is(true));
        assertThat(myMap.getSize(), is(2));
    }

    @Test
    public void whenIterateElementThenShouldReturnNext(){

        MyMapThree<Integer, Integer> myMap = new MyMapThree<>();
        myMap.insert(1, 1);
        myMap.insert(2, 2);
        myMap.insert(3, 3);

        Iterator it = myMap.iterator();

        boolean hasNextFirst = it.hasNext();
        Integer valueOne = (Integer) it.next();
        Integer valueTwo = (Integer) it.next();
        it.next();
        boolean hasNextLast = it.hasNext();

        assertThat(hasNextFirst, is(true));
        assertThat(valueOne, is(1));
        assertThat(valueTwo, is(2));
        assertThat(hasNextLast, is(false));
    }
}