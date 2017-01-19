package ru.mironenko.socket;

/**
 * Created by nikita on 18.01.2017.
 */
public interface ServerSideActions {

    String[] getRootDirectoryList();
    void goToSubDirectory();
    void goToParentDirectory();
    void downloadFile();
    void upLoadFile();

}
