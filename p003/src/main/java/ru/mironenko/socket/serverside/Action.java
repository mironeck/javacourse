package ru.mironenko.socket.serverside;

/**
 * Created by nikita on 24.01.2017.
 */
public abstract class Action {

    private String name;

    private int key;

    public Action(int key, String name){
        this.name = name;
    }

    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }
}
