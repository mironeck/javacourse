package ru.mironenko.conditionaloperator;

import org.hamcrest.Matchers;
import org.junit.Test;
import ru.mironenko.conditionaloperator.Point;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

/**
 * Created by Nikita on 23.07.2016.
 */
public class PointTest {
    @Test
    public void distanceTo() throws Exception {
        Point pointA = new Point(3, 3);
        Point pointB = new Point(1, 1);

        double distance = pointA.distanceTo(pointB);
        assertThat(distance, Matchers.<Double>is(closeTo(2.82, 0.01)));
    }

}