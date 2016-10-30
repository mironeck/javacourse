package ru.mironenko.chesstask;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 25.10.2016.
 */
public class RookTest {
    @Test
    public void whenPathIsValidOne() throws Exception {

        Rook rook = new Rook(Color.White);

        boolean result = rook.isValidPath(0, 0, 0, 7);
        boolean checked = true;

        assertThat(result, is(checked));
    }

    @Test
    public void whenPathIsValidTwo() throws Exception {

        Rook rook = new Rook(Color.White);

        boolean result = rook.isValidPath(0, 0, 6, 0);
        boolean checked = true;

        assertThat(result, is(checked));
    }

    @Test
    public void whenPathIsInvalid() throws Exception {

        Rook rook = new Rook(Color.White);

        boolean result = rook.isValidPath(0, 0, 1, 1);
        boolean checked = false;

        assertThat(result, is(checked));
    }

    @Test
    public void whenGetColor() throws Exception {

        Rook rook = new Rook(Color.White);

        Color result = rook.getColor();
        Color checked = Color.White;

        assertThat(result, is(checked));
    }

}