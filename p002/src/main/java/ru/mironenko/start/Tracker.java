package ru.mironenko.start;

import ru.mironenko.models.*;
import java.util.*;
/*
*Class Tracker is repository of items. It makes some actions with items:
*add, edit, delete, add comment to item, get all items and filtered array of items.
*@mironenko
*@since 24.08.2016
*@version 1
**/

 public class Tracker{
	
	private Item[] items = new Item[10];
	private int position = 0;
	private static final Random RN = new Random();
	
	/*
	*The method generate id for the item and add new item in array of items
	*@param item
	*@return new item
	*/
	public Item add(Item item){
		item.setId(this.generateId());
		this.items[position++] = item;
		return item;
	}
	
	/*
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
	
	/*
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
	
	/*
	*The method generate id for new item
	*@return String id
	*/
	String generateId(){
		return String.valueOf(System.currentTimeMillis() + RN.nextInt());
	}
	
	/*
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
	
	
	/*
	*The method delete item
	*@params items[], id
	*@return result
	*/
	public Item[] deleteItem(String id){
		 Item[] result = new Item[items.length-1]; // создаём новый массив длиной на 1 элемент меньше
		 int indexDeletedItem = -1; //индекс удаляемого элемента
		 position--;
		 //получаем индекс удаляемого элемента
		 for(int index = 0; index != items.length; index++){
			 if (items[index] != null && items[index].getId().equals(id)){
				 indexDeletedItem = index;
			 }
		 }
		 // копируем в новый массив элементы до удаляемого
		 System.arraycopy(items, 0, result, 0, indexDeletedItem);
		 // копируем в новый массив элементы после удаляемого
		 System.arraycopy(items, indexDeletedItem+1, result, indexDeletedItem, items.length-indexDeletedItem-1);	 
		 items = result;
		 return items;
	}  
	
	/*
	*The method add comment to item 
	*params id, comment
	*@return void
	*/
	public void addComment(String id , String comment){
		Item item = this.findById(id);
		item.addComment(comment);
	}
	
	
	/*
	*method return array of items sorted by filter 
	*@return void
	*/
	
	
}