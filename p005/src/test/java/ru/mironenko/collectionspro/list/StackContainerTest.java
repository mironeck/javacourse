package ru.mironenko.collectionspro.list;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 02.04.2017.
 */
public class StackContainerTest {

    @Test
    public void whenPushElementsShouldAddInTheHead(){

        StackContainer<Integer> sc = new StackContainer<>();
        sc.push(3);
        sc.push(2);
        sc.push(1);

        assertThat(sc.get(0), is(1));
    }

    @Test
    public void whenPullElementsShouldRemovedFromTheHead(){

        StackContainer<Integer> sc = new StackContainer<>();
        sc.push(3);
        sc.push(2);
        sc.push(1);

        sc.pull();

        assertThat(sc.get(1), is(2));
        assertThat(sc.getSize(), is(2));
    }

}