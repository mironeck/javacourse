package ru.mironenko.socket.filemanager.clientside;

/**
 * Created by nikita on 03.02.2017.
 */
public class MenuClient {


    private UserAction[] actions = new UserAction[5];



    private class ReturnRootDirectoryList extends Action{


        public ReturnRootDirectoryList(String name) {
            super("Show root directory list");
        }

        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input) {


        }
    }

}
