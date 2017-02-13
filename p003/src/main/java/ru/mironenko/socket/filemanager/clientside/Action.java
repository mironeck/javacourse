package ru.mironenko.socket.filemanager.clientside;

/**
 * Created by nikita on 24.01.2017.
 */
public abstract class Action implements UserAction {

    private String name;

    public Action(String name){
        this.name = name;
    }

    public String info() {
        return String.format("%s. %s", this.key(), this.name);
    }
}
