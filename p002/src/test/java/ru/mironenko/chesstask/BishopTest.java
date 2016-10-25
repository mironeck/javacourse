package ru.mironenko.chesstask;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 25.10.2016.
 */
public class BishopTest {
    @Test
    public void whenGetColor() throws Exception {

        Bishop bishop = new Bishop("white");
        String result = "white";
        String checked = bishop.getColor();

        assertThat(result, is(checked));
    }

    @Test
    public void whenPathIsValidValidOne() throws Exception {

        Bishop bishop = new Bishop("white");

        boolean result = bishop.isValidPath(1, 0, 4, 3);
        boolean checked = true;

        assertThat(result, is(checked));
    }

    @Test
    public void whenPathIsValidValidTwo() throws Exception {

        Bishop bishop = new Bishop("white");

        boolean result = bishop.isValidPath(4, 3, 2, 5);
        boolean checked = true;

        assertThat(result, is(checked));
    }

    @Test
    public void whenPathIsInvalid() throws Exception {

        Bishop bishop = new Bishop("white");

        boolean result = bishop.isValidPath(4, 3, 7, 3);
        boolean checked = false;

        assertThat(result, is(checked));
    }

}