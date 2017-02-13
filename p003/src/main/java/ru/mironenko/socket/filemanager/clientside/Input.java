package ru.mironenko.socket.filemanager.clientside;

/**
 * Created by nikita on 03.02.2017.
 */
public interface Input {

    String ask(String question);
    int ask(String question, int[] range);
}
