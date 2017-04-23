package ru.mironenko.collectionspro.controltasks.trend;

import java.util.Date;

/**
 * Created by nikita on 18.04.2017.
 */
public class Operation {

    final State firstState;
    final State finalState;
    final Date dateOfOperation;

    public Operation(State firstState, State finalState, Date dateOfOperation) {
        this.firstState = firstState;
        this.finalState = finalState;
        this.dateOfOperation = dateOfOperation;
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

}
