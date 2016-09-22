package ru.mironenko.start;

import ru.mironenko.models.*;

public class StartUI{
	
	private Input input;
	private Tracker tracker;

	public StartUI(Input input){
		this.input = input;
	}
	public StartUI(Input input, Tracker tracker){
		this.input = input;
		this.tracker = tracker;
	}
	
	public void init(){
		
		Tracker tracker = new Tracker();
		//создаём объект меню
		MenuTracker menu = new MenuTracker(this.input, tracker);
		// массив возможных значений для выбора
		int[] ranges = new int[menu.getActions().length];
		// заполняем его индексами из  массива событий меню actions[]
		for(int index = 0; index < menu.getActions().length; index++){
			ranges[index] = index+1;
		}
		
		menu.fillActions();
		//отображаем меню пока пользователь не ввёл "y"
		//при помощи цикла с постусловием do while
		do{
			menu.show();
			//int key = Integer.valueOf(input.ask("Select: "));
			menu.select(input.ask("select: ", ranges));
		}while(!"y".equals(this.input.ask("Exit?(y):")));
	}

	public void initTest(){
		//Tracker tracker = new Tracker();
		//создаём объект меню
		MenuTracker menu = new MenuTracker(this.input, this.tracker);
		// массив возможных значений для выбора
		int[] ranges = new int[menu.getActions().length];
		// заполняем его индексами из массива событий меню actions[]
		for(int index = 0; index < menu.getActions().length; index++){
			ranges[index] = index+1;
		}

		menu.fillActions();
		//отображаем меню пока пользователь не ввёл "y"
		//при помощи цикла с постусловием do while
		do{
			menu.show();
			//int key = Integer.valueOf(input.ask("Select: "));
			menu.select(input.ask("select: ", ranges));
		}while(!"y".equals(this.input.ask("Exit?(y):")));
	}

	public static void main(String[] args){
		Input input = new ValidateInput();
		new StartUI(input).init();
	}
}