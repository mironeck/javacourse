package ru.mironenko.chesstask;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 25.10.2016.
 */
public class KingTest {

    @Test
    public void whenGetColor() throws Exception {

        King king = new King("white");

        String result = "white";
        String checked = king.getColor();

        assertThat(result, is(checked));
    }

    @Test
    public void whenPathIsValidOne() throws Exception {

        King king = new King("white");
        boolean result = king.isValidPath(4, 0, 5, 1);

        boolean checked = true;

        assertThat(result, is(checked));
    }

    @Test
    public void whenPathIsValidTwo() throws Exception {

        King king = new King("white");
        boolean result = king.isValidPath(5, 1, 6, 1);

        boolean checked = true;

        assertThat(result, is(checked));
    }

    @Test
    public void whenPathIsValidThree() throws Exception {

        King king = new King("white");
        boolean result = king.isValidPath(6, 1, 6, 2);

        boolean checked = true;

        assertThat(result, is(checked));
    }

    @Test
    public void whenPathIsValidFour() throws Exception {

        King king = new King("white");
        boolean result = king.isValidPath(6, 2, 6, 1);

        boolean checked = true;

        assertThat(result, is(checked));
    }
}