package ru.mironenko.collectionspro.controltasks.trend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nikita on 18.04.2017.
 */
//Task - объект имеет состояние State. дату создания и дату обновления. Список выполненных операций.

public class Task {

    private String name;
    private State state;
    private Date dateOfCreation;
    private Date update;
    private List<Operation> list;

    public Task(String name, State state) {
        this.name = name;
        this.state = state;
        this.dateOfCreation = new Date();
        this.list = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public Date getUpdate() {
        return update;
    }

    public List<Operation> getList() {
        return list;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }
}
