package ru.mironenko.nonblockingalgorithm;

/**
 * Created by nikita on 12.06.2017.
 */
public class Task {

    private int id;
    private String name;
    private int version = 0;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
