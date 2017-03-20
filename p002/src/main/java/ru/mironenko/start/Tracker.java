package ru.mironenko.start;

import ru.mironenko.models.*;
import java.util.*;
/**
*Class Tracker is repository of items. It makes some actions with items:
*add, edit, delete, comment to item, get all items and filtered array of items.
*@author mironenko
*@since 24.08.2016
*@version 1
**/

 public class Tracker{
	
	private List<Item> items = new ArrayList<Item>();
	private List<Item> filteredItems = new ArrayList<Item>();
	private static final Random RN = new Random();

	 public List<Item> getItems(){
	 	return this.items;
	 }
	 public List<Item> getFilteredItems(){
		 return this.filteredItems;
	 }
	 /**
	*The method generate id for new item
	*@return String id
	*/
	 String generateId(){
		 return String.valueOf(System.currentTimeMillis() + RN.nextInt());
	 }


	/**
	*The method generate id for the item and add new item in array of items
	*@param item
	*@return new item
	*/
	public Item add(Item item){

		item.setId(this.generateId());
		this.items.add(item);
		return item;
	}
	
	/**
	*The method edit item
	*@param fresh
	*@return void
	*/
	public void edit(Item fresh){
		for (int index = 0; index != items.size(); ++index){
			Item item = items.get(index);
			if(item != null && item.getId().equals(fresh.getId())){
				items.set(index, fresh);
				break;
			}
		}

	}
	
	/**
	*The method find item with key id
	*@param id
	*@return item with key id
	*/
	protected Item findById(String id){
		Item result = null;
		for (Item item : items){
			if (item != null && item.getId().equals(id)){
				result = item;
				break;
			}
		}
		return result;
	}

	/**
	*The method return all items created
	*@return array of items created
	*/
	public List<Item> getAll(){

		return this.items;
	}

	/**
	*The method delete item and return Item[] without it
	*@param itemToDelete
	*@return void
	*/
	public void deleteItem(Item itemToDelete){

		this.items.remove(itemToDelete);
	}  
	
	/**
	*The method add comment to item 
	*@param id
	*@param comment
	*@return void
	*/
	public void addComment(String id , Comment comment){

		for (Item item : this.items){
			if (item != null && item.getId().equals(id)){
				item.addComment(comment);
				break;
			}
		}
	}

	/**
	*The method return array of items sorted by filter
	*@param filter
	*@return array of items sorted by filter
	*/

	public List<Item> filterItems(Filter filter){

		this.filteredItems.clear();

		for (Item item : this.items) {
			if(item.getName().contains(filter.getFilter()) ||
					item.getDescription().contains(filter.getFilter())){

				this.filteredItems.add(item);
			}
		}

		return  this.filteredItems;
	}
}