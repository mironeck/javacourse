package ru.mironenko.start;


public class StartUITest{

	public static void main(String[] args){
		Input input = new StubInput(
				new String[] {
					"0",

				});
		new StartUI(input).init();
	}
}