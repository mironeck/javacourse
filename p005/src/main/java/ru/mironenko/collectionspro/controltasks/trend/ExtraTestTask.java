package ru.mironenko.collectionspro.controltasks.trend;

import java.time.Period;
import java.util.Calendar;
import java.util.List;

/**
 * Created by nikita on 16.04.2017.
 */
//Необходимо реализовать сервис генерирования отчета типа тренд.
//        Входящие данные - это список задач List<Task>, Calendar start, Calendar finish, Период.
//        Task - объект имеет состояние State. дату создания и дату обновления. Список выполненных операций.
//        Operation - начальное состояние, конечное состояние, дату выполнения.
//        Сервис должен разбить диапазон start - finish на период и вернуть табличное представление.
//        заголовок периоды. (час, день, неделя, месяц, год) на весь диапазон
//        в нулевой колонке состояние задачи.
//        в поле таблицы, сумма всех состояний на данный период.

public class ExtraTestTask {


    public void generateTrend(List<Task> list, Calendar start, Calendar finish, Period period) {



    }


}
