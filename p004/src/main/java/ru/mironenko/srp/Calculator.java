package main.java.ru.mironenko.srp;

/**
 * Created by nikita on 15.02.2017.
 */
//Используя класс Calculator.
//        1. Сделать класс InteractCalc.
//        2. В классе должен быть пользовательский ввод.
//        3. Повторный выбор операции и переиспользование предыдущего вычисления.
//        4. Проект должен следовать SRP.


public class Calculator {
    /**
     * Result of operation
     */
    double result;

    /**
     * Additional two numbers
     * @param first first number
     * @param second second number
     */
    public void add(double first, double second){
        this.result = first + second;
    }

    /**
     * Subtraction two numbers
     * @param first first number
     * @param second second number
     */
    public void subtract(double first, double second){
        this.result = first - second;
    }

    /**
     * Division first number on second
     * @param first first number
     * @param second second number
     */
    public void div(double first, double second){
        this.result = first / second;
    }

    /**
     * Multiplication two numbers
     * @param first first number
     * @param second second number
     */
    public void multiple(double first, double second){
        this.result = first * second;
    }

    /**
     * The method return result of operation
     * @return result 
     */
    public double getResult(){
        return this.result;
    }
}
