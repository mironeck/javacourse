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
	
	private Item[] items = new Item[10];
	private Item[] filteredItems = new Item[0];
	private int position = 0;
	private static final Random RN = new Random();

	 public Item[] getItems(){
	 	return this.items;
	 }
	 public Item[] getFilteredItems(){
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
		if (this.position == this.items.length){
			Item[] temp = new Item[position + 10];
			System.arraycopy(this.items, 0, temp, 0, position);
			this.items = temp;
		}
		item.setId(this.generateId());
		this.items[position++] = item;
		return item;
	}
	
	/**
	*The method edit item
	*@param fresh
	*@return void
	*/
	public void edit(Item fresh){
		for (int index = 0; index != items.length; ++index){
			Item item = items[index];
			if(item != null && item.getId().equals(fresh.getId())){
				items[index] = fresh;
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
	public Item[] getAll(){
		Item[] result = new Item[this.position];
		for(int index = 0; index != this.position; index++){
			result[index] = this.items[index];
		}
		return result;
	}

	/**
	*The method delete item and return Item[] without it
	*@param itemToDelete
	*@return void
	*/
	public void deleteItem(Item itemToDelete){

		for(int index = 0; index < this.position; index++){
			if (this.items[index] != null && this.items[index].equals(itemToDelete)){
				Item[] result = new Item[this.items.length-1];
				// copy elements in the new array before deleted item
				System.arraycopy(items, 0, result, 0, index);
				// copy elements in the new array after deleted item
				System.arraycopy(items, index+1, result, index, items.length-index-1);
				this.items = result;
				position--;
				break;
			}
		}
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

	public Item[] filterItems(Filter filter){

		Item [] result = new Item[position];
		int resultIndex = 0;
		for (int index = 0; index < this.position; index++) {
			if(this.items[index].getName().contains(filter.getFilter()) || this.items[index].getDescription().contains(filter.getFilter())){

				result[resultIndex++] = this.items[index];
			}
		}
		this.filteredItems = Arrays.copyOf(result, resultIndex);
		return  this.filteredItems;
	}
}