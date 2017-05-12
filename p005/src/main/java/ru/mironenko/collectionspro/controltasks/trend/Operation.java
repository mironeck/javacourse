package ru.mironenko.collectionspro.controltasks.trend;

import java.util.Date;

/**
 * Created by nikita on 18.04.2017.
 */
//Operation - начальное состояние, конечное состояние, дату выполнения.
public class Operation {

    private String nameOfOperation;
    private State firstState;
    private State finalState;
    private Date dateOfOperation;

    public Operation(String nameOfOperation) {
        this.nameOfOperation = nameOfOperation;
        this.dateOfOperation = new Date();
    }

    public State getFirstState() {
        return firstState;
    }

    public State getFinalState() {
        return finalState;
    }

    public Date getDateOfOperation() {
        return dateOfOperation;
    }

    public String getNameOfOperation() {
        return nameOfOperation;
    }

    public void setFirstState(State firstState) {
        this.firstState = firstState;
    }

    public void setFinalState(State finalState) {
        this.finalState = finalState;
    }
}
