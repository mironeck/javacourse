package ru.mironenko.collectionspro.generic;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

/**
 * Created by nikita on 27.03.2017.
 */
public class SimpleListTest {

    @Test
    public void whenCreateContainerShouldReturnString(){

        SimpleList<String> list = new SimpleList<String>(4);
        list.add("test");
        String result = list.get(0);

        assertThat(result, is ("test"));
    }

    @Test
    public void whenCreateContainerShouldReturnInteger(){

        SimpleList<Integer> simpleList = new SimpleList<Integer>(4);

        simpleList.add(1);
        int result = simpleList.get(0);

        assertThat(result, is (1));
    }

    @Test
    public void whenUpdateElementReturnNewValue(){
        SimpleList<Integer> simpleList = new SimpleList<Integer>(4);

        simpleList.add(1);
        simpleList.update(0, 2);
        int result = simpleList.get(0);

        assertThat(result, is (2));
    }

    @Test
    public void whenDeleteElementReturnNull(){
        SimpleList<Integer> simpleList = new SimpleList<Integer>(4);

        simpleList.add(1);
        simpleList.delete(0);

        assertNull(simpleList.get(0));
    }
}