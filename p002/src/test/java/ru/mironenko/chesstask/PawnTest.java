package ru.mironenko.chesstask;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by nikita on 25.10.2016.
 */
public class PawnTest {
    @Test
    public void whenPathIsValid() throws Exception {

        Pawn pawn = new Pawn(Color.White);

        boolean result = pawn.isValidPath(0, 1, 0, 3);
        boolean checked = true;

        assertThat(result, is(checked));
    }

    @Test
    public void whenPathIsInvalid() throws Exception {

        Pawn pawn = new Pawn(Color.White);

        boolean result = pawn.isValidPath(0, 1, 0, 4);
        boolean checked = false;

        assertThat(result, is(checked));
    }

    @Test
    public void whenGetColor() throws Exception {

        Pawn pawn = new Pawn(Color.White);

        Color result = pawn.getColor();
        Color checked = Color.White;

        assertThat(result, is(checked));
    }


}