package ru.mironenko.start;

import ru.mironenko.models.*;
/*
* MenuTracker содержит все действия и отвечает отображение меню,
* выбор пользользователем действия.
* Но всё выполнение происходит не в классе MenuTracker, а в классе,
* который отвечает за эти действия.
* в меню необходимо получить экземпляры объектов классов Tracker и Input
* поэтому примем их в качестве параметров конструктора
*/

class EditItem implements UserAction{
	
			
		public int key(){
			return 2;
		}
		
		public void execute(Input input, Tracker tracker){
			String id = input.ask ("Please, enter the task's id: ");
			String name = input.ask ("Please, enter the task's name: ");
			String desc = input.ask ("Please, enter the task desc: ");
			Task task = new Task(name, desc);
			task.setId(id);
			tracker.edit(task);
		}
		
		public String info(){
			return String.format("%s. %s", this.key(), "Edit the new item.");
		}
		
}

public class MenuTracker{
	
	private Input input; // система ввода вывода
	private Tracker tracker;  // базовый класс, который хранит, добавляет и редактирует заявки
	private UserAction[] actions = new UserAction[6]; // массив действий, которые описаны в системе
	
	public MenuTracker(Input input, Tracker tracker){
		this.input = input;
		this.tracker = tracker;
	}
	
	/*
	*создадим событи€
	*для этого создаЄм метод дл€ инициализации этих событий
	*которые будем хранить в массиве UserAction[]
	* 
	* 
	*/
	public void fillActions(){
		//how to fill it.
		// если используется нестатический класс, то обращение происходит через объект родителя
		this.actions[0] = this.new AddItem();
		//если класс статический, то обращение через класс, в котором находится родитель
		this.actions[1] = new MenuTracker.ShowItems();
		this.actions[2] = new EditItem();
		this.actions[3] = this.new DeleteItem();
		this.actions[4] = this.new AddComment();
	}
	
	public UserAction[] getActions(){
		return this.actions;
	}
	
	//метод, который будет выполн€ть действи€, которые выбрал пользователь
	public void select(int key){
		this.actions[key].execute(this.input, this.tracker);
	}
	// метод дл€ печати меню
	public void show(){
		for (UserAction action : actions){
			if (action != null){
			System.out.println(action.info());
			}
		}
	}
	
	/*
	* private inner class to add items
	*
	*
	*/
	private class AddItem implements UserAction{
		
		public int key(){
			return 0;
		}
		
		public void execute(Input input, Tracker tracker){
			String name = input.ask ("Please, enter the task's name: ");
			String desc = input.ask ("Please, enter the task desc: ");
			tracker.add(new Task(name, desc));
		}
		
		public String info(){
			return String.format("%s. %s", this.key(), "Add the new item.");
		}
	}
	
	/*
	* class to show items
	*
	*
	*/
	private static class ShowItems implements UserAction{
		
		public int key(){
			return 1;
		}
		
		public void execute(Input input, Tracker tracker){
			for (Item item : tracker.getAll()){
				System.out.println(String.format("%s. %s", item.getId(), item.getName())
				);
			}
		}
		
		public String info(){
			return String.format("%s. %s", this.key(), "Show all items.");
		}
	}
	
	/*
	* private inner class to delete item
	*
	*
	*/
	private class DeleteItem implements UserAction{
		
		public int key(){
			return 3;
		}
		
		public void execute(Input input, Tracker tracker){
			String id = input.ask ("Please, enter the task's id to delete: ");
			tracker.deleteItem(id);
		}
		
		public String info(){
			return String.format("%s. %s", this.key(), "Delete item.");
		}
	}
	
	/*
	* private inner class to add comment to item
	*
	*
	*/
	private class AddComment implements UserAction{
		
		public int key(){
			return 4;
		}
		
		public void execute(Input input, Tracker tracker){
			String id = input.ask ("Please, enter the task's id to add comment: ");
			String comment = input.ask("Please, enter a comment: ");
			tracker.addComment(id, comment);
		}
		
		public String info(){
			return String.format("%s. %s", this.key(), "Add comment.");
		}
	}
	
	
	
}