package ru.mironenko.start;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

/*
*Class StartUITest is class for test tracker with StubInput
*@mironenko
*@since 17.09.2016
*@version 1
**/
public class StartUITest{

//	public static void main(String[] args) {
//
//		Input input = new StubInput(new String[]{"1", "test", "ttest", "b", "2", "y"});
//		new StartUI(input).init();
//	}
		@Test
		public void whenAddItemStubTest(){

			Tracker tracker =  new Tracker();
			Input input = new StubInput(new String[]{
				"1",
				"name1",
				"desc1",
					"n",
					"2",
					"y"
			});

			new StartUI(input, tracker).initTest();

			assertThat(tracker.getAll()[0].getName(),is("name1"));
			assertThat(tracker.getAll()[0].getDescription(),is("desc1"));
		}

		@Test
	public void whenEditItemStubTest(){

			Tracker tracker =  new Tracker();
			Input input1 = new StubInput(new String[]{
					"1",
					"name1",
					"desc1",
					"y"
			});
			new StartUI(input1, tracker).initTest();

			Input input2 = new StubInput(new String[]{
					"3",
					tracker.getAll()[0].getId(),
					"editName",
					"editDesc",
					"y"
			});
			new StartUI(input2, tracker).initTest();

			assertThat(tracker.getAll()[0].getName(),is("editName"));
			assertThat(tracker.getAll()[0].getDescription(),is("editDesc"));
		}

	@Test
	public void whenDeleteItemStubTest(){

		Tracker tracker =  new Tracker();

		Input input1 = new StubInput(new String[]{
				"1",
				"name1",
				"desc1",
				"n",
				"1",
				"name2",
				"desc2",
				"y"
		});
		new StartUI(input1, tracker).initTest();

		Input input2 = new StubInput(new String[]{
				"4",
				tracker.getAll()[1].getId(),
				"y"
		});
		new StartUI(input2, tracker).initTest();

		assertThat(tracker.getAll().length, is(1));
	}

	@Test
	public void whenAddCommentStubTest(){

		Tracker tracker =  new Tracker();

		Input input1 = new StubInput(new String[]{
				"1",
				"name1",
				"desc1",
				"y"
		});
		new StartUI(input1, tracker).initTest();

		Input input2 = new StubInput(new String[]{
				"5",
				tracker.getAll()[0].getId(),
				"comment",
				"y"
		});
		new StartUI(input2, tracker).initTest();

		assertThat(tracker.getAll()[0].getComment()[0].getComment(), is("comment"));
	}

	@Test
	public void whenFilterItemsStubTest(){

		Tracker tracker =  new Tracker();

		Input input1 = new StubInput(new String[]{
				"1",
				"name1",
				"desc1",
				"n",
				"1",
				"name2",
				"desc2",
				"n",
				"1",
				"test3",
				"test3",
				"y"
		});
		new StartUI(input1, tracker).initTest();

		Input input2 = new StubInput(new String[]{
				"6",
				"name",
				"y"
		});
		new StartUI(input2, tracker).initTest();

		assertThat(tracker.getFilteredItems().length, is(2));
		assertThat(tracker.getFilteredItems()[0].getName(), is("name1"));
		assertThat(tracker.getFilteredItems()[1].getName(), is("name2"));
	}
}