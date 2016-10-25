package ru.mironenko.chesstask;

import org.junit.Test;

import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 25.10.2016.
 */
public class KnightTest {
    @Test
    public void whenGetColor() throws Exception {
        Knight knight = new Knight("black");
        String result = "black";
        String checked = knight.getColor();

        assertThat(result, is(checked));
    }

    @Test
    public void whenPathIsValidOne() throws Exception {

        Knight knight = new Knight("black");

        boolean result = knight.isValidPath(1, 7, 2, 5);
        boolean checked = true;

        assertThat(result, is(checked));
    }

    @Test
    public void whenPathIsValidTwo() throws Exception {

        Knight knight = new Knight("black");

        boolean result = knight.isValidPath(2, 5, 1, 3);
        boolean checked = true;

        assertThat(result, is(checked));
    }

    @Test
    public void whenPathIsInvalid() throws Exception {

        Knight knight = new Knight("black");

        boolean result = knight.isValidPath(2, 5, 2, 3);
        boolean checked = false;

        assertThat(result, is(checked));
    }
}