package ru.mironenko.start;

import ru.mironenko.models.*;

import java.util.ArrayList;
import java.util.List;

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
	private List<UserAction> actions = new ArrayList<UserAction>();

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
		this.actions.add(this.new AddItem());
		this.actions.add(this.new ShowItems());
		this.actions.add(this.new EditItem());
		this.actions.add(this.new DeleteItem());
		this.actions.add(this.new AddComment());
		this.actions.add(this.new FilterItem());
	}
	
	public List<UserAction> getActions(){
		return this.actions;
	}
	
	/**
	*the method does action that user chooses
	*@return void
	*/
	public void select(int key){
		this.actions.get(key - 1).execute(this.input, this.tracker);
	}

	/**
	*the method to show menu
	*@return void
	*/
	public void show(){
		for (UserAction action : this.actions){
			if (action != null){
			System.out.println(action.info());
			}
		}
	}
	
	/**
	* Class AddItem to add items
	*/
	private class AddItem extends BaseAction{

		public AddItem() {
			super("Add new item.");
		}

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

	}
	
	/**
	* Class ShowItems to show items
	*/
	private class ShowItems extends BaseAction{

		public ShowItems() {
			super("Show all items.");
		}

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
				System.out.println(item);
			}
		}
	}

	/**
	* Class EditItem to edit item
	*/
	private class EditItem extends BaseAction{
		public EditItem() {
			super("Edit the item.");
		}

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

	}
	/**
	* Class DeleteItem to delete item
	*/
	private class DeleteItem extends BaseAction{
		public DeleteItem() {
			super("Delete item.");
		}

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
	}
	
	/**
	* Class AddItem to add comment to item
	*/
	private class AddComment extends BaseAction{
		public AddComment() {
			super("Add comment.");
		}

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
	}

	/**
	* Class FilterItem to get filtered array of items
	*/
	private class FilterItem extends BaseAction{
		public FilterItem() {
			super("Filter items.");
		}

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
			List<Item> result  = tracker.filterItems(new Filter(filter));
			for (Item item : result){
				System.out.println(item)
				;
			}
		}
	}
	
}