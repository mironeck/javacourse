package ru.mironenko.lessons.conditionaloperator;

import org.hamcrest.Matchers;
import org.junit.Test;
import ru.mironenko.conditionaloperator.Point;
import ru.mironenko.conditionaloperator.Triangle;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

/**
 * Created by Nikita on 23.07.2016.
 */
public class TriangleTest {
    @Test
    public void area() throws Exception {
        Triangle triangle = new Triangle(new Point(1, 1), new Point(2, 2), new Point(3, 1));
        assertThat(triangle.area(), Matchers.<Double>is(closeTo(0.99, 0.01)));
    }

}