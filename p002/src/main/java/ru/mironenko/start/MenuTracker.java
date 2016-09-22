package ru.mironenko.start;

import ru.mironenko.models.*;
/**
* Class MenuTracker consist all actions and show menu
*@author mironenko
 * @since 17.08.2016
 * @version 1
*/

public class MenuTracker{
	/**
	 * @params input, tracker, actions
	 */
	private Input input;
	private Tracker tracker;
	private UserAction[] actions = new UserAction[6];
	
	public MenuTracker(Input input, Tracker tracker){
		this.input = input;
		this.tracker = tracker;
	}
	
	/**
	*the method initialize actions
	* and keep it in UserAction[] actions
	*@return void
	*/
	public void fillActions(){
		//how to fill it.
		this.actions[0] = this.new AddItem();
		this.actions[1] = new MenuTracker.ShowItems();
		this.actions[2] = new EditItem();
		this.actions[3] = this.new DeleteItem();
		this.actions[4] = this.new AddComment();
		this.actions[5] = this.new FilterItem();
	}
	
	public UserAction[] getActions(){
		return this.actions;
	}
	
	/**
	*the method to do action that choose user
	*@return void
	*/
	public void select(int key){
		this.actions[key-1].execute(this.input, this.tracker);
	}

	/**
	*the method to show menu
	*@return void
	*/
	public void show(){
		for (UserAction action : actions){
			if (action != null){
			System.out.println(action.info());
			}
		}
	}
	
	/**
	* Class AddItem to add items
	*/
	private class AddItem implements UserAction{
		/**
		* the method return key of the action
		* @return 1
		* */
		public int key(){
			return 1;
		}

		/**
		* the method return ask user new item's name and description
		* and add the item
		* @params input, tracker
		* @return void
		* */
		public void execute(Input input, Tracker tracker){
			String name = input.ask ("Please, enter the task's name: ");
			String desc = input.ask ("Please, enter the task desc: ");
			tracker.add(new Item(name, desc));
		}
		/**
		* the method print info what the class do
		* @return info
		* */
		public String info(){
			return String.format("%s. %s", this.key(), "Add the new item.");
		}
	}
	
	/**
	* Class ShowItems to show items
	*/
	private static class ShowItems implements UserAction{
		/**
		* the method return key of the action
		* @return 2
		* */
		public int key(){
			return 2;
		}

		/**
		* the method print all items created
		* @params input, tracker
		* @return void
		* */
		public void execute(Input input, Tracker tracker){
			for (Item item : tracker.getAll()){
				System.out.println(item)
				;
			}
		}
		/**
		* the method print info what the class do
		* @return info
		* */
		public String info(){
			return String.format("%s. %s", this.key(), "Show all items.");
		}
	}

	/**
	* Class EditItem to edit item
	*/
	class EditItem implements UserAction{
		/**
		* the method return key of the action
		* @return 3
		* */
		public int key(){
			return 3;
		}

		/**
		* the method ask user item's id to edit and new name and description
		* @params input, tracker
		* @return void
		* */
		public void execute(Input input, Tracker tracker){
			String id = input.ask ("Please, enter the task's id to edit: ");
			String name = input.ask ("Please, enter the task's name: ");
			String desc = input.ask ("Please, enter the task desc: ");
			Item item = new Item(name, desc);
			item.setId(id);
			tracker.edit(item);
		}
		/**
		* the method print info what the class do
		* @return info
		* */
		public String info(){
			return String.format("%s. %s", this.key(), "Edit the item.");
		}
	}
	/**
	* Class DeleteItem to delete item
	*/
	private class DeleteItem implements UserAction{
		/**
		* the method return key of the action
		* @return 4
		* */
		public int key(){
			return 4;
		}
		/**
		* the method ask user item's id to delete
		* @params input, tracker
		* @return void
		* */
		public void execute(Input input, Tracker tracker){
			String id = input.ask ("Please, enter the task's id to delete: ");
			tracker.deleteItem(tracker.findById(id));
		}
		/**
		* the method print info what the class do
		* @return info
		* */
		public String info(){
			return String.format("%s. %s", this.key(), "Delete item.");
		}
	}
	
	/**
	* Class AddItem to add comment to item
	*/
	private class AddComment implements UserAction{
		/**
		* the method return key of the action
		* @return 5
		* */
		public int key(){
			return 5;
		}
		/**
		* the method ask user item's id to add comment, enter comment and add comment
		* @params input, tracker
		* @return void
		* */
		public void execute(Input input, Tracker tracker){
			String id = input.ask ("Please, enter the task's id to add comment: ");
			String comment = input.ask("Please, enter a comment: ");
			tracker.addComment(id, new Comment(comment));
		}
		/**
		* the method print info what the class do
		* @return info
		* */
		public String info(){
			return String.format("%s. %s", this.key(), "Add comment.");
		}
	}

	/**
	* Class FilterItem to get filtered array of items
	*/
	private class FilterItem implements UserAction{
		/**
		* the method return key of the action
		* @return 6
		* */
		public int key(){
			return 6;
		}
		/**
		* the method ask user filter world and print filtered array of items
		* @params input, tracker
		* @return void
		* */
		public void execute(Input input, Tracker tracker){
			String filter = input.ask("Please, enter a filter word: ");
			Item[] result  = tracker.filterItems(new Filter(filter));
			for (Item item : result){
				System.out.println(item)
				;
			}
		}
		/**
        * the method print info what the class do
        * @return info
        * */
		public String info(){
			return String.format("%s. %s", this.key(), "Filter items.");
		}
	}
	
}