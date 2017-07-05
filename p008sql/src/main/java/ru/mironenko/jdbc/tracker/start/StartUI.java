package ru.mironenko.jdbc.tracker.start;

import ru.mironenko.jdbc.tracker.start.StartUI;
import ru.mironenko.jdbc.tracker.start.Input;

import java.io.IOException;

/**
 * Class StartUI with main method to start the program
 * @author mironenko
 * @since 17.09.2016
 */

public class StartUI{
	/**
	 * @params input, tracker
	 */
	private Input input;
	private Tracker tracker;

	public StartUI(Input input){
		this.input = input;
	}
	public StartUI(Input input, Tracker tracker){
		this.input = input;
		this.tracker = tracker;
	}

	/**
	 * the method create objects of Tracker, MenuTracker,
	 * array of acceptable values to choose from array of actions,
	 * show menu while user don't enter "y"
	 */
	public void init() throws IOException {
		
		Tracker tracker = new Tracker();
		MenuTracker menu = new MenuTracker(this.input, this.tracker);
		menu.fillActions();
		int[] ranges = new int[menu.getActions().size()];
		for(int index = 0; index < menu.getActions().size(); index++){
			ranges[index] = index+1;
		}
		


		do{
			menu.show();
			menu.select(input.ask("select: ", ranges));
		}while(!"y".equals(this.input.ask("Exit?(y):")));
	}

	/**
	 * the method for StartUITest to test it with StunInput
	 * create objects of MenuTracker,
	 * array of acceptable values to choose from array of actions,
	 * show menu while user don't enter "y"
	 */
	public void initTest(){
		MenuTracker menu = new MenuTracker(this.input, this.tracker);
		menu.fillActions();
		int[] ranges = new int[menu.getActions().size()];
		for(int index = 0; index < menu.getActions().size(); index++){
			ranges[index] = index+1;
		}

		do{
			menu.show();
			menu.select(input.ask("select: ", ranges));
		}while(!"y".equals(this.input.ask("Exit?(y):")));
	}

	/**
	 * the main method to start the program
	 * @param args
	 */

	public static void main(String[] args) throws IOException {
		Input input = new ConsoleInput();
		//Input input = new ValidateInput();
		Tracker tracker = new Tracker();

//		new StartUI(input).init();
		new StartUI(input, tracker).init();
	}
}