package ru.mironenko.chesstask;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 25.10.2016.
 */
public class QueenTest {
    @Test
    public void getColor() throws Exception {

        Queen queen = new Queen(Color.White);

        Color result = queen.getColor();
        Color checked = Color.White;

        assertThat(result, is(checked));
    }

    @Test
    public void whenPathIsValidOne() throws Exception {

        Queen queen = new Queen(Color.White);

        boolean result = queen.isValidPath(3, 0, 0, 3);
        boolean checked = true;

        assertThat(result, is(checked));
    }

    @Test
    public void whenPathIsValidTwo() throws Exception {

        Queen queen = new Queen(Color.White);

        boolean result = queen.isValidPath(0, 3, 7, 3);
        boolean checked = true;

        assertThat(result, is(checked));
    }

    @Test
    public void whenPathIsValidThree() throws Exception {

        Queen queen = new Queen(Color.White);

        boolean result = queen.isValidPath(7, 3, 7, 7);
        boolean checked = true;

        assertThat(result, is(checked));
    }

    @Test
    public void whenPathIsValidFour() throws Exception {

        Queen queen = new Queen(Color.White);

        boolean result = queen.isValidPath(7, 7, 0, 7);
        boolean checked = true;

        assertThat(result, is(checked));
    }
}