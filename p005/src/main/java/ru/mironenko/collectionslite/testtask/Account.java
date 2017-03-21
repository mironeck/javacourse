package ru.mironenko.collectionslite.testtask;

/**
 * Created by nikita on 20.03.2017.
 */
public class Account {

    /**
     * value - amount of money
     */
    private int value;
    /**
     * requisites of account
     */
    private int requisites;

    public Account(int value, int requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public int getRequisites() {
        return requisites;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
