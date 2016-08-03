package ru.mironenko.lessons.jvmbytecode;

import org.hamcrest.Matchers;
import org.junit.Test;
import ru.mironenko.jvmbytecode.Calculator;

import static org.junit.Assert.*;



/**
 * Created by Nikita on 13.07.2016.
 */
public class CalculatorTest {
    @Test
    public void add() throws Exception {
        Calculator calculator =  new Calculator();
        calculator.add(1, 1);
        assertThat(calculator.getResult(), Matchers.<Double>is((double) 2));

    }

    @Test
    public void substruct() throws Exception {
        Calculator calculator =  new Calculator();
        calculator.substruct(1, 1);
        assertThat(calculator.getResult(), Matchers.<Double>is((double) 0));
    }

    @Test
    public void div() throws Exception {
        Calculator calculator =  new Calculator();
        calculator.div(4, 2);
        assertThat(calculator.getResult(), Matchers.<Double>is((double) 2));
    }

    @Test
    public void multiple() throws Exception {
        Calculator calculator =  new Calculator();
        calculator.multiple(2, 2);
        assertThat(calculator.getResult(), Matchers.<Double>is((double) 4));
    }


}