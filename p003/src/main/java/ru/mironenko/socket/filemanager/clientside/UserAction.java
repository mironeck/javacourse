package ru.mironenko.socket.filemanager.clientside;

/**
 * Created by nikita on 03.02.2017.
 */
public interface UserAction {
    /*
	* ключ, который будет запрашиваться у пользователя для определения действия, которое он хочет выполнить
	*/
    int key();

    /*
    * метод, который будет выполнять основное действие (добавление, редактирование, поиск, ...)
    */
    void execute(Input input);

    /*
    * этот метод будет сообщать пользователю, что данный метод (execute) делает
    */
    String info();
}
