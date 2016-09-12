package ru.mironenko.start;

public interface Input{
	
	String ask(String question);
	
	//перегружаем метод ask, int[] range - 
	//диапазон чисел (соответстующих пунктам меню), 
	//возможных для выбора пользователем
	int ask(String question, int[] range);
}