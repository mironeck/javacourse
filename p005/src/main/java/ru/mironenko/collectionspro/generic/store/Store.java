package ru.mironenko.collectionspro.generic.store;

/**
 * Created by nikita on 28.03.2017.
 */

//Сделать интерфейс Store<T extends Base> где Base
// Это абстрактный класс для моделей c методами String getId(); void setId(String id).
// 1. Сделать два класса User, и Role которые наследуют данный класс.
// 2. Сделать два хранилища UserStore и RoleStore. Внутри для хранения данных использовать SimpleArray.
// 3. Методы добавить, обновить, удалить.
// 4. Помните. про инкапсуляцию. В методах store нельзя использовать методы c index.

public interface Store<T extends Base> {
}
