package ru.mironenko.start;

public abstract class BaseAction implements UserAction{

    private String name;

    public BaseAction(String name){
        this.name = name;
    }

    public String info() {
        return String.format("%s. %s", this.key(), this.name);
    }
}