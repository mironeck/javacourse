package ru.mironenko.nonblockingalgorithm;

import ru.mironenko.monitore.taskfour.Main;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Created by nikita on 12.06.2017.
 */
//1. Необходимо сделать кеш для хранение моделей. в кеше должны быть методы add, update, delete.
//2. Кеш должен работать на неблокирующих алгоритмах. - использовать ConcurrentHashMap
//3. В кеше должна быть возможность проверять валидность данных.
// Например. Два пользователя прочитали данные task_1
// первый пользователь изменил поле имя и второй сделал тоже самое.
// нужно перед обновлением данных проверить. что текущий пользователь не затер данные другого пользователя.
// если данные затерты, то выбросить OplimisticException - такая реализация достигается за счет введения
// в модель поля version и перед обновлением данных проверять текущую версию и ту,
// что обновляем и увеличивать на один каждый раз когда обновили. если версии не равны то кидать исключение.
// Использовать метод
// https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html#computeIfPresent-K-java.util.function.BiFunction-


public class Cash {

    private final ConcurrentHashMap<Integer, Model> map = new ConcurrentHashMap<>();

    /**
     * Adds model to map.
     * @param model
     */
    public void add(Model model){

        this.map.put(model.getId(), model);
    }

    /**
     * The method sets new name to model
     * @param newModel model with new name
     */
    public void update(Model newModel){
        this.map.computeIfPresent(newModel.getId(), new BiFunction<Integer, Model, Model>(){

            @Override
            public Model apply(Integer integer, Model oldModel) {
                if(oldModel.getVersion() == newModel.getVersion()) {
                    oldModel.setName(newModel.getName());
                } else {
                    throw new OptimisticException("wrong version");
                }
                return oldModel;
            }
        });
    }

    /**
     * The method removes model from map
     * @param modelToDelete model to remove
     */
    public void delete(Model modelToDelete){
        this.map.remove(modelToDelete.getId());
    }

    /**
     * Exception for update method
     */

    private class OptimisticException extends RuntimeException {

        public OptimisticException(String s) {
            super(s);
        }
    }
}
