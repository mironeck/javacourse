package ru.mironenko.conditionaloperator;

import org.hamcrest.Matchers;
import org.junit.Test;
import ru.mironenko.conditionaloperator.FindMaxSide;
import ru.mironenko.conditionaloperator.Point;
import ru.mironenko.conditionaloperator.Triangle;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

/**
 * Created by Nikita on 24.07.2016.
 */
public class FindMaxSideTest {
    @Test
    public void max() throws Exception {
        Triangle triangle = new Triangle(new Point(1, 1), new Point(2, 2), new Point(3, 1));
        FindMaxSide findMaxSide = new FindMaxSide();
        assertThat(findMaxSide.max(triangle), Matchers.<Double>is(2.0));
    }

}