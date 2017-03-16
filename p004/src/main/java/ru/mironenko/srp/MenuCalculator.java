package main.java.ru.mironenko.srp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 16.02.2017.
 */
public class MenuCalculator {

    /**
     * Input
     */
    private final Input input;
    /**
     * Calculator
     */
    private final Calculator calculator;

    /**
     * Constructor
     * @param input
     * @param calculator
     */

    private double secondValue = 0d;
    private double result = 0d;
    private String operation = "";
    private boolean useAgain = false;
    private boolean repeat = false;
    private Action[] actions = new Action[7];

    public MenuCalculator(Input input, Calculator calculator) {
        this.input = input;
        this.calculator = calculator;
    }

    private int position = 0;

    public void fill() {

        this.actions[position++] = new Addition();
    }

    private class Addition implements Action{

        @Override
        public int key() {
            return 1;
        }

        @Override
        public String info() {
            return null;
        }

        @Override
        public void execute() {

        }
    }
}
