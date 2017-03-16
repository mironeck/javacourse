package main.java.ru.mironenko.srp;

/**
 * Created by nikita on 16.02.2017.
 */
public interface Action {

    /**
     * Name of action
     * @return name
     */
    int key();

    /**
     * Info about action, what action doing
     * @return
     */
    String info();

    /**
     * Action
     */
    void execute(Input input, Calculator calculator);
}
